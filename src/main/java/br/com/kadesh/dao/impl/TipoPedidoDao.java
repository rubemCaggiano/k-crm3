package br.com.kadesh.dao.impl;

import br.com.kadesh.model.TipoPedido;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class TipoPedidoDao extends AbstractDao {

    public TipoPedidoDao() {
        super();
    }

    public void saveOrUpdate(TipoPedido tp) throws DataAccessLayerException {
        super.saveOrUpdate(tp);
    }

    public void create(TipoPedido tp) throws DataAccessLayerException {
        super.saveOrUpdate(tp);
    }

    public void delete(TipoPedido tp) throws DataAccessLayerException {
        super.delete(tp);
    }

    public TipoPedido find(Integer id) throws DataAccessLayerException {
        return (TipoPedido) super.find(TipoPedido.class, id);
    }

    public void update(TipoPedido tp) throws DataAccessLayerException {
        super.saveOrUpdate(tp);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(TipoPedido.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();

    }
}
