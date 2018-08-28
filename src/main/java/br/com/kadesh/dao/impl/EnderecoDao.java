package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Endereco;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class EnderecoDao extends AbstractDao {

    public EnderecoDao() {
        super();
    }

    public void saveOrUpdate(Endereco e) throws DataAccessLayerException {
        super.saveOrUpdate(e);
    }

    public void create(Endereco e) throws DataAccessLayerException {
        super.saveOrUpdate(e);
    }

    public void delete(Endereco e) throws DataAccessLayerException {
        super.delete(e);
    }

    public Endereco find(Integer id) throws DataAccessLayerException {
        return (Endereco) super.find(Endereco.class, id);
    }

    public void update(Endereco e) throws DataAccessLayerException {
        super.saveOrUpdate(e);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Endereco.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();

    }
}
