package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Usuario;
import br.com.kadesh.util.DataAccessLayerException;
import br.com.kadesh.util.HibernateFactory;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDao extends AbstractDao {

    private Session session;
    private Transaction tx;

    public UsuarioDao() {
        super();
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
        Usuario usuario = new Usuario();

        try {
            startOperation();
            String sql = "SELECT * FROM usuario where nome = :nome and senha = :senha";
            Query query = session.createQuery(sql);
            query.setParameter("nome", u.getNome());
            query.setParameter("senha", u.getSenha());

            usuario = (Usuario) query.getSingleResult();
            tx.commit();

        } catch (Exception e) {
            usuario = null;
        } finally {
            HibernateFactory.close(session);
        }

        if (usuario != null) {
            return usuario;
        }
        return usuario;
    }

}
