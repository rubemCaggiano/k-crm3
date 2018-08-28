package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Familia;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class FamiliaDao extends AbstractDao {

    public FamiliaDao() {
        super();
    }

    public void saveOrUpdate(Familia f) throws DataAccessLayerException {
        super.saveOrUpdate(f);
    }

    public void create(Familia f) throws DataAccessLayerException {
        super.saveOrUpdate(f);
    }

    public void delete(Familia f) throws DataAccessLayerException {
        super.delete(f);
    }

    public Familia find(Integer id) throws DataAccessLayerException {
        return (Familia) super.find(Familia.class, id);
    }

    public void update(Familia f) throws DataAccessLayerException {
        super.saveOrUpdate(f);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Familia.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();
    }

}
