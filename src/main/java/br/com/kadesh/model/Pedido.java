package br.com.kadesh.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Transportadora transportadora;
    @ManyToOne
    private Endereco enderecoEntrega;
    @ManyToOne
    private CondicaoPagamento condicaoPagamento;
    @ManyToOne
    private TipoPedido tipoPedido;

    private String numeroOrdemCompra;
    private String observacoes;
    private SituacaoEnum situacao;

    private double valorTotal;
    private int quantidade;

    @Temporal(TemporalType.DATE)
    private Date dataCriacao;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido;

    public Pedido() {
    }

    public Pedido(int id, Cliente cliente, Transportadora transportadora, Endereco enderecoEntrega, CondicaoPagamento condicaoPagamento, TipoPedido tipoPedido, String numeroOrdemCompra, String observacoes, SituacaoEnum situacao, double valorTotal, int quantidade, Date dataCriacao, List<ItemPedido> itensPedido) {
        this.id = id;
        this.cliente = cliente;
        this.transportadora = transportadora;
        this.enderecoEntrega = enderecoEntrega;
        this.condicaoPagamento = condicaoPagamento;
        this.tipoPedido = tipoPedido;
        this.numeroOrdemCompra = numeroOrdemCompra;
        this.observacoes = observacoes;
        this.situacao = situacao;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
        this.dataCriacao = dataCriacao;
        this.itensPedido = itensPedido;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
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

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public CondicaoPagamento getCondicaoPagamento() {
        return condicaoPagamento;
    }

    public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
        this.condicaoPagamento = condicaoPagamento;
    }

    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(TipoPedido tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public String getNumeroOrdemCompra() {
        return numeroOrdemCompra;
    }

    public void setNumeroOrdemCompra(String numeroOrdemCompra) {
        this.numeroOrdemCompra = numeroOrdemCompra;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return this.cliente.getCnpj();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

}
