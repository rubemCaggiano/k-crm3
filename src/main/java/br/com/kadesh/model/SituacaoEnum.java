
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

}
