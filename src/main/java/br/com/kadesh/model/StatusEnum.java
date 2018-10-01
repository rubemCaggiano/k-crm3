package br.com.kadesh.model;

public enum StatusEnum {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String status;

    private StatusEnum() {
    }

    private StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
