package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Linha;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class LinhaDao extends AbstractDao {

    public LinhaDao() {
        super();
    }

    public void saveOrUpdate(Linha l) throws DataAccessLayerException {
        super.saveOrUpdate(l);
    }

    public void create(Linha l) throws DataAccessLayerException {
        super.saveOrUpdate(l);
    }

    public void delete(Linha l) throws DataAccessLayerException {
        super.delete(l);
    }

    public Linha find(Integer id) throws DataAccessLayerException {
        return (Linha) super.find(Linha.class, id);
    }

    public void update(Linha l) throws DataAccessLayerException {
        super.saveOrUpdate(l);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Linha.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();
    }

}
