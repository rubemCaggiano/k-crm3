package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.FamiliaDao;
import br.com.kadesh.dao.impl.GrupoDao;
import br.com.kadesh.dao.impl.LinhaDao;
import br.com.kadesh.dao.impl.ProdutoDao;
import br.com.kadesh.model.Familia;
import br.com.kadesh.model.Grupo;
import br.com.kadesh.model.Linha;
import br.com.kadesh.model.Produto;
import br.com.kadesh.model.ProdutoGrade;
import br.com.kadesh.model.StatusEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

@ManagedBean
@ViewScoped
public class ProdutoMB implements Serializable {

    private ProdutoDao produtoDao = new ProdutoDao();
    private FamiliaDao familiaDao = new FamiliaDao();
    private LinhaDao linhaDao = new LinhaDao();
    private GrupoDao grupoDao = new GrupoDao();

    private List<Produto> produtos;
    private List<Familia> familias;
    private List<Linha> linhas;
    private List<Grupo> grupos;
    private List<StatusEnum> statusPossiveis;

    private Produto produto = new Produto();
    private Familia familia;
    private Linha linha;
    private Grupo grupo;
    private StatusEnum status;

    private boolean mostrarProd = false;
    private boolean mostrarFamilia = false;
    private boolean mostrarLinha = false;
    private boolean mostrarGrupo = false;

    public ProdutoMB() {

        familia = new Familia();
        linha = new Linha();
        grupo = new Grupo();
        mostrarProd = false;
        mostrarFamilia = false;
        mostrarLinha = false;
        mostrarGrupo = false;

    }

    //Metodos para mostrar Formularios de cadastro
    public void mostrarCadProd() {
        mostrarProd = true;
    }

    public void mostrarCadFamilia() {
        mostrarFamilia = true;
    }

    public void mostrarCadLinha() {
        mostrarLinha = true;
    }

    public void mostrarCadGrupo() {
        mostrarGrupo = true;
    }
    //--------------------------------------------------------------------------

    //Metodos para esconder Formularios de cadastro
    public void ocultarCadProd() {
        mostrarProd = false;
    }

    public void ocultarCadFamilia() {
        mostrarFamilia = false;
    }

    public void ocultarCadLinha() {
        mostrarLinha = false;
    }

    public void ocultarCadGrupo() {
        mostrarGrupo = false;
    }
    //--------------------------------------------------------------------------

//    Metodos para Salvar Cadastros
    public void salvarProduto() {
        try {
            produto.setFamilia(familia);
            produto.setLinha(linha);
            produto.setGrupo(grupo);
            produto.setStatus(status);
            produto.setNumeracao(geraGrade());
            produtoDao.saveOrUpdate(produto);
            produto = new Produto();
            linha = new Linha();
            familia = new Familia();
            grupo = new Grupo();
            mostrarProd = false;
            selectAll();
            Messages.addGlobalInfo("Registro Inserido Com Sucesso");
        } catch (Exception e) {
            Messages.addGlobalError("Falha ao inserir Registro");
        }

    }

    public void salvarFamilia() {
        try {
            familiaDao.create(familia);
            familia = new Familia();
            mostrarFamilia = false;
            selectAll();
            Messages.addGlobalInfo("Registro Inserido Com Sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao inserir Registro");
        }

    }

    public void salvarLinha() {
        try {
            linhaDao.create(linha);
            linha = new Linha();
            mostrarLinha = false;
            selectAll();
            Messages.addGlobalInfo("Registro Inserido Com Sucesso");
        } catch (Exception e) {
            Messages.addGlobalError("Falha ao inserir Registro");
        }

    }

    public void salvarGrupo() {
        try {
            grupoDao.create(grupo);
            grupo = new Grupo();
            mostrarGrupo = false;
            selectAll();
            Messages.addGlobalInfo("Registro Inserido Com Sucesso");
        } catch (Exception e) {
            Messages.addGlobalError("Falha ao inserir Registro");
        }

    }
    //--------------------------------------------------------------------------

    public List<ProdutoGrade> geraGrade() {
        List<ProdutoGrade> grades = new ArrayList<>();
        ProdutoGrade pg = new ProdutoGrade(0, 34);
        grades.add(pg);
        ProdutoGrade pg2 = new ProdutoGrade(0, 35);
        grades.add(pg2);
        ProdutoGrade pg3 = new ProdutoGrade(0, 36);
        grades.add(pg3);
        ProdutoGrade pg4 = new ProdutoGrade(0, 37);
        grades.add(pg4);
        ProdutoGrade pg5 = new ProdutoGrade(0, 38);
        grades.add(pg5);
        ProdutoGrade pg6 = new ProdutoGrade(0, 39);
        grades.add(pg6);
        ProdutoGrade pg7 = new ProdutoGrade(0, 40);
        grades.add(pg7);
        ProdutoGrade pg8 = new ProdutoGrade(0, 41);
        grades.add(pg8);
        ProdutoGrade pg9 = new ProdutoGrade(0, 42);
        grades.add(pg9);
        ProdutoGrade pg10 = new ProdutoGrade(0, 43);
        grades.add(pg10);
        ProdutoGrade pg11 = new ProdutoGrade(0, 44);
        grades.add(pg11);
        ProdutoGrade pg12 = new ProdutoGrade(0, 45);
        grades.add(pg12);
        ProdutoGrade pg13 = new ProdutoGrade(0, 46);
        grades.add(pg13);
        ProdutoGrade pg14 = new ProdutoGrade(0, 47);
        grades.add(pg14);

        return grades;
    }

    @PostConstruct
    public void selectAll() {
        produtos = produtoDao.findAll();
        familias = familiaDao.findAll();
        linhas = linhaDao.findAll();
        grupos = grupoDao.findAll();
        statusPossiveis = Arrays.asList(StatusEnum.values());
    }

//    Metodos para carregar os cadastros
    public void detalharProduto(Produto p) {
        this.produto = produtoDao.find(p.getId());

        familia = produto.getFamilia();
        linha = produto.getLinha();
        grupo = produto.getGrupo();
        status = produto.getStatus();
        mostrarProd = true;
    }

    public void detalharFamilia(Familia f) {
        familia = familiaDao.find(f.getId());
        mostrarFamilia = true;
    }

    public void detalharLinha(Linha l) {
        linha = linhaDao.find(l.getId());
        mostrarLinha = true;
    }

    public void detalharGrupo(Grupo g) {
        grupo = grupoDao.find(g.getId());
        mostrarGrupo = true;
    }

    //--------------------------------------------------------------------------
//    Metodos para excluir Cadastros
    public void excluirProduto() {
        try {
            produtoDao.delete(produto);
            produto = new Produto();
            selectAll();
        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir Registro");
        }

    }

    public void excluirFamilia(Familia f) {
        try {
            familiaDao.delete(f);
            this.familia = new Familia();
            selectAll();
        } catch (javax.persistence.PersistenceException ex) {
            Messages.addGlobalError("Falha ao excluir, existem produtos vinculados");
        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir Registro");
        }

    }

    public void excluirLinha(Linha l) {
        try {
            linhaDao.delete(l);
            this.linha = new Linha();
            selectAll();
        } catch (javax.persistence.PersistenceException ex) {
            Messages.addGlobalError("Falha ao excluir, existem produtos vinculados");
        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir Registro");
        }

    }

    public void excluirGrupo(Grupo g) {
        try {
            grupoDao.delete(g);
            this.grupo = new Grupo();
            selectAll();
        } catch (javax.persistence.PersistenceException ex) {
            Messages.addGlobalError("Falha ao excluir, existem produtos vinculados");
        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir Registro");
        }

    }
    //--------------------------------------------------------------------------

//    Getters and Setters
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public FamiliaDao getFamiliaDao() {
        return familiaDao;
    }

    public void setFamiliaDao(FamiliaDao familiaDao) {
        this.familiaDao = familiaDao;
    }

    public LinhaDao getLinhaDao() {
        return linhaDao;
    }

    public void setLinhaDao(LinhaDao linhaDao) {
        this.linhaDao = linhaDao;
    }

    public GrupoDao getGrupoDao() {
        return grupoDao;
    }

    public void setGrupoDao(GrupoDao grupoDao) {
        this.grupoDao = grupoDao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Familia> getFamilias() {
        return familias;
    }

    public void setFamilias(List<Familia> familias) {
        this.familias = familias;
    }

    public List<Linha> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Linha> linhas) {
        this.linhas = linhas;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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

    public boolean isMostrarProd() {
        return mostrarProd;
    }

    public void setMostrarProd(boolean mostrarProd) {
        this.mostrarProd = mostrarProd;
    }

    public boolean isMostrarFamilia() {
        return mostrarFamilia;
    }

    public void setMostrarFamilia(boolean mostrarFamilia) {
        this.mostrarFamilia = mostrarFamilia;
    }

    public boolean isMostrarLinha() {
        return mostrarLinha;
    }

    public void setMostrarLinha(boolean mostrarLinha) {
        this.mostrarLinha = mostrarLinha;
    }

    public boolean isMostrarGrupo() {
        return mostrarGrupo;
    }

    public void setMostrarGrupo(boolean mostrarGrupo) {
        this.mostrarGrupo = mostrarGrupo;
    }

}
