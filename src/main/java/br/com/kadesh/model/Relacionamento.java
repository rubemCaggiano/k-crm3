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
public class Relacionamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @OneToOne
    private Cliente cliente;
    
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @OneToOne
    private TipoRelacionamento tipoRelacionamento;
    
    private String observacao;

    public Relacionamento() {
    }

    public Relacionamento(int id, Cliente cliente, Date data, TipoRelacionamento tipoRelacionamento, String observacao) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.tipoRelacionamento = tipoRelacionamento;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public TipoRelacionamento getTipoRelacionamento() {
        return tipoRelacionamento;
    }

    public void setTipoRelacionamento(TipoRelacionamento tipoRelacionamento) {
        this.tipoRelacionamento = tipoRelacionamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
