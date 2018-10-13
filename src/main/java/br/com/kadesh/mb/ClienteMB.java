package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.ClienteDao;
import br.com.kadesh.model.Cliente;
import br.com.kadesh.dao.impl.ContatoDao;
import br.com.kadesh.model.Contato;
import br.com.kadesh.dao.impl.EnderecoDao;
import br.com.kadesh.model.Endereco;
import br.com.kadesh.dao.impl.EstadoDao;
import br.com.kadesh.dao.impl.UsuarioDao;
import br.com.kadesh.dao.impl.VendedorDao;
import br.com.kadesh.model.Estado;
import br.com.kadesh.model.PermissaoEnum;
import br.com.kadesh.model.Usuario;
import br.com.kadesh.model.Vendedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ClienteMB implements Serializable {

    @ManagedProperty("#{loginMB}")
    private LoginMB loginMB;

    ClienteDao clienteDao = new ClienteDao();
    ContatoDao contatoDao = new ContatoDao();
    EnderecoDao enderecoDao = new EnderecoDao();
    EstadoDao estadoDao = new EstadoDao();
    VendedorDao vendedorDao = new VendedorDao();
    private UsuarioDao usuarioDao = new UsuarioDao();

    List<Cliente> clientes;
    List<Contato> contatos = new ArrayList<>();
    List<Endereco> enderecos;
    List<Estado> estados;

    private Usuario usuario;
    private Vendedor vendedor;
    private Cliente cliente;
    private Contato contato;
    private Endereco endereco;
    private Estado estado;

    public ClienteMB() {
        cliente = new Cliente();
        contato = new Contato();
        endereco = new Endereco();
        estado = new Estado();

    }

    public void salvar() {
        endereco.setEstado(estado);
        cliente.setEndereco(endereco);
        cliente.setContatos(contatos);
        cliente.setVendedor(vendedor);
        clientes.add(cliente);
        vendedor.setClientes(clientes);
        
        vendedorDao.saveOrUpdate(vendedor);
//        clienteDao.saveOrUpdate(cliente);

        contatos = new ArrayList<>();
        estado = new Estado();
        endereco = new Endereco();
        cliente = new Cliente();

    }

    public void adicionarContato() {
        contatos.add(contato);
        contato = new Contato();
    }

    public String detalharCliente(Cliente c) {
        cliente = new Cliente(c.getId(), c.getCnpj(), c.getRazaoSocial(), c.getNomeFantasia(), c.getInscricaoEstadual(),
                c.getNumeroFuncionarios(), c.getRamoAtividade(), c.getSegmento(), c.getEmailNFE(), c.getLimite(),
                c.getLimiteDisponivel(), c.getContatos(), c.getEndereco());

        contatos = cliente.getContatos();
        endereco = cliente.getEndereco();
        estado = endereco.getEstado();
        return "adicionarClienteGUI.xhtml";
    }

    @PostConstruct
    public void selectAll() {
        usuario = loginMB.getUsuario();
        if (usuario.getPermissao() == PermissaoEnum.VENDEDOR){
            vendedor = (Vendedor) usuario;
            clientes = vendedor.getClientes();
        }else{
            clientes = clienteDao.findAll();
        }
        enderecos = enderecoDao.findAll();
        estados = estadoDao.findAll();
        
    }

    public void carregarClientes() {

    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public ContatoDao getContatoDao() {
        return contatoDao;
    }

    public void setContatoDao(ContatoDao contatoDao) {
        this.contatoDao = contatoDao;
    }

    public EnderecoDao getEnderecoDao() {
        return enderecoDao;
    }

    public void setEnderecoDao(EnderecoDao enderecoDao) {
        this.enderecoDao = enderecoDao;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public EstadoDao getEstadoDao() {
        return estadoDao;
    }

    public void setEstadoDao(EstadoDao estadoDao) {
        this.estadoDao = estadoDao;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LoginMB getLoginMB() {
        return loginMB;
    }

    public void setLoginMB(LoginMB loginMB) {
        this.loginMB = loginMB;
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

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

}
