 package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.ClienteDao;
import br.com.kadesh.model.Cliente;
import br.com.kadesh.dao.impl.ContatoDao;
import br.com.kadesh.model.Contato;
import br.com.kadesh.dao.impl.EnderecoDao;
import br.com.kadesh.model.Endereco;
import br.com.kadesh.dao.impl.EstadoDao;
import br.com.kadesh.model.Estado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class ClienteMB implements Serializable{
    ClienteDao clienteDao   = new ClienteDao();
    ContatoDao contatoDao   = new ContatoDao();
    EnderecoDao enderecoDao = new EnderecoDao();
    EstadoDao estadoDao     = new EstadoDao();
  
    List<Cliente> clientes;
    List<Contato> contatos = new ArrayList<>();
    List<Endereco> enderecos;
    List<Estado> estados;
    
    private Cliente cliente;
    private Contato contato;
    private Endereco endereco;
    private Estado estado;
    
    public ClienteMB() {
        cliente  = new Cliente();
        contato  = new Contato();
        endereco = new Endereco();
        estado   = new Estado();
    }
    
    public void salvar(){
        endereco.setEstado(estado);
        cliente.setEndereco(endereco);
        cliente.setContatos(contatos);
        clienteDao.saveOrUpdate(cliente);
        
        contatos = new ArrayList<>();
        estado = new Estado();
        endereco = new Endereco();
        cliente = new Cliente();
        
        
        
    }
    
    public void adicionarContato(){
        contatos.add(contato);
        contato = new Contato();
    }
    
    public String detalharCliente(Cliente c){
        cliente = new Cliente(c.getId(), c.getCnpj(), c.getRazaoSocial(), c.getNomeFantasia(), c.getInscricaoEstadual(),
                c.getNumeroFuncionarios(),c.getRamoAtividade(), c.getSegmento(), c.getEmailNFE(), c.getLimite(),
                c.getLimiteDisponivel(), c.getContatos(), c.getEndereco());
        
        contatos = cliente.getContatos();
        endereco = cliente.getEndereco();
        estado = endereco.getEstado();
        return "adicionarClienteGUI.xhtml";
    }
    
    @PostConstruct
    public void selectAll(){
        clientes  = clienteDao.findAll();
        enderecos = enderecoDao.findAll();
        estados   = estadoDao.findAll();
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
    
    
    
    
    
    
}
