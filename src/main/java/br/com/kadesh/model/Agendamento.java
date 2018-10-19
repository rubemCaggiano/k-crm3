package br.com.kadesh.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = true)
public class Agendamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;
    
    @ManyToOne
    private Vendedor vendedor;
   
    @Temporal(TemporalType.DATE)
    private Date dataAgendada;
    
    @Temporal(TemporalType.DATE)
    private Date dataRealizacao;
    
    private String observacao;
    
    private StatusEnumAgendamento status;

    public Agendamento() {
    }

    public Agendamento(int id, Cliente cliente, Vendedor vendedor, Date dataAgendada, Date dataRealizacao, String observacao, StatusEnumAgendamento status) {
        this.id             = id;
        this.cliente        = cliente;
        this.vendedor       = vendedor;
        this.dataAgendada   = dataAgendada;
        this.dataRealizacao = dataRealizacao;
        this.observacao     = observacao;
        this.status         = status;
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

    public Date getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(Date dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public StatusEnumAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusEnumAgendamento status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    } 

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    @Override
    public boolean equals(Object obj) {
        Agendamento a = (Agendamento) obj;
        return this.id == a.getId();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
        return hash;
    }
     
}
