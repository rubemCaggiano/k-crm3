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

    private Produto produto;
    private Familia familia;
    private Linha linha;
    private Grupo grupo;
    private StatusEnum status;

    public ProdutoMB() {

        produto = new Produto();
        familia = new Familia();
        linha = new Linha();
        grupo = new Grupo();
    }

    public void salvarProduto() {
        produto.setFamilia(familia);
        produto.setLinha(linha);
        produto.setGrupo(grupo);
        produto.setStatus(StatusEnum.ATIVO);
        produto.setNumeracao(geraGrade());
        
        System.out.println("Salvando");
        produtoDao.saveOrUpdate(produto);
        System.out.println("Salvo");

        produto = new Produto();
        selectAll();
    }

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

    public void salvarFamilia() {
        familiaDao.create(familia);
        familia = new Familia();

        selectAll();
    }

    public void salvarLinha() {
        linhaDao.create(linha);
        linha = new Linha();
        selectAll();
    }

    public void salvarGrupo() {
        grupoDao.create(grupo);
        grupo = new Grupo();
        selectAll();
    }

    @PostConstruct
    public void selectAll() {
        produtos = produtoDao.findAll();
        familias = familiaDao.findAll();
        linhas = linhaDao.findAll();
        grupos = grupoDao.findAll();
        statusPossiveis = Arrays.asList(StatusEnum.values());
    }

    public void novoProduto() {
        produto = new Produto();
    }

    public void detalharProduto(Produto p) {
        produto = produtoDao.find(p.getId());
//        produto = new Produto(p.getId(), p.getDescricao(), p.getReferencia(), p.getNumeroCa(),
//                p.getCusto(), p.getMcMinima(), p.isStatus(), p.getPrecoSugerido(), p.getPrecoMinimo(),
//                p.getGrupo(), p.getFamilia(), p.getLinha());
        familia = produto.getFamilia();
        linha = produto.getLinha();
        grupo = produto.getGrupo();
    }

    public void carregarProduto(Produto p) {
        produto = produtoDao.find(p.getId());

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
