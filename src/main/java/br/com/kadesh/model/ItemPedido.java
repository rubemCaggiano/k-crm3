package br.com.kadesh.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ItemPedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<GradeVenda> produtos;

    @ManyToOne
    private Produto produto;

    private int quantidade;
    private double preco;
    private double valorItens;
    private double frete;
    private double mc;

    public ItemPedido() {
    }

    public ItemPedido(int id, List<GradeVenda> produtos, Produto produto, int quantidade, double preco, double valorItens, double frete) {
        this.id = id;
        this.produtos = produtos;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.valorItens = valorItens;
        this.frete = frete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        setValorItens(this.quantidade * preco);
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public List<GradeVenda> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<GradeVenda> produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getValorItens() {
        return valorItens;
    }

    public void setValorItens(double valorItens) {
        this.valorItens = valorItens;
    }

    public double getMc() {
        return mc;
    }

    public void setMc(double mc) {
        this.mc = mc;
    }

}
