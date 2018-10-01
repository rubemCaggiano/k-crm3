package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.ClienteDao;
import br.com.kadesh.dao.impl.CondPagDao;
import br.com.kadesh.dao.impl.EnderecoDao;
import br.com.kadesh.dao.impl.EstadoDao;
import br.com.kadesh.dao.impl.OpcionaisDao;
import br.com.kadesh.dao.impl.PedidoDao;
import br.com.kadesh.dao.impl.ProdutoDao;
import br.com.kadesh.dao.impl.ProdutoGradeDao;
import br.com.kadesh.dao.impl.TipoPedidoDao;
import br.com.kadesh.dao.impl.TransportadoraDao;
import br.com.kadesh.model.Cliente;
import br.com.kadesh.model.CondicaoPagamento;
import br.com.kadesh.model.Endereco;
import br.com.kadesh.model.Estado;
import br.com.kadesh.model.GradeVenda;
import br.com.kadesh.model.ItemPedido;
import br.com.kadesh.model.Opcional;
import br.com.kadesh.model.Pedido;
import br.com.kadesh.model.Produto;
import br.com.kadesh.model.ProdutoGrade;
import br.com.kadesh.model.SituacaoEnum;
import br.com.kadesh.model.TipoPedido;
import br.com.kadesh.model.Transportadora;
import br.com.kadesh.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@RequestScoped
public class PedidoDetalheMB {

    @ManagedProperty("#{loginMB}")
    private LoginMB loginMB;

    private PedidoDao pedidoDao = new PedidoDao();
    private ClienteDao clienteDao = new ClienteDao();
    private CondPagDao condPagDao = new CondPagDao();
    private TransportadoraDao transportadoraDao = new TransportadoraDao();
    private TipoPedidoDao tipoPedidoDao = new TipoPedidoDao();
    private EnderecoDao enderecoDao = new EnderecoDao();
    private EstadoDao estadoDao = new EstadoDao();
    private ProdutoDao produtoDao = new ProdutoDao();
    private ProdutoGradeDao produtoGradeDao = new ProdutoGradeDao();
    private OpcionaisDao opcionaisDao = new OpcionaisDao();

    private List<TipoPedido> tipoPedidos;
    private List<Transportadora> transportadoras;
    private List<CondicaoPagamento> condicoes;
    private List<Pedido> pedidos;
    private List<Cliente> clientes;
    private List<Estado> estados;
    private List<Produto> produtos;
    private List<ItemPedido> itens = new ArrayList<>();
    private List<GradeVenda> gradeVendas = new ArrayList<>();
    private List<ProdutoGrade> produtosGrade;
    private List<Opcional> opcionaisDisponiveis;

    private Pedido pedido;
    private Cliente cliente;
    private CondicaoPagamento condicaoPagamento;
    private Transportadora transportadora;
    private TipoPedido tipoPedido;
    private Endereco endereco;
    private Estado estado;
    private Produto produto;
    private ProdutoGrade produtoGrade;
    private ItemPedido itemPedido;
    private GradeVenda gradeVenda;
    private Opcional opcional;
    private Usuario usuario;

    public PedidoDetalheMB() {

        pedido = new Pedido();
        cliente = new Cliente();
        condicaoPagamento = new CondicaoPagamento();
        transportadora = new Transportadora();
        tipoPedido = new TipoPedido();
        endereco = new Endereco();
        estado = new Estado();
        produto = new Produto();
        produtoGrade = new ProdutoGrade();
        itemPedido = new ItemPedido();
        gradeVenda = new GradeVenda();
        opcional = new Opcional();
    }

    @PostConstruct
    public void selectAll() {
        usuario = loginMB.getUsuario();

        pedidos = pedidoDao.findAll();
        clientes = clienteDao.findAll();
        condicoes = condPagDao.findAll();
        transportadoras = transportadoraDao.findAll();
        tipoPedidos = tipoPedidoDao.findAll();
        estados = estadoDao.findAll();

        produtos = produtoDao.findAll();

        opcionaisDisponiveis = opcionaisDao.findAll();

    }

    public String detalharPedido(Pedido p) {
        pedido = new Pedido(p.getId(), p.getCliente(), p.getTransportadora(), p.getEnderecoEntrega(), p.getCondicaoPagamento(),
                p.getTipoPedido(), p.getNumeroOrdemCompra(), p.getObservacoes(), p.getSituacao(), p.getValorTotal(), p.getQuantidade(),
                p.getDataCriacao(), p.getItensPedido());
        itens = pedido.getItensPedido();

        return "detalhesPedidoGUI.xhtml";
    }

//    Pedido cancelado pelo vendedor
    public void cancelar() {
        pedido.setSituacao(SituacaoEnum.CANCELADO);
        pedidoDao.saveOrUpdate(pedido);
    }

//    Pedido finalizado pelo vendedor e encaminhado para o supervisor
    public void finalizar() {
        pedido.setSituacao(SituacaoEnum.FINALIZADO);
        pedidoDao.saveOrUpdate(pedido);
    }

//    Pedido aprovado pelo supervisor e encaminhado para o financeiro
    public void aprovar() {
        pedido.setSituacao(SituacaoEnum.SUPERVISOR);
        pedidoDao.saveOrUpdate(pedido);
    }

//    Pedido devolvido para o vendedor pelo supervisor
    public void devolverVendedor() {
        pedido.setSituacao(SituacaoEnum.DEVOLVIDO);
        pedidoDao.saveOrUpdate(pedido);
    }

//    Pedido devolvido para o supervisor pelo financeiro(Não aprovado analise de credito)
    public void devolverSupervisor() {
        pedido.setSituacao(SituacaoEnum.DEVOLVIDO);
        pedidoDao.saveOrUpdate(pedido);
    }

//    Pedido enviado para produção pelo financeiro
    public void enviarProducao() {
        pedido.setSituacao(SituacaoEnum.FINANCEIRO);
        pedidoDao.saveOrUpdate(pedido);
    }

    public PedidoDao getPedidoDao() {
        return pedidoDao;
    }

    public void setPedidoDao(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public CondPagDao getCondPagDao() {
        return condPagDao;
    }

    public void setCondPagDao(CondPagDao condPagDao) {
        this.condPagDao = condPagDao;
    }

    public TransportadoraDao getTransportadoraDao() {
        return transportadoraDao;
    }

    public void setTransportadoraDao(TransportadoraDao transportadoraDao) {
        this.transportadoraDao = transportadoraDao;
    }

    public TipoPedidoDao getTipoPedidoDao() {
        return tipoPedidoDao;
    }

    public void setTipoPedidoDao(TipoPedidoDao tipoPedidoDao) {
        this.tipoPedidoDao = tipoPedidoDao;
    }

    public EnderecoDao getEnderecoDao() {
        return enderecoDao;
    }

    public void setEnderecoDao(EnderecoDao enderecoDao) {
        this.enderecoDao = enderecoDao;
    }

    public EstadoDao getEstadoDao() {
        return estadoDao;
    }

    public void setEstadoDao(EstadoDao estadoDao) {
        this.estadoDao = estadoDao;
    }

    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public ProdutoGradeDao getProdutoGradeDao() {
        return produtoGradeDao;
    }

    public void setProdutoGradeDao(ProdutoGradeDao produtoGradeDao) {
        this.produtoGradeDao = produtoGradeDao;
    }

    public OpcionaisDao getOpcionaisDao() {
        return opcionaisDao;
    }

    public void setOpcionaisDao(OpcionaisDao opcionaisDao) {
        this.opcionaisDao = opcionaisDao;
    }

    public List<TipoPedido> getTipoPedidos() {
        return tipoPedidos;
    }

    public void setTipoPedidos(List<TipoPedido> tipoPedidos) {
        this.tipoPedidos = tipoPedidos;
    }

    public List<Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public void setTransportadoras(List<Transportadora> transportadoras) {
        this.transportadoras = transportadoras;
    }

    public List<CondicaoPagamento> getCondicoes() {
        return condicoes;
    }

    public void setCondicoes(List<CondicaoPagamento> condicoes) {
        this.condicoes = condicoes;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public List<GradeVenda> getGradeVendas() {
        return gradeVendas;
    }

    public void setGradeVendas(List<GradeVenda> gradeVendas) {
        this.gradeVendas = gradeVendas;
    }

    public List<ProdutoGrade> getProdutosGrade() {
        return produtosGrade;
    }

    public void setProdutosGrade(List<ProdutoGrade> produtosGrade) {
        this.produtosGrade = produtosGrade;
    }

    public List<Opcional> getOpcionaisDisponiveis() {
        return opcionaisDisponiveis;
    }

    public void setOpcionaisDisponiveis(List<Opcional> opcionaisDisponiveis) {
        this.opcionaisDisponiveis = opcionaisDisponiveis;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public CondicaoPagamento getCondicaoPagamento() {
        return condicaoPagamento;
    }

    public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
        this.condicaoPagamento = condicaoPagamento;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(TipoPedido tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoGrade getProdutoGrade() {
        return produtoGrade;
    }

    public void setProdutoGrade(ProdutoGrade produtoGrade) {
        this.produtoGrade = produtoGrade;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    public GradeVenda getGradeVenda() {
        return gradeVenda;
    }

    public void setGradeVenda(GradeVenda gradeVenda) {
        this.gradeVenda = gradeVenda;
    }

    public Opcional getOpcional() {
        return opcional;
    }

    public void setOpcional(Opcional opcional) {
        this.opcional = opcional;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LoginMB getLoginMB() {
        return loginMB;
    }

    public void setLoginMB(LoginMB loginMB) {
        this.loginMB = loginMB;
    }

}
