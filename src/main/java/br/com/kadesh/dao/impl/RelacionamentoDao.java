package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Relacionamento;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class RelacionamentoDao extends AbstractDao{
    
    public RelacionamentoDao() {
        super();
    }

    public void saveOrUpdate(Relacionamento r) throws DataAccessLayerException {
        super.saveOrUpdate(r);
    }

    public void create(Relacionamento r) throws DataAccessLayerException {
        super.saveOrUpdate(r);
    }

    public void delete(Relacionamento r) throws DataAccessLayerException {
        super.delete(r);
    }

    public Relacionamento find(Integer id) throws DataAccessLayerException {
        return (Relacionamento) super.find(Relacionamento.class, id);
    }

    public void update(Relacionamento r) throws DataAccessLayerException {
        super.saveOrUpdate(r);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Relacionamento.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();
    }
}
