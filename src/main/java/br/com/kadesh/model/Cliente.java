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
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
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
    private StatusEnum status;

    @ManyToOne
    private Vendedor vendedor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Contato> contatos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Relacionamento> relacionamentos;

    @OneToOne(cascade = {CascadeType.ALL})
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(int id, String cnpj, String razaoSocial, String nomeFantasia, String inscricaoEstadual, int numeroFuncionarios, String ramoAtividade, String segmento, String emailNFE, double limite, double limiteDisponivel, Vendedor vendedor, List<Contato> contatos, List<Relacionamento> relacionamentos, Endereco endereco) {
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
        this.vendedor = vendedor;
        this.contatos = contatos;
        this.relacionamentos = relacionamentos;
        this.endereco = endereco;
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

    public Cliente(int id, String cnpj, String razaoSocial, String nomeFantasia, String inscricaoEstadual, int numeroFuncionarios, String ramoAtividade, String segmento, String emailNFE, double limite, double limiteDisponivel, StatusEnum status, Vendedor vendedor, List<Contato> contatos, List<Relacionamento> relacionamentos, Endereco endereco) {
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
        this.status = status;
        this.vendedor = vendedor;
        this.contatos = contatos;
        this.relacionamentos = relacionamentos;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
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

//    @Override
//    public boolean equals(Object obj) {
//        Cliente c = (Cliente) obj;
//        return this.id == c.getId();
//    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        return hash;
    }

    public List<Relacionamento> getRelacionamentos() {
        return relacionamentos;
    }

    public void setRelacionamentos(List<Relacionamento> relacionamentos) {
        this.relacionamentos = relacionamentos;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

}
