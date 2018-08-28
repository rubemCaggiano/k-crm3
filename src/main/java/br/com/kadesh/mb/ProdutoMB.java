package br.com.kadesh.mb;

import br.com.kadesh.dao.impl.FamiliaDao;
import br.com.kadesh.dao.impl.GrupoDao;
import br.com.kadesh.dao.impl.LinhaDao;
import br.com.kadesh.dao.impl.ProdutoDao;
import br.com.kadesh.model.Familia;
import br.com.kadesh.model.Grupo;
import br.com.kadesh.model.Linha;
import br.com.kadesh.model.Produto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

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

    private Produto produto;
    private Familia familia;
    private Linha linha;
    private Grupo grupo;

    public ProdutoMB() {
        
        produto = new Produto();
        familia = new Familia();
        linha = new Linha();
        grupo = new Grupo();
    }
    
    public void salvar() {
        System.out.println("Salvo1");
        produto.setFamilia(familia);
        produto.setLinha(linha);
        produto.setGrupo(grupo);

        produtoDao.create(produto);

        produto = new Produto();
        System.out.println("Salvo2");
    }

    @PostConstruct
    public void selectAll() {
        produtos = produtoDao.findAll();
        familias = familiaDao.findAll();
        linhas = linhaDao.findAll();
        grupos = grupoDao.findAll();
    }

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

}
