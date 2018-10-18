package br.com.kadesh.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = true)
public class Supervisor extends Usuario {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Vendedor> vendedores;

    public Supervisor() {

    }

    public Supervisor(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public Supervisor(List<Vendedor> vendedores, int id, String nome, String email, String usuario, String senha, StatusEnum status, PermissaoEnum permissao) {
        super(id, nome, email, usuario, senha, status, permissao);
        this.vendedores = vendedores;
    }

    public Supervisor(int id, String nome, String email, String usuario, String senha, StatusEnum status, PermissaoEnum permissao) {
        super(id, nome, email, usuario, senha, status, permissao);

    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

}
