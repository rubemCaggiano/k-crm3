package br.com.kadesh.model;

public enum StatusEnumAgendamento {
    PENDENTE("Pendente"),
    REALIZADO("Realizado");
    
    private String status;

    private StatusEnumAgendamento() {
    }

    private StatusEnumAgendamento(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
