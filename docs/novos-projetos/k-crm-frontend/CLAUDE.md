# k-crm-frontend

SPA do **K-CRM**, CRM de vendas da Kadesh. Reescrita do sistema legado (JSF + BootsFaces), que fica no
repositório `k-crm3` **somente para consulta** de telas e regras — não copie código nem layout de lá.

A API fica em outro repositório: `k-crm-backend` (Spring Boot). **O backend é a fonte da verdade** para regras,
permissões e cálculos; o frontend só apresenta e valida para UX.

---

## 1. Decisões de arquitetura

| Tema             | Decisão                                                                                       |
|------------------|-----------------------------------------------------------------------------------------------|
| Stack            | Vue 3 + TypeScript (strict) + Vite + Vue Router + Pinia + PrimeVue                            |
| Autenticação     | Sessão do backend via cookie `HttpOnly`. O frontend **nunca** lida com token e não guarda nada sensível em `localStorage`/`sessionStorage`/IndexedDB |
| Hospedagem       | Imagem Docker (Nginx unprivileged) atrás de reverse proxy no **mesmo domínio** da API (`/` → frontend, `/api` → backend). Sem CORS |
| Contrato         | Tipos gerados do OpenAPI do backend; nada de tipos de API escritos à mão                      |
| Idioma do código | Domínio em português (`PedidoForm.vue`, `useClientes`), termos técnicos em inglês. Sem acentos em identificadores. Textos da UI em pt-BR |
| Escopo inicial   | Paridade com as telas do legado, seguindo as regras corrigidas do backend                     |

Mudanças nessas decisões viram ADR em `docs/adr/NNNN-titulo.md`.

## 2. Princípios

- Segurança por padrão (seção 5).
- YAGNI: só adicionar biblioteca quando houver necessidade real.
- Todo componente/composable com lógica tem teste.
- Valores monetários chegam como string decimal; exibir com
  `Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' })`. Cálculos no front são só prévia.
- Datas exibidas em `America/Sao_Paulo`.
- Commits pequenos em Conventional Commits (`feat(pedidos): ...`).

---

## 3. Domínio (resumo para a UI)

### 3.1 Áreas

Clientes (contatos, endereço, relacionamentos), Agendamentos, Catálogo (produtos com grade de numeração,
grupos, famílias, linhas), Transportadoras, Pedidos (itens com grade por numeração), Usuários.

### 3.2 Perfis e menus

Perfis: `ADMIN`, `VENDEDOR`, `SUPERVISOR`, `FINANCEIRO`, `LOGISTICA`, `CUSTO`.

| Tela / ação                           | ADMIN | VENDEDOR | SUPERVISOR | FINANCEIRO | LOGISTICA | CUSTO |
|---------------------------------------|:-----:|:--------:|:----------:|:----------:|:---------:|:-----:|
| Usuários                              | ✔     |          |            |            |           |       |
| Clientes — consultar                  | ✔     | ✔        | ✔          | ✔          | ✔         |       |
| Clientes — criar/editar               | ✔     | ✔        |            |            | ✔         |       |
| Catálogo — editar                     | ✔     |          |            |            |           | ✔     |
| Catálogo — consultar                  | ✔     | ✔        | ✔          |            | ✔         | ✔     |
| Transportadoras — editar              | ✔     |          |            |            |           |       |
| Transportadoras — consultar           | ✔     | ✔        | ✔          |            | ✔         |       |
| Pedidos — criar                       |       | ✔        |            |            | ✔         |       |
| Pedidos — consultar                   | ✔     | ✔        | ✔          | ✔          | ✔         |       |
| Agendamentos                          | ✔     | ✔        | ✔          |            | ✔         | ✔     |

O escopo dos registros (ex.: vendedor só vê os seus) é aplicado pelo backend. O frontend usa os perfis de
`GET /api/auth/me` apenas para montar menus, rotas e botões; nunca para decidir acesso a dados.

### 3.3 Fluxo do pedido

`ABERTO → FINALIZADO → APROVADO_SUPERVISOR → APROVADO_FINANCEIRO`, com `DEVOLVIDO` (a partir de FINALIZADO ou
APROVADO_SUPERVISOR, com motivo obrigatório) e `CANCELADO` (a partir de ABERTO).

- Botões de ação (`finalizar`, `aprovar`, `devolver`, `cancelar`, `reabrir`) chamam
  `POST /api/pedidos/{id}/<acao>` e aparecem conforme situação + perfil; o backend valida de novo.
- Pedido só é editável em `ABERTO` ou `DEVOLVIDO`.
- Mostrar o histórico de situação (quem, quando, motivo).

### 3.4 Tela de pedido

- Seleção de cliente carrega o endereço de entrega; transportadoras filtradas pela UF de entrega.
- Item: produto → quantidades por numeração (grade) → quantidade total = soma da grade; preço; prévia de
  valor e MC% (`(preco − custo) / preco × 100`) só para perfis que veem custo.
- Avisar quando o preço estiver abaixo do preço mínimo (a regra final é do backend).
- Totais exibidos são os devolvidos pela API.

---

## 4. Implementação

### 4.1 Stack

- Vue 3 (estável mais recente; sem Vapor Mode enquanto experimental), **somente** Composition API com
  `<script setup lang="ts">`.
- TypeScript `strict`, `noUncheckedIndexedAccess`, sem `any`.
- Vite, Vue Router 4, Pinia (setup stores), PrimeVue (tema próprio, CSS gerado em build), VeeValidate + Zod.
- API: `openapi-typescript` + `openapi-fetch` (tipos em `src/shared/api/schema.d.ts`). TanStack Query (Vue Query)
  se o cache de dados de servidor justificar.
- Testes: Vitest + Vue Test Utils, MSW para mocks, Playwright para E2E.
- Qualidade: ESLint flat config (`eslint-plugin-vue`, `typescript-eslint`), Prettier, `vue-tsc --noEmit`.
- pnpm com lockfile commitado; CI usa `pnpm install --frozen-lockfile`.

### 4.2 Estrutura (por feature)

```
src/
├── app/                # main.ts, App.vue, router, plugins (PrimeVue, Pinia), layout
├── shared/
│   ├── api/            # cliente HTTP (credenciais, CSRF, erros), schema.d.ts gerado
│   ├── components/
│   ├── composables/
│   └── utils/          # formatação de moeda, CNPJ, datas
└── features/
    ├── auth/           # login, store do usuário, guards
    ├── clientes/
    ├── agendamentos/
    ├── catalogo/
    ├── transportadoras/
    ├── usuarios/
    └── pedidos/
        ├── api.ts
        ├── store.ts    # só se houver estado compartilhado
        ├── schemas.ts  # Zod
        ├── components/
        └── views/      # páginas roteadas (lazy-loaded)
```

Uma feature não importa arquivos internos de outra; o que for compartilhado vai para `shared/`.

### 4.3 Convenções

- Componentes PascalCase com nome composto (`PedidoForm.vue`); `defineProps<...>()`, `defineEmits<...>()`,
  `defineModel` para v-model.
- Lógica reutilizável em composables `useXxx`; sem mixins.
- Pinia só para estado global (usuário logado, preferências); alterar estado por actions; `storeToRefs` ao
  desestruturar.
- Rotas lazy (`() => import(...)`) com `meta: { roles: [...] }`; guard global consulta `/api/auth/me`.
- Formulários: Zod no cliente **e** exibir os erros de validação do backend (`ProblemDetail` 400/422) nos campos.
- Listagens com `DataTable` em modo lazy (paginação, ordenação e filtro no servidor).
- Acessibilidade: labels em todos os campos, foco visível, navegação por teclado.

### 4.4 Comandos

```bash
pnpm install
pnpm dev          # http://localhost:5173, proxy /api → http://localhost:8080 (mesma origem, cookie funciona)
pnpm build        # vue-tsc + vite build
pnpm test         # Vitest
pnpm test:e2e     # Playwright
pnpm lint && pnpm format
pnpm gen:api      # regenera src/shared/api/schema.d.ts a partir do OpenAPI do backend
```

---

## 5. Segurança (OWASP Top 10:2025 + ASVS 5.0 nível 2)

| Risco | Medidas neste projeto |
|-------|-----------------------|
| A01 Broken Access Control | Guards e menus são só UX; nunca assumir que esconder um botão protege algo. Não montar URLs de API com dados não validados. |
| A02 Security Misconfiguration | Sem source maps públicos em prod; sem `console.log` de dados; variáveis `VITE_*` nunca contêm segredo (tudo nelas é público); Nginx com headers abaixo. |
| A03 Software Supply Chain Failures | Lockfile; `pnpm audit` no CI (falha em high/critical); Dependabot/Renovate; nada de script de CDN externo; avaliar toda dependência nova (manutenção, downloads, permissões de install). |
| A04 Cryptographic Failures | Nada sensível em storage do navegador; só HTTPS. |
| A05 Injection / XSS | Nunca `v-html` com dado do usuário (se inevitável, DOMPurify); nada de `eval`/`new Function`; não interpolar dados em `href`/`src` sem validar protocolo; CSP estrita sem `unsafe-inline`/`unsafe-eval`. |
| A07 Authentication Failures | Login sem revelar se o usuário existe; limpar estado (stores, cache de queries) no logout; tratar 401 redirecionando ao login. |
| A08 Data Integrity | Requisições que alteram estado enviam `X-XSRF-TOKEN` (lido do cookie `XSRF-TOKEN`); `fetch` com `credentials: 'same-origin'`; tratar 409 (conflito de versão) pedindo para recarregar. |
| A10 Exceptional Conditions | Tratamento central de erros HTTP e de rede; mensagens genéricas ao usuário, sem exibir detalhes técnicos. |

Headers servidos pelo Nginx:

```
Strict-Transport-Security: max-age=63072000; includeSubDomains
Content-Security-Policy: default-src 'self'; script-src 'self'; style-src 'self'; img-src 'self' data:;
                         connect-src 'self'; frame-ancestors 'none'; base-uri 'self'; form-action 'self'
X-Content-Type-Options: nosniff
Referrer-Policy: strict-origin-when-cross-origin
Permissions-Policy: camera=(), microphone=(), geolocation=()
Cross-Origin-Opener-Policy: same-origin
```

LGPD: dados de contatos só exibidos a quem o backend devolve; não persistir em storage; não enviar para
ferramentas de analytics/telemetria.

### Checklist de PR

- [ ] Nenhum `v-html`, `eval` ou URL montada com dado não validado.
- [ ] Nada sensível em storage do navegador ou em variáveis `VITE_*`.
- [ ] Erros do backend tratados (400/401/403/404/409/422/5xx).
- [ ] Dependência nova justificada e sem vulnerabilidade high/critical.
- [ ] Tipos da API regenerados se o contrato mudou.

---

## 6. Infraestrutura e CI

- Dockerfile multi-stage: build com Node LTS + pnpm; runtime `nginx-unprivileged` com fallback de SPA para
  `index.html`, cache longo para assets com hash e `no-cache` para `index.html`.
- GitHub Actions: install com lockfile, lint, type-check, testes, `pnpm audit`, build, E2E (contra backend em
  container ou MSW), build da imagem e scan (Trivy). Merge só com CI verde.

## 7. Definição de pronto

1. Tela funcional com estados de carregando, vazio e erro.
2. Testes de componente/composable; E2E para fluxos críticos (login, criar e aprovar pedido).
3. Tipos da API atualizados.
4. Lint, type-check e testes verdes no CI; checklist de segurança revisado.
