package br.com.kadesh.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String email;

    @Column(unique = true)
    private String usuario;
    private String senha;

    private StatusEnum status;
    private PermissaoEnum permissao;

    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String usuario, String senha, StatusEnum status, PermissaoEnum permissao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
        this.status = status;
        this.permissao = permissao;
    }

    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public PermissaoEnum getPermissao() {
        return permissao;
    }

    public void setPermissao(PermissaoEnum permissao) {
        this.permissao = permissao;
    }

//    @Override
//    public boolean equals(Object obj) {
//        Usuario u = (Usuario) obj;
//        return this.id == u.getId();
//    }
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        return hash;
    }

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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
