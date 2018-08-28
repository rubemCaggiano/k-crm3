package br.com.kadesh.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Agendamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Relacionamento relacionamento;

    @Temporal(TemporalType.DATE)
    private Date dataAgendada;

    private String observacao;

    public Agendamento() {
    }

    public Agendamento(int id, Relacionamento relacionamento, Date dataAgendada, String observacao) {
        this.id = id;
        this.relacionamento = relacionamento;
        this.dataAgendada = dataAgendada;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Relacionamento getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(Relacionamento relacionamento) {
        this.relacionamento = relacionamento;
    }

    public Date getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(Date dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

}
