package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.EstadoDao;
import br.com.kadesh.dao.impl.TransportadoraDao;
import br.com.kadesh.model.Estado;
import br.com.kadesh.model.StatusEnum;
import br.com.kadesh.model.Transportadora;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TransportadoraMB implements Serializable {

    private TransportadoraDao transportadoraDao = new TransportadoraDao();
    private EstadoDao estadoDao = new EstadoDao();

    private List<Transportadora> transportadoras;
    private List<Estado> estados;
    private List<Estado> estadosAtendidos = new ArrayList<>();
    private List<StatusEnum> statusPossiveis;

    private Transportadora transportadora;
    private Estado estado;
    private StatusEnum status;
    private boolean mostrar;

    public TransportadoraMB() {
        selectAll();
        transportadora = new Transportadora();
        estado = new Estado();
        mostrar = false;
    }
    public void instanciarTransportadora(){
        transportadora = new Transportadora();
        estadosAtendidos = new ArrayList<>();
        estado = new Estado();
    }

    public void salvar() {
//        transportadoraDao.saveOrUpdate(transportadora);
        transportadora.setStatus(status);
        transportadora.setEstadosAtendidos(estadosAtendidos);

        transportadoraDao.saveOrUpdate(transportadora);

        transportadora = new Transportadora();
        estadosAtendidos = new ArrayList<>();
        mostrar = false;
        selectAll();
    }

    public void mostrarCadTransp() {
        mostrar = true;
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
        status = transportadora.getStatus();
        mostrar = true;

    }

    public void excluirTransportadora(Transportadora transportadora) {
        transportadoraDao.delete(transportadora);
        this.transportadora = new Transportadora();
        selectAll();
    }

    public void selectAll() {
        transportadoras = transportadoraDao.findAll();
        estados = estadoDao.findAll();
        statusPossiveis = Arrays.asList(StatusEnum.values());
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

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public List<StatusEnum> getStatusPossiveis() {
        return statusPossiveis;
    }

    public void setStatusPossiveis(List<StatusEnum> statusPossiveis) {
        this.statusPossiveis = statusPossiveis;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

}
