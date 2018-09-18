package br.com.kadesh.model;

public enum SituacaoEnum {

    ABERTO("Aberto"),
    FINALIZADO("Finalizado"),
    SUPERVISOR("Aprovado Supervisor"),
    FINANCEIRO("Aprovado Financeiro");

    private String situacao;

    private SituacaoEnum(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public static SituacaoEnum getABERTO() {
        return ABERTO;
    }

    public static SituacaoEnum getFINALIZADO() {
        return FINALIZADO;
    }

    public static SituacaoEnum getSUPERVISOR() {
        return SUPERVISOR;
    }

    public static SituacaoEnum getFINANCEIRO() {
        return FINANCEIRO;
    }

}
