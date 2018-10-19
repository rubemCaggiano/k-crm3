package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Agendamento;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

public class AgendamentoDao extends AbstractDao {

    public AgendamentoDao() {
        super();
    }
    
    public void saveOrUpdate(Agendamento a) throws DataAccessLayerException {
        super.saveOrUpdate(a);
    }

    public void create(Agendamento a) throws DataAccessLayerException {
        super.saveOrUpdate(a);
    }

    public void delete(Agendamento a) throws DataAccessLayerException {
        super.delete(a);
    }

    public Agendamento find(Integer id) throws DataAccessLayerException {
        return (Agendamento) super.find(Agendamento.class, id);
    }

    public void update(Agendamento a) throws DataAccessLayerException {
        super.saveOrUpdate(a);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Agendamento.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();

    }
}
