package br.com.kadesh.dao.impl;

import br.com.kadesh.model.ProdutoGrade;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class ProdutoGradeDao extends AbstractDao {

    public ProdutoGradeDao() {
        super();
    }

    public void saveOrUpdate(ProdutoGrade pg) throws DataAccessLayerException {
        super.saveOrUpdate(pg);
    }

    public void create(ProdutoGrade pg) throws DataAccessLayerException {
        super.saveOrUpdate(pg);
    }

    public void delete(ProdutoGrade pg) throws DataAccessLayerException {
        super.delete(pg);
    }

    public ProdutoGrade find(Integer id) throws DataAccessLayerException {
        return (ProdutoGrade) super.find(ProdutoGrade.class, id);
    }

    public void update(ProdutoGrade pg) throws DataAccessLayerException {
        super.saveOrUpdate(pg);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(ProdutoGrade.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();

    }

}
