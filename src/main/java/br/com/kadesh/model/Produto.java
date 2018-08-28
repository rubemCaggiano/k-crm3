package br.com.kadesh.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String descricao;
    private String referencia;
    private String numeroCa;
    private double custo;
    private double mcMinima;
    private boolean status;

    @Column(columnDefinition = "Decimal(10,2)")
    private double precoSugerido;
    @Column(columnDefinition = "Decimal(10,2)")
    private double precoMinimo;

    @ManyToOne
    private Grupo grupo;

    @ManyToOne
    private Familia familia;

    @ManyToOne
    private Linha linha;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private List<ProdutoGrade> numeracao;

    public Produto() {
    }

    public Produto(int id, String descricao, String referencia, String numeroCa, double custo, double mcMinima, boolean status, double precoSugerido, double precoMinimo, Grupo grupo, Familia familia, Linha linha, List<ProdutoGrade> numeracao) {
        this.id = id;
        this.descricao = descricao;
        this.referencia = referencia;
        this.numeroCa = numeroCa;
        this.custo = custo;
        this.mcMinima = mcMinima;
        this.status = status;
        this.precoSugerido = precoSugerido;
        this.precoMinimo = precoMinimo;
        this.grupo = grupo;
        this.familia = familia;
        this.linha = linha;
        this.numeracao = numeracao;
    }

    public Produto(int id, String descricao, String referencia, String numeroCa, double custo, double mcMinima, boolean status, double precoSugerido, double precoMinimo, Grupo grupo, Familia familia, Linha linha) {
        this.id = id;
        this.descricao = descricao;
        this.referencia = referencia;
        this.numeroCa = numeroCa;
        this.custo = custo;
        this.mcMinima = mcMinima;
        this.status = status;
        this.precoSugerido = precoSugerido;
        this.precoMinimo = precoMinimo;
        this.grupo = grupo;
        this.familia = familia;
        this.linha = linha;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNumeroCa() {
        return numeroCa;
    }

    public void setNumeroCa(String numeroCa) {
        this.numeroCa = numeroCa;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
        setPrecoSugerido(this.custo + (this.custo * 0.20));
    }

    public double getMcMinima() {
        return mcMinima;
    }

    public void setMcMinima(double mcMinima) {
        this.mcMinima = mcMinima;
        setPrecoMinimo(custo + (custo * (this.mcMinima / 100)));
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public double getPrecoSugerido() {
        return precoSugerido;
    }

    public void setPrecoSugerido(double precoSugerido) {
        this.precoSugerido = precoSugerido;
    }

    public double getPrecoMinimo() {
        return precoMinimo;
    }

    public void setPrecoMinimo(double precoMinimo) {
        this.precoMinimo = precoMinimo;
    }

    public List<ProdutoGrade> getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(List<ProdutoGrade> numeracao) {
        this.numeracao = numeracao;
    }

    @Override
    public String toString() {
        return referencia;
    }
    @Override
    public boolean equals(Object obj) {
        Produto p = (Produto) obj;
        return this.id == p.getId();
    }

}
