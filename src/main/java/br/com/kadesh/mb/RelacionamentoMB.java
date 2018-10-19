package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.ClienteDao;
import br.com.kadesh.dao.impl.RelacionamentoDao;
import br.com.kadesh.model.Cliente;
import br.com.kadesh.model.Relacionamento;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class RelacionamentoMB implements Serializable{
    
    private ClienteDao clienteDao               = new ClienteDao();
    private RelacionamentoDao relacionamentoDao = new RelacionamentoDao();
    
    private List<Cliente> clientes;
    private List<Relacionamento> relacionamentos;
    
    private Cliente cliente               = new Cliente();
    private Relacionamento relacionamento = new Relacionamento();

    public RelacionamentoMB() {
        cliente        = new Cliente();
        relacionamento = new Relacionamento();     
    }
    
    @PostConstruct
    public void selectAll() {
        clientes = clienteDao.findAll();
    }
    
    public void Salvar(){
        relacionamento.setCliente(cliente);
        relacionamentoDao.saveOrUpdate(relacionamento);
        
        relacionamento = new Relacionamento();
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public RelacionamentoDao getRelacionamentoDao() {
        return relacionamentoDao;
    }

    public void setRelacionamentoDao(RelacionamentoDao relacionamentoDao) {
        this.relacionamentoDao = relacionamentoDao;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Relacionamento> getRelacionamentos() {
        return relacionamentos;
    }

    public void setRelacionamentos(List<Relacionamento> relacionamentos) {
        this.relacionamentos = relacionamentos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Relacionamento getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(Relacionamento relacionamento) {
        this.relacionamento = relacionamento;
    } 
}
