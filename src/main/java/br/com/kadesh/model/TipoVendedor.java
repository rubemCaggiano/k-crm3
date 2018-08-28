package br.com.kadesh.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoVendedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String tipo;

    public TipoVendedor() {
    }

    public TipoVendedor(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return tipo;
    }

    @Override
    public boolean equals(Object obj) {
        TipoVendedor tv = (TipoVendedor) obj;
        return this.id == tv.getId();
    }

}
