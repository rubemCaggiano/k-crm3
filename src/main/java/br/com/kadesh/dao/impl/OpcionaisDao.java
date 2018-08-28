package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Opcional;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class OpcionaisDao extends AbstractDao {

    public OpcionaisDao() {
        super();
    }

    public void saveOrUpdate(Opcional o) throws DataAccessLayerException {
        super.saveOrUpdate(o);
    }

    public void create(Opcional o) throws DataAccessLayerException {
        super.saveOrUpdate(o);
    }

    public void delete(Opcional o) throws DataAccessLayerException {
        super.delete(o);
    }

    public Opcional find(Integer id) throws DataAccessLayerException {
        return (Opcional) super.find(Opcional.class, id);
    }

    public void update(Opcional o) throws DataAccessLayerException {
        super.saveOrUpdate(o);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Opcional.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();
    }

}
