package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.SetorDao;
import br.com.kadesh.dao.impl.UsuarioDao;
import br.com.kadesh.dao.impl.VendedorDao;
import br.com.kadesh.model.Setor;
import br.com.kadesh.model.Usuario;
import br.com.kadesh.model.Vendedor;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class UsuarioMB implements Serializable{
    
    private UsuarioDao usuarioDao = new UsuarioDao();
    private VendedorDao vendedorDao = new VendedorDao();
    private SetorDao setorDao = new SetorDao();
    
    private List<Usuario> usuarios;
    private List<Vendedor> vendedores;
    private List<Setor> setores;
    
    private Usuario usuario;
    private Vendedor vendedor;
    private Setor setor;

    public UsuarioMB() {
        usuario = new Usuario();
        vendedor = new Vendedor();
        setor = new Setor();
    }
    
    
    public void salvar(){
        System.out.println("Usuário Salvo");
        
        usuarioDao.saveOrUpdate(usuario);
    }
    
    @PostConstruct
    public void selectAll(){
        usuarios = usuarioDao.findAll();
        vendedores = vendedorDao.findAll();
        setores = setorDao.findAll();
                
    }
    
    public boolean renderizar(){
        if(setor.getNome().equals("Vendas")){
            return true;
        } else{
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
    
}
