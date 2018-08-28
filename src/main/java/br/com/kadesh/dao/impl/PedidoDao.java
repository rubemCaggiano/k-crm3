package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Pedido;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class PedidoDao extends AbstractDao {

    public PedidoDao() {
        super();
    }

    public void saveOrUpdate(Pedido p) throws DataAccessLayerException {
        super.saveOrUpdate(p);
    }

    public void create(Pedido p) throws DataAccessLayerException {
        super.saveOrUpdate(p);
    }

    public void delete(Pedido p) throws DataAccessLayerException {
        super.delete(p);
    }

    public Pedido find(Integer id) throws DataAccessLayerException {
        return (Pedido) super.find(Pedido.class, id);
    }

    public void update(Pedido p) throws DataAccessLayerException {
        super.saveOrUpdate(p);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Pedido.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();

    }

}
