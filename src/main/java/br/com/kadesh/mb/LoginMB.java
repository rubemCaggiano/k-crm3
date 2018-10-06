package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.UsuarioDao;
import br.com.kadesh.model.Usuario;
import br.com.kadesh.util.SessionContext;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

    private UsuarioDao usuarioDao = new UsuarioDao();
    private Usuario usuario;
    private Usuario usuarioLogado;
    private List<Usuario> usuarios;

    public LoginMB() {
        usuario = new Usuario();
        usuarioLogado = new Usuario();
    }

    public Usuario getUser() {
        return (Usuario) SessionContext.getInstance().getUsuarioLogado();

    }

    @PostConstruct
    public void carregarUsuario() {
        usuarioLogado = getUser();
    }

    public String doLogin() {
        try {
            usuario = usuarioDao.fazerLogin(usuario);
//           User user = userBO.isUsuarioReadyToLogin(login, senha);

            if (usuario == null) {
//             addErrorMessage("Login ou Senha errado, tente novamente !");
                FacesContext.getCurrentInstance().validationFailed();
                return "login.xhtml";
            }
            SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
            return "/Telas/inicial.xhtml";

        } catch (Exception e) {
            FacesContext.getCurrentInstance().validationFailed();
            e.printStackTrace();
            return "";
        }

    }

    public void doLogout() {
        SessionContext.getInstance().encerrarSessao();
    }
//    @PostConstruct
//    public void selectAll() {
//        usuarios = usuarioDao.findAll();
//    }
//
//    public String envia() {
//
//        usuario = usuarioDao.fazerLogin(usuario);
//        if (usuario != null) {
//            usuario = new Usuario();
//
//            SessionContext.getInstance().setAttribute("nome", usuario.getNome());
//
//            return "adicionarClienteGUI.xhtml";
//        } else {
//            return "login.xhtml";
//        }
//
//    }

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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

}
