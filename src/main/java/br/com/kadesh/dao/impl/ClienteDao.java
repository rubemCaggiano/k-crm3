package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Cliente;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;


public class ClienteDao extends AbstractDao {

    public ClienteDao() {
        super();
    }

    public void saveOrUpdate(Cliente c) throws DataAccessLayerException {
        super.saveOrUpdate(c);
    }

    public void create(Cliente c) throws DataAccessLayerException {
        super.saveOrUpdate(c);
    }

    public void delete(Cliente c) throws DataAccessLayerException {
        super.delete(c);
    }

    public Cliente find(Integer id) throws DataAccessLayerException {
        return (Cliente) super.find(Cliente.class, id);
    }

    public void update(Cliente c) throws DataAccessLayerException {
        super.saveOrUpdate(c);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Cliente.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();
    }
    
    
    
    
    
}
