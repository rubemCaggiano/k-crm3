package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Grupo;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class GrupoDao extends AbstractDao {

    public GrupoDao() {
        super();
    }

    public void saveOrUpdate(Grupo g) throws DataAccessLayerException {
        super.saveOrUpdate(g);
    }

    public void create(Grupo g) throws DataAccessLayerException {
        super.saveOrUpdate(g);
    }

    public void delete(Grupo g) throws DataAccessLayerException {
        super.delete(g);
    }

    public Grupo find(Integer id) throws DataAccessLayerException {
        return (Grupo) super.find(Grupo.class, id);
    }

    public void update(Grupo g) throws DataAccessLayerException {
        super.saveOrUpdate(g);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Grupo.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();
    }

}
