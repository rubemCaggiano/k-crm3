package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Estado;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class EstadoDao extends AbstractDao {

    public EstadoDao() {
        super();
    }

    public void saveOrUpdate(Estado e) throws DataAccessLayerException {
        super.saveOrUpdate(e);
    }

    public void create(Estado e) throws DataAccessLayerException {
        super.saveOrUpdate(e);
    }

    public void delete(Estado e) throws DataAccessLayerException {
        super.delete(e);
    }

    public Estado find(Integer id) throws DataAccessLayerException {
        return (Estado) super.find(Estado.class, id);
    }

    public void update(Estado e) throws DataAccessLayerException {
        super.saveOrUpdate(e);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Estado.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();

    }

}
