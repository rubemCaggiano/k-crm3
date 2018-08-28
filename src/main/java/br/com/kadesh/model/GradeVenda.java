package br.com.kadesh.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class GradeVenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private ProdutoGrade grade;

    @ManyToOne
    private Produto produto;

    private int quantidade;

    public GradeVenda() {
    }

    public GradeVenda(int id, ProdutoGrade grade, Produto produto, int quantidade) {
        this.id = id;
        this.grade = grade;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProdutoGrade getGrade() {
        return grade;
    }

    public void setGrade(ProdutoGrade grade) {
        this.grade = grade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
