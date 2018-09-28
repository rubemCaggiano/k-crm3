package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.SetorDao;
import br.com.kadesh.dao.impl.SupervisorDao;
import br.com.kadesh.dao.impl.UsuarioDao;
import br.com.kadesh.dao.impl.VendedorDao;
import br.com.kadesh.model.PermissaoEnum;
import br.com.kadesh.model.Setor;
import br.com.kadesh.model.Supervisor;
import br.com.kadesh.model.Usuario;
import br.com.kadesh.model.Vendedor;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable {

    private UsuarioDao usuarioDao = new UsuarioDao();
    private VendedorDao vendedorDao = new VendedorDao();
    private SupervisorDao supervisorDao = new SupervisorDao();
    private SetorDao setorDao = new SetorDao();

    private List<Usuario> usuarios;
    private List<Vendedor> vendedores;
    private List<Supervisor> supervisores;
    private List<Setor> setores;
    private List<PermissaoEnum> permissoes;

    private Usuario usuario;
    private Vendedor vendedor;
    private Supervisor supervisor;
    private Setor setor;
    private PermissaoEnum permissao;
    
    private String nome;
    private String email;
    private String usuarioUsuario;
    private String senha;

    public UsuarioMB() {
        usuario = new Usuario();
        vendedor = new Vendedor();
        setor = new Setor();

    }

    public void salvar() {

        usuario.setPermissao(permissao);
        if (permissao == PermissaoEnum.VENDEDOR) {
            vendedor.setNome(nome);
            vendedor.setEmail(email);
            vendedor.setUsuario(usuarioUsuario);
            vendedor.setSenha(senha);
            vendedor.setPermissao(permissao);
            vendedor.setStatus(true);
            vendedorDao.create(vendedor);
        } else if (permissao == PermissaoEnum.SUPERVISOR) {
            supervisor.setNome(nome);
            supervisor.setEmail(email);
            supervisor.setUsuario(usuarioUsuario);
            supervisor.setSenha(senha);
            supervisor.setPermissao(permissao);
            supervisor.setStatus(true);
            supervisorDao.create(supervisor);
        } else {
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setUsuario(usuarioUsuario);
            usuario.setSenha(senha);
            usuario.setPermissao(permissao);
            usuario.setStatus(true);
            usuarioDao.create(usuario);
        }
        usuario = new Usuario();
        selectAll();
    }
    
    

    @PostConstruct
    public void selectAll() {
        usuarios = usuarioDao.findAll();
        vendedores = vendedorDao.findAll();
        setores = setorDao.findAll();
        permissoes = Arrays.asList(PermissaoEnum.values());

    }

    public void detalharUsuario(Usuario u) {

    }
    
    public void excluirUsuario(Usuario u){
        usuarioDao.delete(u);
        selectAll();
    }

    public boolean renderizar() {
        if (setor.getNome().equals("Vendas")) {
            return true;
        } else {
            return false;
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

    public SetorDao getSetorDao() {
        return setorDao;
    }

    public void setSetorDao(SetorDao setorDao) {
        this.setorDao = setorDao;
    }

    public List<Setor> getSetores() {
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
