package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Contato;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class ContatoDao extends AbstractDao{

    public ContatoDao() {
       super();
    }
    
    public void saveOrUpdate(Contato co) throws DataAccessLayerException {
        super.saveOrUpdate(co);
    }

    public void create(Contato co) throws DataAccessLayerException {
        super.saveOrUpdate(co);
    }

    public void delete(Contato co) throws DataAccessLayerException {
        super.delete(co);
    }

    public Contato find(Integer id) throws DataAccessLayerException {
        return (Contato) super.find(Contato.class, id);
    }

    public void update(Contato co) throws DataAccessLayerException {
        super.saveOrUpdate(co);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Contato.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();
    }
    
}
