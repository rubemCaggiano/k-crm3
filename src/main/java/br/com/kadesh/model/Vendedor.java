package br.com.kadesh.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Vendedor extends Usuario {

    @OneToMany
    private List<Cliente> clientes;

    public Vendedor() {

    }

    public Vendedor(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}
