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
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private int numeroFuncionarios;
    private String ramoAtividade;
    private String segmento;
    private String emailNFE;
    private double limite;
    private double limiteDisponivel;

    @OneToMany
    private List<Contato> contatos;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(int id, String cnpj, String razaoSocial, String nomeFantasia, String inscricaoEstadual, int numeroFuncionarios, String ramoAtividade, String segmento, String emailNFE, double limite, double limiteDisponivel, List<Contato> contatos, Endereco endereco) {
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.inscricaoEstadual = inscricaoEstadual;
        this.numeroFuncionarios = numeroFuncionarios;
        this.ramoAtividade = ramoAtividade;
        this.segmento = segmento;
        this.emailNFE = emailNFE;
        this.limite = limite;
        this.limiteDisponivel = limiteDisponivel;
        this.contatos = contatos;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public int getNumeroFuncionarios() {
        return numeroFuncionarios;
    }

    public void setNumeroFuncionarios(int numeroFuncionarios) {
        this.numeroFuncionarios = numeroFuncionarios;
    }

    public String getRamoAtividade() {
        return ramoAtividade;
    }

    public void setRamoAtividade(String ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getEmailNFE() {
        return emailNFE;
    }

    public void setEmailNFE(String emailNFE) {
        this.emailNFE = emailNFE;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public void setLimiteDisponivel(double limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }

    @Override
    public String toString() {
        return razaoSocial;
    }

    @Override
    public boolean equals(Object obj) {
        Cliente c = (Cliente) obj;
        return this.id == c.getId();
    }

}
