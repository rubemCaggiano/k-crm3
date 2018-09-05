/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kadesh.dao.impl;

import br.com.kadesh.model.Setor;
import br.com.kadesh.util.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Fabio
 */
public class SetorDao extends AbstractDao{

    public SetorDao() {
        super();
    }
    
    public void saveOrUpdate(Setor se) throws DataAccessLayerException {
        super.saveOrUpdate(se);
    }

    public void create(Setor se) throws DataAccessLayerException {
        super.saveOrUpdate(se);
    }

    public void delete(Setor se) throws DataAccessLayerException {
        super.delete(se);
    }

    public Setor find(Integer id) throws DataAccessLayerException {
        return (Setor) super.find(Setor.class, id);
    }

    public void update(Setor se) throws DataAccessLayerException {
        super.saveOrUpdate(se);
    }

    public List findAll() throws DataAccessLayerException {
        return super.findAll(Setor.class);
    }

    public void closeSession() throws HibernateException {
        super.closeOperation();
    }

    public void closeSessionFactory() throws HibernateException {
        super.closeFactory();
    }
    
  }
