/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author princ
 */
public class FactoryProvider {

    static SessionFactory sessionFactory;

    public static SessionFactory getFactory() {
        try {
            if (sessionFactory == null) {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;

    }

}
