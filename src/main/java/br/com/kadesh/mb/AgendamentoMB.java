package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.ClienteDao;
import br.com.kadesh.dao.impl.AgendamentoDao;
import br.com.kadesh.dao.impl.UsuarioDao;
import br.com.kadesh.dao.impl.VendedorDao;
import br.com.kadesh.model.Cliente;
import br.com.kadesh.model.Agendamento;
import br.com.kadesh.model.StatusEnumAgendamento;
import br.com.kadesh.model.Usuario;
import br.com.kadesh.model.Vendedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AgendamentoMB implements Serializable {

    @ManagedProperty("#{loginMB}")
    private LoginMB loginMB;

    private ClienteDao clienteDao = new ClienteDao();
    private AgendamentoDao agendamentoDao = new AgendamentoDao();
    private VendedorDao vendedorDao = new VendedorDao();
    private UsuarioDao usuarioDao = new UsuarioDao();

    private List<Cliente> clientes;
    private List<Agendamento> agendamentos;
    private List<StatusEnumAgendamento> status;

    private Usuario usuario;
    private Vendedor vendedor;
    private Cliente cliente;
    private Agendamento agendamento;
    private StatusEnumAgendamento statusEnumAgendamento;
    private boolean PAgendamento = false;
    private boolean dadosAlterar = false;
    private boolean alterar = false;

    public AgendamentoMB() {
        cliente = new Cliente();
        agendamento = new Agendamento();
        alterar = false;
    }

    public void mostrarPAgendamento() {
        dadosAlterar = false;
        PAgendamento = true;
        cliente = new Cliente();
        agendamento = new Agendamento();
    }

    public void ocultarPAgendamento() {
        PAgendamento = false;
    }

    public void salvar() {
        agendamento.setCliente(cliente);
        agendamento.setVendedor(vendedor);
        agendamento.setStatus(statusEnumAgendamento);

        if (alterar == true) {
            List <Agendamento> agendamentosTemp = new ArrayList<>();
            for (Agendamento a : agendamentos) {
                if (a.getId() != agendamento.getId()) {
                    agendamentosTemp.add(a);
                }
            }
            
            agendamentosTemp.add(agendamento);
            vendedor.setAgendamentos(agendamentosTemp);
            vendedorDao.saveOrUpdate(vendedor);
        } else if (alterar == false) {
            agendamento.setStatus(StatusEnumAgendamento.PENDENTE);
            agendamentos.add(agendamento);
            vendedor.setAgendamentos(agendamentos);
            vendedorDao.saveOrUpdate(vendedor);
        }

        cliente = new Cliente();
        agendamento = new Agendamento();
        dadosAlterar = false;
        PAgendamento = false;
        selectAll();
    }

    public void cancelar() {
        cliente = new Cliente();
        agendamento = new Agendamento();
        dadosAlterar = false;
        PAgendamento = false;
    }

    @PostConstruct
    public void selectAll() {
        vendedor = (Vendedor) loginMB.getUsuario();
        clientes = vendedor.getClientes();
        agendamentos = vendedor.getAgendamentos();
        status = Arrays.asList(StatusEnumAgendamento.values());
    }

    public void detalharAgendamento(Agendamento a) {
        this.agendamento = agendamentoDao.find(a.getId());
        statusEnumAgendamento = agendamento.getStatus();
        cliente = agendamento.getCliente();
        dadosAlterar = true;
        PAgendamento = true;
        alterar = true;
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public AgendamentoDao getAgendamentoDao() {
        return agendamentoDao;
    }

    public void setAgendamentoDao(AgendamentoDao agendamentoDao) {
        this.agendamentoDao = agendamentoDao;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<StatusEnumAgendamento> getStatus() {
        return status;
    }

    public void setStatus(List<StatusEnumAgendamento> status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public StatusEnumAgendamento getStatusEnumAgendamento() {
        return statusEnumAgendamento;
    }

    public void setStatusEnumAgendamento(StatusEnumAgendamento statusEnumAgendamento) {
        this.statusEnumAgendamento = statusEnumAgendamento;
    }

    public LoginMB getLoginMB() {
        return loginMB;
    }

    public void setLoginMB(LoginMB loginMB) {
        this.loginMB = loginMB;
    }

    public VendedorDao getVendedorDao() {
        return vendedorDao;
    }

    public void setVendedorDao(VendedorDao vendedorDao) {
        this.vendedorDao = vendedorDao;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
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

    public boolean isPAgendamento() {
        return PAgendamento;
    }

    public void setPAgendamento(boolean PAgendamento) {
        this.PAgendamento = PAgendamento;
    }

    public boolean isDadosAlterar() {
        return dadosAlterar;
    }

    public void setDadosAlterar(boolean dadosAlterar) {
        this.dadosAlterar = dadosAlterar;
    }

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }

}
