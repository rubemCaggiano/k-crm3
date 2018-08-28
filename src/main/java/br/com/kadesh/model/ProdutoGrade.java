package br.com.kadesh.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProdutoGrade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int numeracao;
    private String codBarras;

    public ProdutoGrade() {
    }

    public ProdutoGrade(int id, int numeracao, String codBarras) {
        this.id = id;
        this.numeracao = numeracao;
        this.codBarras = codBarras;
    }

    public ProdutoGrade(int id, int numeracao) {
        this.id = id;
        this.numeracao = numeracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(int numeracao) {
        this.numeracao = numeracao;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

}
