/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kadesh.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 *
 * @author Carbono
 */
public class HibernateSession {
    
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try{
            return new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex){
            ex.printStackTrace();
            System.err.println("Falha na criação da SessionFactory. " + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
    
}
