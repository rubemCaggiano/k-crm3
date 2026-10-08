# K-CRM legado (k-crm3) — repositório somente para consulta

Este repositório contém o **K-CRM legado** (JSF 2.2 + BootsFaces + Hibernate 5 + MySQL, Java 7), CRM de vendas
da Kadesh. O sistema está sendo reescrito como **R-CRM**, em dois repositórios novos:

| Projeto             | Repositório      | Stack                                            |
|---------------------|------------------|--------------------------------------------------|
| Backend (API REST)  | `r-crm-backend`  | Java 25 + Spring Boot 4.1.x + PostgreSQL         |
| Frontend (SPA)      | `r-crm-frontend` | Vue 3 + TypeScript + Vite + Pinia + Vuetify      |

## Regras para trabalhar aqui

- **Não alterar o código legado** (`src/`, `pom.xml`, `target/`). Ele não será mais evoluído, compilado nem
  implantado. Só são aceitas mudanças em documentação (`CLAUDE.md`, `docs/`).
- Use este repositório para **entender regras de negócio e telas**. Não copie código, padrões nem
  configurações daqui para os projetos novos: o legado tem falhas de segurança e de design conhecidas
  (lista abaixo).
- As definições dos projetos novos (arquitetura, convenções, segurança) estão no `CLAUDE.md` de cada repositório
  novo. Mudanças nessas definições são feitas lá, não aqui.
- Commits: um commit por alteração relevante de documentação, em Conventional Commits (`docs: ...`), como definido na
  seção 2.1 do `CLAUDE.md` dos repositórios novos; branches e pull requests seguem a seção 2.2 de lá.

## Onde encontrar cada coisa no legado

| O que                         | Onde                                                          |
|-------------------------------|---------------------------------------------------------------|
| Entidades e enums             | `src/main/java/br/com/kadesh/model/`                          |
| Regras de negócio e telas     | `src/main/java/br/com/kadesh/mb/` (managed beans JSF)         |
| Fluxo de aprovação de pedido  | `mb/PedidoDetalheMB.java` (`finalizar`, `aprovar`, `devolver...`, `enviarProducao`, `cancelar`) |
| Cálculos de item/pedido e MC  | `mb/PedidoMB.java` (`adicionarItem`, `calcularMc`), `model/ItemPedido.java`, `model/Produto.java` |
| Acesso a dados                | `src/main/java/br/com/kadesh/dao/impl/`                       |
| Login e sessão                | `mb/LoginMB.java`, `util/PageFilter.java`, `util/SessionContext.java`, `util/Digest.java` |
| Menus por perfil              | `src/main/webapp/Template/template.xhtml`                     |
| Telas                         | `src/main/webapp/Telas/*.xhtml`, `src/main/webapp/login.xhtml`|
| Configuração do banco         | `src/main/resources/hibernate.cfg.xml`                        |

`target/` são artefatos de build commitados por engano; ignore-os.

## Resumo do domínio

- **Módulos:** usuários, clientes (contatos, endereço), relacionamento (relacionamentos e agendamentos),
  catálogo (produtos com grade de numeração, grupos, famílias, linhas), logística (transportadoras e estados
  atendidos), pedidos (itens com grade, condição de pagamento, tipo de pedido).
- **Perfis:** `ADMIN`, `VENDEDOR`, `SUPERVISOR`, `FINANCEIRO`, `LOGISTICA`, `CUSTO`.
- **Fluxo do pedido no legado:** `ABERTO → FINALIZADO` (vendedor) `→ SUPERVISOR` (aprovado pelo supervisor)
  `→ FINANCEIRO` (aprovado pelo financeiro / enviado à produção); `DEVOLVIDO` pelo supervisor ou financeiro;
  `CANCELADO` pelo vendedor.
- **Cálculos:** `precoSugerido = custo × 1,20`; `precoMinimo = custo × (1 + mcMinima/100)`;
  `quantidade do item = Σ grade`; `valorItens = quantidade × preco`; `MC% = (preco − custo)/preco × 100`;
  totais do pedido = soma dos itens.

A especificação completa (matriz de permissões, máquina de estados corrigida e pontos em aberto) está no
`CLAUDE.md` do repositório `r-crm-backend`.

## Problemas conhecidos do legado (não reproduzir)

1. Senhas em SHA-256 sem salt (`util/Digest.java`); usuário e senha do banco no código (`hibernate.cfg.xml`).
2. Autorização só no menu (`rendered` no template); `PageFilter` só verifica se há sessão — qualquer usuário
   logado acessa qualquer tela.
3. Escopo do vendedor (só os próprios clientes/pedidos) aplicado só na tela.
4. Situação do pedido gravada sem validar a transição.
5. `limiteDisponivel` do cliente existe mas nunca é usado; preço mínimo não é validado.
6. Valores monetários em `double`.
7. `hbm2ddl.auto=update`, `show_sql=true`, `PROJECT_STAGE=Development`, `printStackTrace`.
8. Fetch `EAGER` em cascata (vendedor carrega todos os clientes, pedidos e agendamentos) e `cascade = ALL`
   indiscriminado.
9. `Relacionamento` modelado como `@OneToOne` com cliente; `equals` sem checagem de tipo/nulo em várias
   entidades; `Vendedor.equals` baseado na lista de clientes.
