package br.com.kadesh.model;

public enum PermissaoEnum {

    VENDEDOR("Vendedor"),
    SUPERVISOR("Supervisor"),
    LOGISTICA("Logistica"),
    FINANCEIRO("Financeiro"),
    ADMIN("Administrador");

    private String permissao;

    private PermissaoEnum() {
    }

    private PermissaoEnum(String permissao) {
        this.permissao = permissao;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

}
