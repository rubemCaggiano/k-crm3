package br.com.kadesh.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Opcional implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descricao;
    private double custo;
    private double preco;
    private boolean status;

    public Opcional() {
    }

    public Opcional(int id, String descricao, double custo, double preco, boolean status) {
        this.id = id;
        this.descricao = descricao;
        this.custo = custo;
        this.preco = preco;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return descricao;
    }

    @Override
    public boolean equals(Object obj) {
        Opcional o = (Opcional) obj;
        return this.id == o.getId();
    }

}
