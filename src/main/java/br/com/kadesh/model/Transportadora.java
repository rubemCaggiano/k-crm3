package br.com.kadesh.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Transportadora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String razaoSocial;
    private String cnpj;
    private String nomeFantasia;

    @OneToMany
    private List<Regiao> regioesAtendidas;

    private boolean status;

    public Transportadora() {
    }

    public Transportadora(int id, String razaoSocial, String cnpj, String nomeFantasia, List<Regiao> regioesAtendidas, boolean status) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.regioesAtendidas = regioesAtendidas;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public List<Regiao> getRegioesAtendidas() {
        return regioesAtendidas;
    }

    public void setRegioesAtendidas(List<Regiao> regioesAtendidas) {
        this.regioesAtendidas = regioesAtendidas;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nomeFantasia;
    }

    @Override
    public boolean equals(Object obj) {
        Transportadora t = (Transportadora) obj;
        return this.id == t.getId();
    }
}
