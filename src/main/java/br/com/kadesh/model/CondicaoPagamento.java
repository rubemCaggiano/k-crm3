package br.com.kadesh.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CondicaoPagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String condicao;
    private double indiceFinaceiro;

    public CondicaoPagamento() {
    }

    public CondicaoPagamento(int id, String condicao, double indiceFinaceiro) {
        this.id = id;
        this.condicao = condicao;
        this.indiceFinaceiro = indiceFinaceiro;
    }

    public double getIndiceFinaceiro() {
        return indiceFinaceiro;
    }

    public void setIndiceFinaceiro(double indiceFinaceiro) {
        this.indiceFinaceiro = indiceFinaceiro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    @Override
    public String toString() {
        return condicao;
    }

    @Override
    public boolean equals(Object obj) {
        CondicaoPagamento cp = (CondicaoPagamento) obj;
        return this.id == cp.getId();
    }

}
