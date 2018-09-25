package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Usuario;
import br.com.kadesh.util.DataAccessLayerException;
import br.com.kadesh.util.HibernateFactory;
import br.com.kadesh.util.HibernateSession;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

public class UsuarioDao extends AbstractDao {

    private Session session;
    private Transaction tx;

    public UsuarioDao() {
        super();
        HibernateFactory.buildIfNeeded();
    }

    public void saveOrUpdate(Usuario u) throws DataAccessLayerException {
        super.saveOrUpdate(u);
    }

    public void create(Usuario u) throws DataAccessLayerException {
        super.saveOrUpdate(u);
    }

    public void delete(Usuario u) throws DataAccessLayerException {
        super.delete(u);
    }

    public Usuario find(Integer id) throws DataAccessLayerException {
        return (Usuario) super.find(Usuario.class, id);
    }

    public void update(Usuario u) throws DataAccessLayerException {
        super.saveOrUpdate(u);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Usuario.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();
    }

    public Usuario fazerLogin(Usuario u) {
        session = HibernateSession.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Usuario usuario = new Usuario();

        try {
            String sql = "SELECT * FROM usuario where usuario = :usuario and senha = :senha";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Usuario.class);
            query.setParameter("usuario", u.getUsuario());
            query.setParameter("senha", u.getSenha());
            usuario = (Usuario) query.getSingleResult();
            session.getTransaction().commit();

        } catch (Exception e) {
            usuario = null;
        }

        if (usuario != null) {
            return usuario;
        }
        return usuario;
    }

}
