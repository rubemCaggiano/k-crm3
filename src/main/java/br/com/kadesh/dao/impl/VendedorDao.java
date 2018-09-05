package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Vendedor;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class VendedorDao extends AbstractDao {

    public VendedorDao() {
        super();
    }
    
    public void saveOrUpdate(Vendedor v) throws DataAccessLayerException{
        super.saveOrUpdate(v);
    }
    
    public void create(Vendedor v ) throws DataAccessLayerException{
        super.saveOrUpdate(v);
    }
    
    public void delete(Vendedor v) throws DataAccessLayerException{
        super.delete(v);
    }
    
    public Vendedor find(Integer id) throws DataAccessLayerException{
        return (Vendedor) super.find(Vendedor.class, id);
    }
    
    public  void update(Vendedor v ) throws DataAccessLayerException{
        super.saveOrUpdate(v);
    }
    
    public List findAll() throws DataAccessLayerException{
        return super.findAll(Vendedor.class);
    }
    
    public void closeSession() throws HibernateException{
        super.closeOperation();
    }
    
    public void closeSessionFactory() throws HibernateException{
        super.closeFactory();
    }
    
    
    
}
