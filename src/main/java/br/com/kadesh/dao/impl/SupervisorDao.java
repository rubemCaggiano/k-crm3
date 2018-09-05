package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Supervisor;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class SupervisorDao extends AbstractDao{

    public SupervisorDao() {
        super();
    }
    
    public void saveOrUpdate(Supervisor s) throws DataAccessLayerException {
        super.saveOrUpdate(s);
    }

    public void create(Supervisor s) throws DataAccessLayerException {
        super.saveOrUpdate(s);
    }

    public void delete(Supervisor s) throws DataAccessLayerException {
        super.delete(s);
    }

    public Supervisor find(Integer id) throws DataAccessLayerException {
        return (Supervisor) super.find(Supervisor.class, id);
    }

    public void update(Supervisor s) throws DataAccessLayerException {
        super.saveOrUpdate(s);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Supervisor.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();
    }
    
    
    
}
