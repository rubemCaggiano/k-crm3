package br.com.kadesh.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vendedor extends Usuario {

    @ManyToOne
    private TipoVendedor tipoVendedor;
    private String cnpj;
    private String nomeFantasia;
    private String telefone;

    @OneToMany
    private List<Cliente> clientes;

    public Vendedor() {

    }

    public Vendedor(TipoVendedor tipoVendedor, String cnpj, String nomeFantasia, String telefone, List<Cliente> clientes) {
        this.tipoVendedor = tipoVendedor;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.telefone = telefone;
        this.clientes = clientes;
    }

    public Vendedor(TipoVendedor tipoVendedor, String cnpj, String nomeFantasia, String telefone, List<Cliente> clientes, int id, String nome, String email, Setor setor, String senha, boolean status) {
        super(id, nome, email, setor, senha, status);
        this.tipoVendedor = tipoVendedor;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.telefone = telefone;
        this.clientes = clientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public TipoVendedor getTipoVendedor() {
        return tipoVendedor;
    }

    public void setTipoVendedor(TipoVendedor tipoVendedor) {
        this.tipoVendedor = tipoVendedor;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
