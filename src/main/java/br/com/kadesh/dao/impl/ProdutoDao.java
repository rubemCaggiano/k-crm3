package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Produto;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class ProdutoDao extends AbstractDao {

    public ProdutoDao() {
        super();
    }

    public void saveOrUpdate(Produto p) throws DataAccessLayerException {
        super.saveOrUpdate(p);
    }

    public void create(Produto p) throws DataAccessLayerException {
        super.saveOrUpdate(p);
    }

    public void delete(Produto p) throws DataAccessLayerException {
        super.delete(p);
    }

    public Produto find(Integer id) throws DataAccessLayerException {
        return (Produto) super.find(Produto.class, id);
    }

    public void update(Produto p) throws DataAccessLayerException {
        super.saveOrUpdate(p);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Produto.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();
    }

}
