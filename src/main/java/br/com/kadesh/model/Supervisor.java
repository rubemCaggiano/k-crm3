package br.com.kadesh.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Supervisor extends Usuario {

    @OneToMany
    private List<Vendedor> vendedores;

    public Supervisor() {
    }

    public Supervisor(List<Vendedor> vendedores, int id, String nome, String email, String usuario, String senha, boolean status, PermissaoEnum permissao) {
        super(id, nome, email, usuario, senha, status, permissao);
        this.vendedores = vendedores;
    }

    public Supervisor(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

}
