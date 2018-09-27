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
        produto.setFamilia(familia);
        produto.setLinha(linha);
        produto.setGrupo(grupo);

        produtoDao.create(produto);

        produto = new Produto();
        selectAll();
    }

    @PostConstruct
    public void selectAll() {
        produtos = produtoDao.findAll();
        familias = familiaDao.findAll();
        linhas = linhaDao.findAll();
        grupos = grupoDao.findAll();
    }

    public void novoProduto() {
        produto = new Produto();
    }

    public void detalharProduto(Produto p) {
        produto = new Produto(p.getId(), p.getDescricao(), p.getReferencia(), p.getNumeroCa(),
                p.getCusto(), p.getMcMinima(), p.isStatus(), p.getPrecoSugerido(), p.getPrecoMinimo(),
                p.getGrupo(), p.getFamilia(), p.getLinha());
        familia = produto.getFamilia();
        linha = produto.getLinha();
        grupo = produto.getGrupo();
    }

    public void detalharFamilia(Familia f) {
        familia = new Familia(f.getId(), f.getNome(), f.getDescricao(), f.getNomeReduzido());
    }

    public void detalharLinha(Linha l) {
        linha = new Linha(l.getId(), l.getNome(), l.getDescricao(), l.getNomeReduzido());
    }

    public void detalharGrupo(Grupo g) {
        grupo = new Grupo(g.getId(), g.getNome(), g.getDescricao(), g.getNomeReduzido());
    }

    public void excluirProduto() {
        produtoDao.delete(produto);
        produto = new Produto();
        selectAll();
    }

    public void excluirFamilia(Familia f) {
        familiaDao.delete(f);
        this.familia = new Familia();
        selectAll();
    }

    public void excluirLinha(Linha l) {
        linhaDao.delete(l);
        this.linha = new Linha();
        selectAll();
    }

    public void excluirGrupo(Grupo g) {
        grupoDao.delete(g);
        this.grupo = new Grupo();
        selectAll();
    }

    public void alterarProduto(Produto p) {

    }

    public void alterarFamilia(Familia f) {

    }

    public void alterarLinha(Linha l) {

    }

    public void alterarGrupo(Grupo g) {
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
