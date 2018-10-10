package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.EstadoDao;
import br.com.kadesh.dao.impl.TransportadoraDao;
import br.com.kadesh.model.Estado;
import br.com.kadesh.model.Transportadora;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TransportadoraMB {

    private TransportadoraDao transportadoraDao = new TransportadoraDao();
    private EstadoDao estadoDao = new EstadoDao();

    private List<Transportadora> transportadoras;
    private List<Estado> estados;
    private List<Estado> estadosAtendidos = new ArrayList<>();

    private Transportadora transportadora;
    private Estado estado;

    public TransportadoraMB() {
        selectAll();
        transportadora = new Transportadora();
        estado = new Estado();
    }

    public void salvar() {
        transportadora.setEstadosAtendidos(estadosAtendidos);
        transportadoraDao.saveOrUpdate(transportadora);

        transportadora = new Transportadora();
        selectAll();
    }

    public void adicionarEstado() {
        estadosAtendidos.add(estado);
        estados.remove(estado);
        estado = new Estado();
    }

    public void removerEstado(Estado estado) {
        estadosAtendidos.remove(estado);

    }

    public void detalharTransportadora(Transportadora transportadora) {
        this.transportadora = transportadoraDao.find(transportadora.getId());
        estadosAtendidos = transportadora.getEstadosAtendidos();

    }

    public void selectAll() {
        transportadoras = transportadoraDao.findAll();
        estados = estadoDao.findAll();
    }

    public TransportadoraDao getTransportadoraDao() {
        return transportadoraDao;
    }

    public void setTransportadoraDao(TransportadoraDao transportadoraDao) {
        this.transportadoraDao = transportadoraDao;
    }

    public EstadoDao getEstadoDao() {
        return estadoDao;
    }

    public void setEstadoDao(EstadoDao estadoDao) {
        this.estadoDao = estadoDao;
    }

    public List<Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public void setTransportadoras(List<Transportadora> transportadoras) {
        this.transportadoras = transportadoras;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Estado> getEstadosAtendidos() {
        return estadosAtendidos;
    }

    public void setEstadosAtendidos(List<Estado> estadosAtendidos) {
        this.estadosAtendidos = estadosAtendidos;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
