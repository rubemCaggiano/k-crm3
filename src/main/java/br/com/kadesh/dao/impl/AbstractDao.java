package br.com.kadesh.dao.impl;


import br.com.kadesh.util.DataAccessLayerException;
import br.com.kadesh.util.HibernateFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;



import org.hibernate.Query;

import java.util.List;

public abstract class AbstractDao{
	private Session session;
	private Transaction tx;

	public AbstractDao() {
		HibernateFactory.buildIfNeeded();
	}
	
	protected void delete(Object obj) {
		try {
			startOperation();
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			HibernateFactory.close(session);
		}
	}

	protected Object find(Class clas, Integer id) {
		Object obj = null;
		try {
			startOperation();
			obj = session.load(clas, id);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
//			HibernateFactory.close(session);
		}
		return obj;
	}

	@SuppressWarnings("deprecation")
	protected List findAll(Class clas) {
		List objects = null;
		try {
			startOperation();
			Query query = session.createQuery("from " + clas.getName());
			objects = query.list();
			tx.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			HibernateFactory.close(session);
		}
		return objects;
	}

	protected void saveOrUpdate(Object obj) {
		try {
			startOperation();
			session.merge(obj);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			HibernateFactory.close(session);
		}
	}

	protected void handleException(HibernateException e) throws DataAccessLayerException {
		HibernateFactory.rollback(tx);
		throw new DataAccessLayerException(e);
	}

	protected void startOperation() throws HibernateException {
		session = HibernateFactory.openSession();
		tx = session.beginTransaction();
	}
	
	protected void closeOperation() throws HibernateException {
		HibernateFactory.close(session);
	}
	
	protected void closeFactory() throws HibernateException {
		HibernateFactory.closeFactory();
	}
}