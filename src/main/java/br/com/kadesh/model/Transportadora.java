package br.com.kadesh.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Subselect;

@Entity
@Proxy(lazy = true)
public class Transportadora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String razaoSocial;
    private String cnpj;
    private String nomeFantasia;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Estado> estadosAtendidos;

    private boolean status;

    public Transportadora() {
    }

    public Transportadora(int id, String razaoSocial, String cnpj, String nomeFantasia, List<Estado> estadosAtendidos, boolean status) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.estadosAtendidos = estadosAtendidos;
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

    public List<Estado> getEstadosAtendidos() {
        return estadosAtendidos;
    }

    public void setEstadosAtendidos(List<Estado> estadosAtendidos) {
        this.estadosAtendidos = estadosAtendidos;
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
