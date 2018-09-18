package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.EstadoDao;
import br.com.kadesh.dao.impl.TransportadoraDao;
import br.com.kadesh.model.Estado;
import br.com.kadesh.model.Transportadora;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TransportadoraMB implements Serializable {

    private TransportadoraDao transportadoraDao = new TransportadoraDao();
    private EstadoDao estadoDao = new EstadoDao();

    private List<Transportadora> transportadoras;
    private List<Estado> estados;
    private List<Estado> estadosAtendidos;

    private Transportadora transportadora;
    private Estado estado;

    public TransportadoraMB() {
        transportadora = new Transportadora();
        estado = new Estado();
    }
    
    public void adicionarEstado(){
        estadosAtendidos.add(estado);
        estado = new Estado();
    }

    @PostConstruct
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

    public List<Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public void setTransportadoras(List<Transportadora> transportadoras) {
        this.transportadoras = transportadoras;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
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

    public List<Estado> getEstadosAtendidos() {
        return estadosAtendidos;
    }

    public void setEstadosAtendidos(List<Estado> estadosAtendidos) {
        this.estadosAtendidos = estadosAtendidos;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
