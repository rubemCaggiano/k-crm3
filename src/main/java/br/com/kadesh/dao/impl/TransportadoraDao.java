package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Transportadora;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class TransportadoraDao extends AbstractDao {

    public TransportadoraDao() {
        super();
    }

    public void saveOrUpdate(Transportadora t) throws DataAccessLayerException {
        super.saveOrUpdate(t);
    }

    public void create(Transportadora t) throws DataAccessLayerException {
        super.saveOrUpdate(t);
    }

    public void delete(Transportadora t) throws DataAccessLayerException {
        super.delete(t);
    }

    public Transportadora find(Integer id) throws DataAccessLayerException {
        return (Transportadora) super.find(Transportadora.class, id);
    }

    public void update(Transportadora t) throws DataAccessLayerException {
        super.saveOrUpdate(t);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Transportadora.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();

    }

}
