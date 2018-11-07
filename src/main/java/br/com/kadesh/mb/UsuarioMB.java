package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.SupervisorDao;
import br.com.kadesh.dao.impl.UsuarioDao;
import br.com.kadesh.dao.impl.VendedorDao;
import br.com.kadesh.model.PermissaoEnum;
import br.com.kadesh.model.StatusEnum;
import br.com.kadesh.model.Supervisor;
import br.com.kadesh.model.Usuario;
import br.com.kadesh.model.Vendedor;
import br.com.kadesh.util.Digest;
import br.com.kadesh.util.HashGenerationException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

@ManagedBean
@ViewScoped
public class UsuarioMB {

    private UsuarioDao usuarioDao = new UsuarioDao();
    private VendedorDao vendedorDao = new VendedorDao();
    private SupervisorDao supervisorDao = new SupervisorDao();

    private List<Usuario> usuarios;
    private List<Vendedor> vendedores;
    private List<Supervisor> supervisores;
    private List<PermissaoEnum> permissoes;
    private List<Vendedor> vendedoresSelecionados = new ArrayList<>();

    private Usuario usuario;
    private Vendedor vendedor;
    private Supervisor supervisor;
    private PermissaoEnum permissao;
    private boolean mostrar;
    private boolean mostrarEquipe;

    private String nome;
    private String email;
    private String usuarioUsuario;
    private String senhaPlana;

    public UsuarioMB() {
        selectAll();
        usuario = new Usuario();
        vendedor = new Vendedor();
        supervisor = new Supervisor();
        mostrar = false;
        mostrarEquipe = false;

    }

    public void mostrarCadUsuario() {
        mostrar = true;
        nome = "";
        email = "";
        usuarioUsuario = "";
        senhaPlana = "";

        usuario = new Usuario();
        vendedor = new Vendedor();
        supervisor = new Supervisor();
    }

    public void ocultarCadUsuario() {
        mostrar = false;
    }

    public void mostrarEquipe(Usuario u) {
        supervisor = (Supervisor) u;
        mostrarEquipe = true;
    }

    public void adicionarVendedor() {
        vendedor.setSupervisor(supervisor);
        vendedoresSelecionados.add(vendedor);
        vendedor = new Vendedor();
    }

    public void removerVendedor(Vendedor v) {
        vendedoresSelecionados.remove(v);
    }

    public void vincularEquipe() {
        supervisor.setVendedores(vendedoresSelecionados);
        supervisorDao.saveOrUpdate(supervisor);
        vendedoresSelecionados = new ArrayList<>();
        selectAll();
    }

    public void salvar() {
        try {
            usuario.setPermissao(permissao);
            if (permissao == PermissaoEnum.VENDEDOR) {
                vendedor.setNome(nome);
                vendedor.setEmail(email);
                vendedor.setUsuario(usuarioUsuario);
                try {
                    vendedor.setSenha(Digest.hashString(senhaPlana, "SHA-256"));
                } catch (HashGenerationException ex) {
                    Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
                }
                vendedor.setPermissao(permissao);
                vendedor.setStatus(StatusEnum.ATIVO);
                vendedorDao.saveOrUpdate(vendedor);
            } else if (permissao == PermissaoEnum.SUPERVISOR) {
                supervisor = new Supervisor();
                supervisor.setNome(nome);
                supervisor.setEmail(email);
                supervisor.setUsuario(usuarioUsuario);
                try {
                    supervisor.setSenha(Digest.hashString(senhaPlana, "SHA-256"));
                } catch (HashGenerationException ex) {
                    Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
                }
                supervisor.setPermissao(permissao);
                supervisor.setStatus(StatusEnum.ATIVO);
                supervisorDao.saveOrUpdate(supervisor);
            } else {
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setUsuario(usuarioUsuario);
                try {
                    usuario.setSenha(Digest.hashString(senhaPlana, "SHA-256"));
                } catch (HashGenerationException ex) {
                    Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
                }
                usuario.setPermissao(permissao);
                usuario.setStatus(StatusEnum.ATIVO);
                usuarioDao.saveOrUpdate(usuario);
            }
            nome = "";
            email = "";
            usuarioUsuario = "";
            senhaPlana = "";

            usuario = new Usuario();
            selectAll();
            mostrar = false;
            Messages.addGlobalInfo("Usuario cadastrado com Sucesso");
        } catch (Exception e) {
            Messages.addGlobalError("Falha ao cadastrar Usuario");
        }

    }

    public void selectAll() {
        usuarios = usuarioDao.findAll();
        vendedores = vendedorDao.findAll();
        supervisores = supervisorDao.findAll();
        permissoes = Arrays.asList(PermissaoEnum.values());

    }

    public void detalharUsuario(Usuario usuario) {

        this.usuario = usuarioDao.find(usuario.getId());
        if (usuario.getPermissao() == PermissaoEnum.VENDEDOR) {
            vendedor = (Vendedor) usuario;
        }

        nome = usuario.getNome();
        email = usuario.getEmail();
        usuarioUsuario = usuario.getUsuario();
        senhaPlana = usuario.getSenha();
        permissao = usuario.getPermissao();
        mostrar = true;
    }

    public void excluirUsuario(Usuario u) {
        try {
            usuarioDao.delete(u);
            selectAll();
        } catch (javax.persistence.PersistenceException ex) {
            Messages.addGlobalError("Falha ao excluir, existem vinculos com esse usuario");
        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir Usuario");
        }

    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public VendedorDao getVendedorDao() {
        return vendedorDao;
    }

    public void setVendedorDao(VendedorDao vendedorDao) {
        this.vendedorDao = vendedorDao;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<PermissaoEnum> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<PermissaoEnum> permissoes) {
        this.permissoes = permissoes;
    }

    public PermissaoEnum getPermissao() {
        return permissao;
    }

    public void setPermissao(PermissaoEnum permissao) {
        this.permissao = permissao;
    }

    public SupervisorDao getSupervisorDao() {
        return supervisorDao;
    }

    public void setSupervisorDao(SupervisorDao supervisorDao) {
        this.supervisorDao = supervisorDao;
    }

    public List<Supervisor> getSupervisores() {
        return supervisores;
    }

    public void setSupervisores(List<Supervisor> supervisores) {
        this.supervisores = supervisores;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
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

    public String getUsuarioUsuario() {
        return usuarioUsuario;
    }

    public void setUsuarioUsuario(String usuarioUsuario) {
        this.usuarioUsuario = usuarioUsuario;
    }

    public String getSenhaPlana() {
        return senhaPlana;
    }

    public void setSenhaPlana(String senhaPlana) {
        this.senhaPlana = senhaPlana;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public List<Vendedor> getVendedoresSelecionados() {
        return vendedoresSelecionados;
    }

    public void setVendedoresSelecionados(List<Vendedor> vendedoresSelecionados) {
        this.vendedoresSelecionados = vendedoresSelecionados;
    }

    public boolean isMostrarEquipe() {
        return mostrarEquipe;
    }

    public void setMostrarEquipe(boolean mostrarEquipe) {
        this.mostrarEquipe = mostrarEquipe;
    }

}
