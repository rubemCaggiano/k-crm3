package br.com.kadesh.dao.impl;

import br.com.kadesh.model.CondicaoPagamento;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class CondPagDao extends AbstractDao {

    public CondPagDao() {
        super();
    }

    
    public void saveOrUpdate(CondicaoPagamento cp) throws DataAccessLayerException {
        super.saveOrUpdate(cp);
    }

    public void create(CondicaoPagamento cp) throws DataAccessLayerException {
        super.saveOrUpdate(cp);
    }

    public void delete(CondicaoPagamento cp) throws DataAccessLayerException {
        super.delete(cp);
    }

    public CondicaoPagamento find(Integer id) throws DataAccessLayerException {
        return (CondicaoPagamento) super.find(CondicaoPagamento.class, id);
    }

    public void update(CondicaoPagamento cp) throws DataAccessLayerException {
        super.saveOrUpdate(cp);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(CondicaoPagamento.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();

    }
}
