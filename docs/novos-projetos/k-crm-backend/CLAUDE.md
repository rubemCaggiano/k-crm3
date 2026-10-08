# k-crm-backend

API REST do **K-CRM**, CRM de vendas da Kadesh. Reescrita do sistema legado (JSF + Hibernate 5 + MySQL),
que fica no repositório `k-crm3` **somente para consulta** de regras de negócio — não copie código,
padrões nem configurações de lá.

O frontend fica em outro repositório: `k-crm-frontend` (Vue 3 + TypeScript).

---

## 1. Decisões de arquitetura

| Tema            | Decisão                                                                                         |
|-----------------|-------------------------------------------------------------------------------------------------|
| Stack           | Java 25 (LTS) + Spring Boot 4.1.x + PostgreSQL                                                  |
| Arquitetura     | Monólito modular: pacotes por domínio, fronteiras verificadas com Spring Modulith               |
| Autenticação    | Sessão server-side (Spring Security + Spring Session JDBC), cookie `HttpOnly`/`Secure`/`SameSite`. Sem JWT, sem token no navegador |
| Banco           | PostgreSQL; schema versionado com Flyway                                                        |
| Idioma do código| Domínio em português (`Pedido`, `CondicaoPagamento`, `/api/pedidos`); termos técnicos em inglês (`Repository`, `Service`, `Controller`, `Mapper`, `Dto`). Sem acentos em identificadores |
| Deploy          | Imagem Docker atrás de reverse proxy no **mesmo domínio** do frontend (`/api` → backend, `/` → frontend). Sem CORS |
| Escopo inicial  | Paridade com o legado + correção das regras quebradas (seção 3.5)                              |
| Contrato        | OpenAPI gerado pelo springdoc; o frontend gera seus tipos a partir dele. Mudança de contrato = mudança coordenada nos dois repositórios |

Mudanças nessas decisões viram ADR em `docs/adr/NNNN-titulo.md`.

## 2. Princípios

- Segurança por padrão (seção 5). Na dúvida, negar.
- YAGNI: nada de mecanismo que o problema atual não exige. Sem microsserviços.
- Toda regra de negócio tem teste.
- Dinheiro sempre `BigDecimal` / `numeric(15,2)`; no JSON como string decimal. Nunca `double`.
- Datas: `LocalDate` para datas de negócio, `Instant`/`timestamptz` para eventos; servidor em UTC.
- Commits pequenos em Conventional Commits (`feat(pedidos): ...`, `fix(auth): ...`).

---

## 3. Domínio

### 3.1 Módulos e entidades

| Módulo           | Entidades |
|------------------|-----------|
| `usuarios`       | `Usuario` (nome, email, login, senha, status, perfil); vínculo vendedor → supervisor como associação (não usar herança JPA como o legado) |
| `clientes`       | `Cliente` (CNPJ único, razão social, nome fantasia, IE, nº funcionários, ramo, segmento, e-mail NF-e, limite, limite disponível, status, vendedor responsável), `Contato`, `Endereco` |
| `relacionamento` | `Relacionamento` (cliente N:1, data, `TipoRelacionamento`, observação), `Agendamento` (cliente, vendedor, data agendada, data realização, observação, status `PENDENTE`/`REALIZADO`) |
| `catalogo`       | `Produto` (referência única, descrição, nº CA, custo, MC mínima %, preço sugerido, preço mínimo, status, grupo, família, linha), `ProdutoGrade` (numeração + código de barras), `Grupo`, `Familia`, `Linha` (nome e nome reduzido únicos) |
| `logistica`      | `Transportadora` (CNPJ único, razão social, nome fantasia, status, estados atendidos), `Estado` |
| `pedidos`        | `Pedido`, `ItemPedido`, `GradeVenda` (quantidade por numeração), `CondicaoPagamento` (índice financeiro), `TipoPedido`, histórico de situação |

### 3.2 Perfis e permissões

Perfis: `ADMIN`, `VENDEDOR`, `SUPERVISOR`, `FINANCEIRO`, `LOGISTICA`, `CUSTO`.

| Recurso                                | ADMIN | VENDEDOR          | SUPERVISOR                     | FINANCEIRO | LOGISTICA | CUSTO |
|----------------------------------------|:-----:|:-----------------:|:------------------------------:|:----------:|:---------:|:-----:|
| Usuários (CRUD)                        | ✔     |                   |                                |            |           |       |
| Clientes — consultar                   | ✔     | só os seus        | da sua equipe                  | ✔          | ✔         |       |
| Clientes — criar/editar                | ✔     | só os seus        |                                |            | ✔         |       |
| Produtos/Grupo/Família/Linha — editar  | ✔     |                   |                                |            |           | ✔     |
| Produtos — consultar                   | ✔     | ✔                 | ✔                              |            | ✔         | ✔     |
| Ver custo/MC do produto                | ✔     |                   | ✔                              | ✔          |           | ✔     |
| Transportadoras — editar               | ✔     |                   |                                |            |           |       |
| Transportadoras — consultar            | ✔     | ✔                 | ✔                              |            | ✔         |       |
| Pedidos — criar                        |       | ✔ (seus clientes) |                                |            | ✔         |       |
| Pedidos — consultar                    | ✔     | só os seus        | da sua equipe, exceto `ABERTO` | ✔          | ✔         |       |
| Agendamentos                           | ✔     | só os seus        | da sua equipe                  |            | ✔         | ✔     |

> Pendente de confirmação com o negócio (seção 8).

Permissões são aplicadas **aqui no backend**, por endpoint (`@PreAuthorize`) **e por registro** (dono/equipe).
O frontend esconder menus é só UX. Campos restritos (ex.: custo/MC) são omitidos do DTO para perfis sem acesso.

### 3.3 Fluxo do pedido

```
           finalizar (VENDEDOR)           aprovar (SUPERVISOR)            aprovar (FINANCEIRO)
ABERTO ───────────────────────► FINALIZADO ──────────────────► APROVADO_SUPERVISOR ──────────────► APROVADO_FINANCEIRO
  │  ▲                              │                                │                             (enviado à produção)
  │  │ reabrir (VENDEDOR)           │ devolver (SUPERVISOR)          │ devolver (FINANCEIRO)
  │  └──────────── DEVOLVIDO ◄──────┴────────────────────────────────┘
  │ cancelar (VENDEDOR)
  ▼
CANCELADO
```

- Transições só por métodos de domínio (`pedido.finalizar(usuario)`, `pedido.devolver(usuario, motivo)`...),
  que validam situação atual e perfil. Sem `setSituacao` público.
- Devolução exige motivo. Toda transição grava histórico (quem, quando, de → para, motivo) e publica evento.
- Pedido só é editável em `ABERTO` ou `DEVOLVIDO`.
- Endpoints: `POST /api/pedidos/{id}/finalizar`, `/aprovar`, `/devolver`, `/cancelar`, `/reabrir`.

### 3.4 Cálculos (sempre no backend; valores de totais enviados pelo cliente são ignorados)

- `precoSugerido = custo × 1,20`
- `precoMinimo = custo × (1 + mcMinima / 100)`
- `ItemPedido.quantidade = Σ GradeVenda.quantidade`
- `ItemPedido.valorItens = quantidade × preco`
- `ItemPedido.mc (%) = (preco − custo) / preco × 100`
- `Pedido.quantidade = Σ itens.quantidade`; `Pedido.valorTotal = Σ itens.valorItens`
- Arredondamento `HALF_EVEN`, 2 casas (confirmar).

### 3.5 Correções em relação ao legado

1. Vendedor só acessa seus clientes/pedidos/agendamentos (no legado o filtro era só na tela).
2. Preço abaixo do `precoMinimo`: bloquear ou exigir aprovação do supervisor (**a definir**).
3. Limite de crédito verificado/consumido na aprovação financeira e liberado em cancelamento/devolução.
4. Transições de situação validadas (no legado qualquer valor era gravado).
5. Transportadora precisa atender a UF do endereço de entrega.
6. CNPJ validado (dígitos verificadores) em clientes e transportadoras; IE conforme UF quando aplicável.
7. Senhas com Argon2id; nenhuma credencial no código.
8. `Relacionamento` é N:1 com cliente (o legado usava `@OneToOne`).

---

## 4. Implementação

### 4.1 Stack

- Java 25, Maven com wrapper (`./mvnw`).
- Spring Boot 4.1.x (fixar o patch mais recente), Spring Web MVC, Spring Security 7, Spring Data JPA
  (Hibernate 7), Jakarta Validation, Spring Session JDBC, Spring Modulith.
- Flyway; `spring.jpa.hibernate.ddl-auto=validate` (nunca `update`/`create`).
- springdoc-openapi (`/api/v3/api-docs`), MapStruct, Actuator (só `health`, `info`, `prometheus`).
- Testes: JUnit 5, AssertJ, Mockito, Spring Boot Test, spring-security-test, Testcontainers (PostgreSQL),
  Spring Modulith tests / ArchUnit.
- Qualidade: Spotless, Checkstyle ou Error Prone, JaCoCo, Dependabot + OWASP Dependency-Check, SBOM CycloneDX.

### 4.2 Estrutura

```
src/main/java/br/com/kadesh/crm/
├── CrmApplication.java
├── shared/              # tipos transversais (Dinheiro, Cnpj), erros, auditoria, configuração de segurança
├── usuarios/
├── clientes/
├── relacionamento/
├── catalogo/
├── logistica/
└── pedidos/
    ├── api/             # Controllers REST + DTOs (records)
    ├── application/     # casos de uso, @Transactional, autorização por registro
    ├── domain/          # entidades, value objects, regras, eventos, interfaces de repositório
    └── infrastructure/  # implementações JPA específicas, integrações
src/main/resources/
├── application.yml (+ application-dev.yml, application-prod.yml)
└── db/migration/        # V1__baseline.sql, V2__...
```

- Um módulo só usa outro pela API pública dele (pacote raiz ou `@NamedInterface`), nunca por `domain`/`infrastructure`
  alheios. O teste `ApplicationModules.of(CrmApplication.class).verify()` é obrigatório.
- Reações entre módulos via eventos (`@ApplicationModuleListener`).

### 4.3 Convenções

- Injeção por construtor, campos `final`. Sem `@Autowired` em campo.
- DTOs são `record`s com Bean Validation. **Nunca** receber ou devolver entidade JPA no controller.
- Entidades: `@Version`, auditoria (`createdAt/By`, `updatedAt/By`) via Spring Data Auditing, `equals/hashCode`
  pelo id tratando entidade transiente, sem setters para campos com regra.
- Associações `LAZY`; carregar com `@EntityGraph`/fetch join; `spring.jpa.open-in-view=false`.
- Listagens paginadas (`Pageable`, máx. 100 por página) com ordenação só por campos permitidos.
- Consultas: métodos derivados, JPQL com parâmetros ou Specifications. **Proibido** concatenar strings em query.
- `@Transactional` na camada `application`; `readOnly = true` em consultas.
- Erros: exceções de domínio + `@RestControllerAdvice` devolvendo `ProblemDetail` (RFC 9457), sem stack trace,
  SQL ou detalhes internos.
- Logs SLF4J estruturados (JSON em prod) com correlation id; nunca logar senha, cookie, payload inteiro ou dados
  pessoais completos.
- Configuração com `@ConfigurationProperties` validadas; segredos só por variáveis de ambiente.
- REST: recursos no plural, ações de estado como sub-recursos, status HTTP corretos (201 + `Location`, 204, 400,
  401, 403, 404, 409, 422).

### 4.4 Testes

- Unitários sem Spring para domínio (cálculos, máquina de estados).
- `@WebMvcTest` por controller com **teste de autorização em todo endpoint**: perfil permitido, perfil negado,
  registro de outro vendedor (→ 404).
- Integração com Testcontainers + `@ServiceConnection`, rodando as migrações Flyway reais.
- Cobertura mínima de 80% em `domain` e `application`.

### 4.5 Comandos

```bash
docker compose up -d db          # PostgreSQL local
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
./mvnw verify                    # build + testes + checks
./mvnw spotless:apply            # formatação
```

---

## 5. Segurança (OWASP Top 10:2025 + ASVS 5.0 nível 2)

| Risco | Medidas neste projeto |
|-------|-----------------------|
| A01 Broken Access Control | `authorizeHttpRequests` deny-by-default; `@PreAuthorize` por caso de uso; checagem de dono/equipe em todo acesso por id (404 fora do escopo); sem requisições HTTP para URLs vindas do usuário (SSRF). |
| A02 Security Misconfiguration | Perfis `dev`/`prod`; em prod sem Swagger público, sem `show-sql`, Actuator em porta de gerenciamento, erros genéricos; headers de segurança; imagem mínima com usuário não-root. |
| A03 Software Supply Chain Failures | Versões fixadas; Dependabot; Dependency-Check no CI falhando em CVSS ≥ 7; SBOM; actions fixadas por SHA; nova dependência só com justificativa. |
| A04 Cryptographic Failures | TLS + HSTS; `DelegatingPasswordEncoder` com Argon2id; segredos fora do repositório; TLS com o banco em prod. |
| A05 Injection | Só queries parametrizadas; Bean Validation em todo DTO; validação de tamanho/formato; nada de SQL nativo montado com string. |
| A06 Insecure Design | Regras (preço, totais, situação, crédito) no domínio; modelagem de ameaças leve para login e aprovação; limites de payload e paginação. |
| A07 Authentication Failures | `POST /api/auth/login` com mensagem genérica; rate limit e bloqueio progressivo (Bucket4j); troca do id de sessão no login; logout invalida sessão; timeout ocioso 30 min e absoluto 8 h; senha mín. 12 caracteres e checagem contra senhas vazadas; MFA (TOTP) planejado para ADMIN/FINANCEIRO. |
| A08 Software or Data Integrity Failures | CSRF ativo (`CookieCsrfTokenRepository` + header `X-XSRF-TOKEN`, handler para SPA); `@Version`; Jackson sem default typing. |
| A09 Security Logging and Alerting Failures | Auditoria de login/logout, mudança de perfil/senha, transições de pedido, alteração de preço/limite; logs sem dados sensíveis e protegidos contra log injection; métricas de 401/403/429. |
| A10 Mishandling of Exceptional Conditions | Handler global; falhar fechado; rollback de transação; timeouts em chamadas externas; nunca engolir exceção. |

Sessão e cookies:
- Cookie `__Host-SESSION`: `HttpOnly`, `Secure`, `SameSite=Lax`, `Path=/`, sem `Domain`.
- `GET /api/auth/me` devolve usuário e perfis para o frontend.
- Sem CORS (mesmo domínio). Se um dia for necessário: whitelist explícita, nunca `*` com credenciais.

LGPD: dados de contatos (nome, e-mail, telefone) com acesso por perfil, mascarados em logs, com
exclusão/anonimização possível; exportações só para perfis autorizados e auditadas.

### Checklist de PR

- [ ] Endpoint novo com autorização explícita e teste de acesso negado.
- [ ] Acesso por id verifica escopo do usuário.
- [ ] DTO de entrada validado; DTO de saída sem campos internos ou restritos.
- [ ] Nenhum segredo ou dado real no código, testes ou logs.
- [ ] Dependência nova justificada e sem CVE alta/crítica.
- [ ] Erros em `ProblemDetail` sem detalhes internos.

---

## 6. Infraestrutura e CI

- Dockerfile multi-stage; runtime Temurin 25 JRE (ou distroless), usuário não-root, health checks em
  `/actuator/health/liveness` e `/readiness`.
- `docker-compose.yml` para dev com PostgreSQL; variáveis em `.env` (não commitado) com `.env.example`.
- GitHub Actions: build, testes, Spotless check, Dependency-Check, build da imagem e scan (Trivy). Merge só com CI verde.
- O contrato OpenAPI é publicado como artefato do CI para o frontend gerar os tipos.

## 7. Definição de pronto

1. Regra de negócio com testes unitários e de integração.
2. Autorização testada (permitido e negado).
3. Migração Flyway quando houver mudança de schema (nunca editar migração já aplicada).
4. OpenAPI atualizado e o frontend avisado de mudança de contrato.
5. CI verde e checklist de segurança revisado.

## 8. Pontos em aberto

- Matriz de permissões (SUPERVISOR, LOGISTICA, CUSTO).
- Preço abaixo do mínimo: bloquear ou exigir aprovação do supervisor?
- Regra de consumo/liberação do limite de crédito.
- Destino de um pedido `DEVOLVIDO`.
- Uso do `frete` do item e do `indiceFinanceiro` da condição de pagamento no valor do pedido.
- IDs sequenciais ou UUID nos recursos expostos.
- Migração de dados do MySQL legado (fora do escopo inicial).
