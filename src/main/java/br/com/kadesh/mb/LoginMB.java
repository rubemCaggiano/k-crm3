package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.UsuarioDao;
import br.com.kadesh.model.Setor;
import br.com.kadesh.model.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

    private UsuarioDao usuarioDao = new UsuarioDao();

    private Usuario usuario;
    private Setor setor;

    public LoginMB() {
        setor = new Setor(0, "Vendas");
        usuario = new Usuario(0, "Rubem", "rubem@kadesh.com", setor, "1234", true);

    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

}
