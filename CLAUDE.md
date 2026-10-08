# K-CRM — Definições do projeto de reescrita

Este repositório contém o **K-CRM legado** (JSF 2.2 + BootsFaces + Hibernate 5 + MySQL, Java 7), um CRM de
vendas da Kadesh. Ele será reescrito como **dois projetos em repositórios separados**:

| Projeto            | Repositório (sugestão) | Stack principal                                   |
|--------------------|------------------------|---------------------------------------------------|
| Backend (API REST) | `k-crm-backend`        | Java 25 (LTS) + Spring Boot 4.1.x + PostgreSQL    |
| Frontend (SPA)     | `k-crm-frontend`       | Vue 3 + TypeScript + Vite + Pinia + PrimeVue      |

O código deste repositório é **somente referência funcional**: não copie código, padrões nem configurações
dele. Use-o para entender regras de negócio (seção 3). Cada repositório novo deve ter seu próprio `CLAUDE.md`,
derivado das seções 4 (backend) ou 5 (frontend) + as seções 1–3 e 6, que valem para ambos.

---

## 1. Decisões de arquitetura (registradas)

| Tema              | Decisão                                                                                       |
|-------------------|-----------------------------------------------------------------------------------------------|
| Repositórios      | Separados: backend e frontend, cada um com CI, versionamento e imagem Docker próprios.        |
| Autenticação      | Sessão server-side (Spring Security + Spring Session JDBC) com cookie `HttpOnly`/`Secure`/`SameSite`. **Nenhum token em `localStorage`/`sessionStorage`.** |
| Banco de dados    | PostgreSQL; schema versionado com Flyway.                                                     |
| Arquitetura back  | Monólito modular: pacotes por domínio, fronteiras verificadas com Spring Modulith.            |
| UI                | PrimeVue (modo *unstyled* ou tema próprio), formulários com VeeValidate + Zod.                |
| Idioma do código  | **Domínio em português** (`Pedido`, `Cliente`, `CondicaoPagamento`, `/api/pedidos`); termos técnicos em inglês (`Repository`, `Service`, `Controller`, `Mapper`, `Dto`). Sem acentos em identificadores. |
| Deploy            | Imagens Docker atrás de um reverse proxy no **mesmo domínio** (`/` → frontend/Nginx, `/api` → backend). Docker Compose para desenvolvimento. |
| Escopo inicial    | Paridade funcional com o legado **+ correção das regras quebradas** (seção 3.5).              |

Por que mesmo domínio: o cookie de sessão é `SameSite=Strict`/`Lax` e sem CORS; em dev o Vite faz proxy de
`/api` para `localhost:8080`, reproduzindo o mesmo cenário.

Qualquer mudança nessas decisões deve virar um ADR (`docs/adr/NNNN-titulo.md`) no repositório afetado.

---

## 2. Princípios gerais

- Segurança por padrão (seção 6). Na dúvida, negar.
- Simplicidade: não introduzir mecanismo que o problema atual não exige (YAGNI). Sem microsserviços.
- Contrato primeiro: a API é descrita em OpenAPI (gerada pelo backend via springdoc) e o frontend gera tipos a
  partir dela. Mudança de contrato = mudança nos dois repositórios, coordenada.
- Testes acompanham o código; nenhuma regra de negócio sem teste.
- Dinheiro é sempre decimal exato (`BigDecimal` / `numeric(15,2)` / string ou centavos no JSON) — nunca `double`.
- Datas: `LocalDate` para datas de negócio, `Instant`/`timestamptz` para eventos; UTC no servidor, fuso
  `America/Sao_Paulo` apenas na apresentação.
- Commits pequenos, no padrão Conventional Commits (`feat(pedidos): ...`, `fix(auth): ...`).

---

## 3. Domínio (extraído do legado)

### 3.1 Módulos e entidades

| Módulo          | Entidades                                                                                         |
|-----------------|---------------------------------------------------------------------------------------------------|
| `usuarios`      | `Usuario` (nome, email, login, senha, status, perfil); `Vendedor` (pertence a um `Supervisor`)    |
| `clientes`      | `Cliente` (CNPJ único, razão social, nome fantasia, IE, nº funcionários, ramo, segmento, e-mail NF-e, limite, limite disponível, status, vendedor responsável), `Contato`, `Endereco` |
| `relacionamento`| `Relacionamento` (cliente, data, `TipoRelacionamento`, observação), `Agendamento` (cliente, vendedor, data agendada, data realização, observação, status `PENDENTE`/`REALIZADO`) |
| `catalogo`      | `Produto` (referência única, descrição, nº CA, custo, MC mínima %, preço sugerido, preço mínimo, status, grupo, família, linha), `ProdutoGrade` (numeração + código de barras), `Grupo`, `Familia`, `Linha` (nome e nome reduzido únicos) |
| `logistica`     | `Transportadora` (CNPJ único, razão social, nome fantasia, status, estados atendidos), `Estado`   |
| `pedidos`       | `Pedido`, `ItemPedido`, `GradeVenda` (quantidade por numeração), `CondicaoPagamento` (índice financeiro), `TipoPedido` |

Herança `Usuario → Vendedor/Supervisor` do legado **não** deve ser reproduzida via herança JPA: modele como
`Usuario` + perfil, e o vínculo vendedor→supervisor como associação.

### 3.2 Perfis (roles) e permissões

Perfis: `ADMIN`, `VENDEDOR`, `SUPERVISOR`, `FINANCEIRO`, `LOGISTICA`, `CUSTO`.

| Recurso                       | ADMIN | VENDEDOR      | SUPERVISOR         | FINANCEIRO | LOGISTICA | CUSTO |
|-------------------------------|:-----:|:-------------:|:------------------:|:----------:|:---------:|:-----:|
| Usuários (CRUD)               | ✔     |               |                    |            |           |       |
| Clientes — consultar          | ✔     | só os seus    | da sua equipe      | ✔          | ✔         |       |
| Clientes — criar/editar       | ✔     | só os seus    |                    |            | ✔         |       |
| Produtos/Grupo/Família/Linha — editar | ✔ |            |                    |            |           | ✔     |
| Produtos — consultar          | ✔     | ✔             | ✔                  |            | ✔         | ✔     |
| Ver custo/MC do produto       | ✔     |               | ✔                  | ✔          |           | ✔     |
| Transportadoras — editar      | ✔     |               |                    |            |           |       |
| Transportadoras — consultar   | ✔     | ✔             | ✔                  |            | ✔         |       |
| Pedidos — criar               |       | ✔ (seus clientes) |                |            | ✔         |       |
| Pedidos — consultar           | ✔     | só os seus    | da sua equipe, exceto `ABERTO` | ✔ | ✔     |       |
| Agendamentos                  | ✔     | só os seus    | da sua equipe      |            | ✔         | ✔     |

> Essa tabela reflete o menu do legado com a restrição de escopo corrigida. **Confirmar com o negócio** antes
> de implementar (em especial: escopo do SUPERVISOR, LOGISTICA criando pedido, visibilidade de custo).

Regra obrigatória: permissões são aplicadas **no backend**, por endpoint e por registro (dono/equipe). Esconder
menu no frontend é apenas UX.

### 3.3 Fluxo do pedido (máquina de estados)

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

- Transições só via métodos de domínio (`pedido.finalizar(usuario)`, etc.), que validam estado atual e perfil;
  nunca via `setSituacao`.
- Devolução exige motivo; toda transição gera registro de histórico (quem, quando, de→para, motivo).
- Pedido só é editável em `ABERTO` ou `DEVOLVIDO`.
- A transição `DEVOLVIDO → ABERTO` não existia explicitamente no legado — **confirmar**.

### 3.4 Regras de cálculo

- `precoSugerido = custo × 1,20`
- `precoMinimo = custo × (1 + mcMinima / 100)`
- `ItemPedido.quantidade = Σ GradeVenda.quantidade`
- `ItemPedido.valorItens = quantidade × preco`
- `ItemPedido.mc (%) = (preco − custo) / preco × 100`
- `Pedido.quantidade = Σ itens.quantidade`; `Pedido.valorTotal = Σ itens.valorItens`
- Totais e MC são **sempre calculados no backend**; valores enviados pelo cliente são ignorados.
- Arredondamento: `RoundingMode.HALF_EVEN`, 2 casas para valores, 2 casas para percentuais (confirmar).

### 3.5 Correções em relação ao legado (escopo "paridade + correções")

1. Vendedor só acessa seus próprios clientes/pedidos/agendamentos (no legado, o filtro era só na tela).
2. Preço do item abaixo do `precoMinimo` deve ser bloqueado ou exigir aprovação do supervisor (**definir qual**).
3. Limite de crédito: `limiteDisponivel` deve ser verificado/consumido na aprovação financeira e liberado em
   cancelamento/devolução (no legado o campo existe mas não é usado).
4. Transições de status com validação (no legado, qualquer status podia ser gravado).
5. Pedido só pode usar transportadora que atende o estado do endereço de entrega.
6. Validar CNPJ (dígitos verificadores) de clientes e transportadoras; IE conforme UF quando aplicável.
7. Senhas com Argon2id/bcrypt (legado: SHA-256 sem salt); credenciais fora do código.
8. `Relacionamento`: o legado usa `@OneToOne` com cliente, o que impede vários registros — o correto é N:1.

---

## 4. Backend — `k-crm-backend`

### 4.1 Stack

- Java 25 (LTS), Maven (wrapper `./mvnw`) — Gradle aceitável se o time preferir, mas escolher um só.
- Spring Boot 4.1.x (fixar o patch mais recente; acompanhar releases — cada minor tem ~12 meses de suporte OSS).
- Spring Web MVC, Spring Security 7, Spring Data JPA (Hibernate 7), Bean Validation (Jakarta Validation).
- Spring Session JDBC (sessão persistida no PostgreSQL).
- Flyway para migrações; `spring.jpa.hibernate.ddl-auto=validate` (nunca `update`/`create` fora de testes).
- Spring Modulith (verificação de módulos + eventos de domínio entre módulos).
- springdoc-openapi (OpenAPI 3.1 em `/api/v3/api-docs`, desativado/protegido em produção).
- MapStruct para DTO ↔ entidade.
- Actuator (somente `health`, `info`, `prometheus` expostos, em porta/rota de gerenciamento separada).
- Testes: JUnit 5, AssertJ, Mockito, Spring Boot Test, **Testcontainers (PostgreSQL)**, ArchUnit / Modulith tests,
  spring-security-test.
- Qualidade: Spotless (google-java-format ou palantir), Checkstyle/Error Prone, JaCoCo, OWASP Dependency-Check
  ou Dependabot + CycloneDX (SBOM).

### 4.2 Estrutura

```
src/main/java/br/com/kadesh/crm/
├── CrmApplication.java
├── shared/                 # tipos e utilidades transversais (Dinheiro, Cnpj, erros, auditoria, segurança)
├── usuarios/
├── clientes/
├── relacionamento/
├── catalogo/
├── logistica/
└── pedidos/
    ├── api/                # Controllers REST + DTOs (records) de request/response
    ├── application/        # Services/casos de uso, @Transactional, autorização por registro
    ├── domain/             # Entidades, value objects, regras, eventos, interfaces de repositório
    └── infrastructure/     # Implementações JPA específicas, integrações
src/main/resources/
├── application.yml
└── db/migration/           # V1__baseline.sql, V2__...
```

- Um módulo só acessa outro pela API pública dele (pacote raiz do módulo ou `@NamedInterface`); nunca por
  `domain`/`infrastructure` alheios. Teste `ApplicationModules.of(CrmApplication.class).verify()` obrigatório.
- Comunicação assíncrona entre módulos por eventos (`@ApplicationModuleListener`).

### 4.3 Convenções de código

- Injeção por construtor (campos `final`); nada de `@Autowired` em campo.
- DTOs são `record`s com validação (`@NotBlank`, `@Size`, `@Positive`...). **Nunca** expor ou receber entidades
  JPA nos controllers (evita mass assignment e vazamento de dados).
- Entidades: id `Long` (ou UUID para recursos expostos externamente — decidir em ADR), `@Version` para lock
  otimista, auditoria (`createdAt/By`, `updatedAt/By`) via Spring Data Auditing, `equals/hashCode` baseados no id
  com tratamento de entidade transiente.
- Associações `LAZY` por padrão; carregar o necessário com `@EntityGraph`/fetch join;
  `spring.jpa.open-in-view=false`.
- Listagens sempre paginadas (`Pageable`, tamanho máximo limitado, ex. 100) e com ordenação por whitelist.
- Consultas: Spring Data derivadas, JPQL com parâmetros ou Specifications. **Proibido** concatenar strings em
  queries.
- `@Transactional` na camada `application`; `readOnly = true` em consultas.
- Erros: exceções de domínio específicas + `@RestControllerAdvice` retornando `ProblemDetail` (RFC 9457).
  Nunca retornar stack trace, mensagem SQL ou detalhes internos.
- Logs com SLF4J, estruturados (JSON em produção), com correlation id (MDC/Micrometer tracing). Nunca logar
  senha, cookie, CPF/CNPJ completo de contato, ou payloads inteiros.
- Configuração por `@ConfigurationProperties` tipadas e validadas; segredos via variáveis de ambiente.
- REST: substantivos no plural (`/api/pedidos/{id}`), ações de estado como sub-recursos
  (`POST /api/pedidos/{id}/finalizar`), códigos HTTP corretos (201 + `Location`, 204, 400, 401, 403, 404, 409, 422).
  Versionamento só quando houver quebra.

### 4.4 Testes

- Unitários para domínio (regras de cálculo, máquina de estados) — sem Spring.
- `@WebMvcTest` para controllers, incluindo **testes de autorização para cada endpoint** (perfil permitido,
  perfil negado, acesso a registro de outro vendedor → 403/404).
- Integração com Testcontainers PostgreSQL (`@ServiceConnection`), aplicando as migrações Flyway reais.
- Cobertura mínima sugerida: 80% em `domain` e `application`.

### 4.5 Comandos

```bash
./mvnw verify                      # build + testes + checks
./mvnw spring-boot:run             # sobe a API (requer PostgreSQL; use docker compose up -d db)
./mvnw spotless:apply              # formatação
docker compose up -d               # PostgreSQL + (opcional) Mailpit em dev
```

---

## 5. Frontend — `k-crm-frontend`

### 5.1 Stack

- Vue 3 (versão estável mais recente; não usar Vapor Mode enquanto experimental) com **Composition API e
  `<script setup lang="ts">`** exclusivamente.
- TypeScript em modo `strict` (sem `any`; `noUncheckedIndexedAccess` ligado).
- Vite, Vue Router 4, Pinia (setup stores), PrimeVue, VeeValidate + Zod.
- Cliente HTTP tipado: tipos gerados do OpenAPI do backend com `openapi-typescript` + `openapi-fetch`
  (ou wrapper único sobre `fetch`). TanStack Query (Vue Query) opcional para cache de dados de servidor.
- Testes: Vitest + Vue Test Utils (unitários/componentes), Playwright (E2E), MSW para mocks de API.
- Qualidade: ESLint (flat config, `eslint-plugin-vue`, `typescript-eslint`), Prettier, `vue-tsc --noEmit` no CI.
- Gerenciador de pacotes: pnpm, com lockfile commitado e `pnpm install --frozen-lockfile` no CI.

### 5.2 Estrutura (por feature)

```
src/
├── app/                # main.ts, App.vue, router, plugins (PrimeVue, Pinia)
├── shared/
│   ├── api/            # cliente HTTP, tipos gerados (schema.d.ts), tratamento de erros/CSRF
│   ├── components/     # componentes genéricos reutilizáveis
│   ├── composables/
│   └── utils/          # formatação de moeda/CNPJ/datas
└── features/
    ├── auth/           # login, sessão do usuário, guards
    ├── clientes/
    ├── catalogo/
    ├── pedidos/
    │   ├── api.ts      # chamadas da feature
    │   ├── store.ts    # Pinia (só se houver estado compartilhado)
    │   ├── components/
    │   ├── views/      # páginas roteadas (lazy-loaded)
    │   └── schemas.ts  # Zod
    └── ...
```

### 5.3 Convenções

- Componentes em PascalCase com nome de múltiplas palavras (`PedidoForm.vue`); props e emits tipados com
  `defineProps<...>()`/`defineEmits<...>()`; `defineModel` para v-model.
- Lógica reutilizável em composables (`useXxx`); nada de mixins.
- Estado: Pinia apenas para estado global (usuário logado, preferências). Dados de servidor ficam nas
  chamadas/queries da feature. Alterar estado do store apenas por actions; usar `storeToRefs` ao desestruturar.
- Rotas com lazy loading (`() => import(...)`) e `meta: { roles: [...] }` para guards de UX.
- Formulários: validação com Zod no cliente **e** sempre tratar os erros 400/422 do backend (fonte da verdade).
- Valores monetários exibidos com `Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' })`; não
  fazer cálculo financeiro definitivo no frontend (apenas prévia).
- i18n: textos em pt-BR; se houver chance de outros idiomas, usar `vue-i18n` desde o início.
- Acessibilidade: componentes PrimeVue com labels, foco visível, navegação por teclado.

### 5.4 Comandos

```bash
pnpm install
pnpm dev               # Vite em http://localhost:5173 com proxy /api → http://localhost:8080
pnpm build             # vue-tsc + vite build
pnpm test              # Vitest
pnpm test:e2e          # Playwright
pnpm lint && pnpm format
pnpm gen:api           # regenera src/shared/api/schema.d.ts a partir do OpenAPI do backend
```

---

## 6. Segurança (obrigatório em ambos os projetos)

Base: **OWASP Top 10:2025**, **OWASP ASVS 5.0 (alvo: nível 2)** e OWASP Cheat Sheet Series. Toda feature nova
deve ser revisada contra esta seção.

### 6.1 Mapeamento OWASP Top 10:2025

| Risco                                        | Medidas                                                                                  |
|----------------------------------------------|------------------------------------------------------------------------------------------|
| A01 Broken Access Control (inclui SSRF)      | `authorizeHttpRequests` com deny-by-default; `@PreAuthorize` por caso de uso; verificação de dono/equipe em **todo** acesso por id (evitar IDOR — retornar 404 para registros fora do escopo); testes de autorização; sem chamadas HTTP a URLs vindas do usuário. |
| A02 Security Misconfiguration                | Perfis `dev`/`prod` separados; em prod: sem Swagger público, sem `show-sql`, Actuator restrito, erros genéricos; headers de segurança (abaixo); imagens Docker mínimas, usuário não-root. |
| A03 Software Supply Chain Failures           | Versões fixadas e lockfiles; Dependabot/Renovate; OWASP Dependency-Check / `pnpm audit` no CI (falha em CVSS ≥ 7); SBOM CycloneDX; actions do GitHub fixadas por SHA; revisar novas dependências antes de adicionar. |
| A04 Cryptographic Failures                   | TLS em tudo (HSTS); senhas com `Argon2PasswordEncoder` (ou bcrypt ≥ 12) via `DelegatingPasswordEncoder`; segredos em variáveis de ambiente/secret manager, nunca no repositório; conexão TLS com o banco em prod. |
| A05 Injection                                | Somente queries parametrizadas/Spring Data; Bean Validation em todos os DTOs; no Vue, nunca `v-html` com dado do usuário (se inevitável, DOMPurify); CSP estrita; validar uploads (se houver) por tipo/tamanho. |
| A06 Insecure Design                          | Regras de negócio no backend (preços, totais, transições de status, limite de crédito); modelagem de ameaças leve para fluxos críticos (login, aprovação de pedido); limites de tamanho de payload e paginação. |
| A07 Authentication Failures                  | Login por `POST /api/auth/login` com mensagem genérica; rate limit e bloqueio progressivo por usuário/IP (ex. Bucket4j); regeneração do id de sessão no login (session fixation); logout invalida sessão e cookie; timeout ocioso 30 min e absoluto 8 h; política de senha ASVS (mín. 12 caracteres, checagem contra senhas vazadas); MFA (TOTP) planejado para ADMIN/FINANCEIRO. |
| A08 Software or Data Integrity Failures      | Proteção CSRF ativa (`CookieCsrfTokenRepository` + header `X-XSRF-TOKEN`, handler para SPA); `@Version` contra atualização perdida; nada de desserialização de tipos arbitrários (Jackson sem default typing); build reprodutível no CI. |
| A09 Security Logging and Alerting Failures   | Log de auditoria para login (sucesso/falha), logout, mudança de perfil/senha, transições de pedido, alterações de preço/limite; logs sem dados sensíveis e protegidos contra log injection; métricas e alertas para picos de 401/403/429. |
| A10 Mishandling of Exceptional Conditions    | Handler global com `ProblemDetail`; falhar fechado (erro de autorização ⇒ negar); transações com rollback; timeouts em chamadas externas; nunca engolir exceção silenciosamente; frontend trata 401 (redireciona ao login), 403, 409 e erros de rede sem expor detalhes. |

### 6.2 Configuração de sessão e cookies

- Cookie de sessão: nome neutro (ex. `__Host-SESSION`), `HttpOnly`, `Secure`, `SameSite=Lax` (ou `Strict`),
  `Path=/`, sem `Domain`.
- Frontend chama a API com `credentials: 'same-origin'` e envia o header CSRF lido do cookie `XSRF-TOKEN` em
  métodos que alteram estado.
- Endpoint `GET /api/auth/me` retorna usuário e perfis para o frontend montar a UI; o frontend não guarda nada
  sensível em storage do navegador.
- Sem CORS em produção (mesmo domínio). Se for indispensável, whitelist explícita, nunca `*` com credenciais.

### 6.3 Headers HTTP (reverse proxy / Spring Security)

```
Strict-Transport-Security: max-age=63072000; includeSubDomains
Content-Security-Policy: default-src 'self'; script-src 'self'; style-src 'self'; img-src 'self' data:;
                         connect-src 'self'; frame-ancestors 'none'; base-uri 'self'; form-action 'self'
X-Content-Type-Options: nosniff
Referrer-Policy: strict-origin-when-cross-origin
Permissions-Policy: camera=(), microphone=(), geolocation=()
Cross-Origin-Opener-Policy: same-origin
```

(Se o tema do PrimeVue exigir estilos inline, preferir o modo com CSS gerado em build ou nonce em vez de
`'unsafe-inline'`.)

### 6.4 Dados pessoais (LGPD)

- Contatos de clientes contêm dados pessoais (nome, e-mail, telefone): acesso restrito por perfil, mascarados
  em logs, com possibilidade de exclusão/anonimização.
- Exportações de dados (CSV/Excel) apenas para perfis autorizados e auditadas.

### 6.5 Checklist de PR (segurança)

- [ ] Endpoint novo tem regra de autorização explícita e teste de acesso negado.
- [ ] Acesso por id verifica escopo do usuário.
- [ ] Entrada validada (DTO + Bean Validation / Zod) e saída sem campos internos.
- [ ] Nenhum segredo, credencial ou dado real no código, testes ou logs.
- [ ] Nova dependência justificada e sem CVE alta/crítica.
- [ ] Erros retornam `ProblemDetail` sem detalhes internos.

---

## 7. Infraestrutura e CI

- Docker multi-stage: backend em imagem JRE mínima (ex. Eclipse Temurin 25 JRE / distroless), usuário não-root;
  frontend servido por Nginx (unprivileged) com os headers da seção 6.3 e fallback de SPA para `index.html`.
- Reverse proxy (Nginx/Traefik/Caddy) terminando TLS e roteando `/api` → backend, `/` → frontend.
- `docker-compose.yml` de dev: PostgreSQL (+ Mailpit se houver e-mail). Variáveis em `.env` (não commitado;
  manter `.env.example`).
- CI (GitHub Actions) por repositório: build, lint, testes, análise de dependências, build da imagem e scan
  (ex. Trivy). PR só mergeia com CI verde.
- Health checks via Actuator (`/actuator/health/liveness` e `readiness`).

---

## 8. Definição de pronto (DoD)

1. Regras de negócio implementadas no backend com testes unitários e de integração.
2. Autorização testada (permitido e negado).
3. Migração Flyway incluída quando houver mudança de schema (nunca editar migração já aplicada).
4. Contrato OpenAPI atualizado e tipos do frontend regenerados.
5. Lint, formatação, type-check e testes passando no CI.
6. Checklist de segurança (6.5) revisado.

---

## 9. Pontos em aberto (confirmar com o negócio)

- Matriz de permissões da seção 3.2 (SUPERVISOR, LOGISTICA, CUSTO).
- Preço abaixo do mínimo: bloquear ou exigir aprovação do supervisor?
- Regra exata de consumo/liberação do limite de crédito.
- Caminho de um pedido `DEVOLVIDO` (volta para `ABERTO`? para quem foi devolvido?).
- Uso do campo `frete` do item e do `indiceFinanceiro` da condição de pagamento no cálculo do valor.
- Necessidade de migrar dados do MySQL legado (fora do escopo inicial).
- IDs numéricos sequenciais vs UUID nos recursos expostos.
