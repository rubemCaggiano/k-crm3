package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.UsuarioDao;
import br.com.kadesh.model.Usuario;
import br.com.kadesh.util.SessionContext;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

    private UsuarioDao usuarioDao = new UsuarioDao();
    private Usuario usuario;
    private List<Usuario> usuarios;

    public LoginMB() {
        usuario = new Usuario();
    }

    @PostConstruct
    public void selectAll() {
        usuarios = usuarioDao.findAll();
    }

    public String envia() {

        usuario = usuarioDao.fazerLogin(usuario);
        if (usuario != null) {
            usuario = new Usuario();

            SessionContext.getInstance().setAttribute("nome", usuario.getNome());

            return "adicionarClienteGUI.xhtml";
        } else {
            return "login.xhtml";
        }

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

}
