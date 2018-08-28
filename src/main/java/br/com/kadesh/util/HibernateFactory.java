package br.com.kadesh.util;


import org.hibernate.*;
import org.hibernate.cfg.Configuration;



public class HibernateFactory {
	private static SessionFactory sessionFactory;

	/**
	 * Constructs a new Singleton SessionFactory
	 * @return
	 * @throws HibernateException
	 */
	public static SessionFactory buildSessionFactory() throws HibernateException {
		if (sessionFactory != null) {
			closeFactory();
		}
		return configureSessionFactory();
	}

	/**
	 * Builds a SessionFactory
	 */
	public static SessionFactory buildIfNeeded() throws DataAccessLayerException {
		if (sessionFactory != null) {
			return sessionFactory;
		}
		try {
			return configureSessionFactory();
		} catch (HibernateException e) {
			throw new DataAccessLayerException(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session openSession() throws HibernateException {
		buildIfNeeded();
		return sessionFactory.openSession();
	}

	public static void closeFactory() {
		if (sessionFactory != null) {
			try {
				sessionFactory.close();
			} catch (HibernateException ignored) {
				System.out.println("Couldn't close SessionFactory: " + ignored);
			}
		}
	}

	public static void close(Session session) {
		if (session != null) {
			try {
				session.close();
			} catch (HibernateException ignored) {
				System.out.println("Couldn't close Session: " + ignored);
			}
		}
	}

	public static void rollback(Transaction tx) {
		try {
			if (tx != null) {
				tx.rollback();
			}
		} catch (HibernateException ignored) {
			System.out.println("Couldn't rollback Transaction: " + ignored);
		}
	}

	/**
	 * @return
	 * @throws HibernateException
	 */
	private static SessionFactory configureSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.configure();
		sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}
}