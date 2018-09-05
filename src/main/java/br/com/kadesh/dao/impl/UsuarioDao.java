package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Usuario;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class UsuarioDao extends AbstractDao{

    public UsuarioDao() {
        super();
    }
    
    public void saveOrUpdate(Usuario u) throws DataAccessLayerException{
        super.saveOrUpdate(u);
    }
    
    public void create(Usuario u) throws DataAccessLayerException{
        super.saveOrUpdate(u);
    }
    
    public void delete (Usuario u) throws DataAccessLayerException{
        super.delete(u);
    }
    
    public Usuario find(Integer id) throws DataAccessLayerException{
        return (Usuario) super.find(Usuario.class, id);
    }
    
    public void update(Usuario u) throws DataAccessLayerException{
        super.saveOrUpdate(u);
    }
    
    public List findAll() throws DataAccessLayerException{
        return super.findAll(Usuario.class);
    }
    
    public void closeSession() throws HibernateException{
        super.closeOperation();
    }
    
    public void closeSessionFactory() throws HibernateException{
        super.closeFactory();
    }
    
}