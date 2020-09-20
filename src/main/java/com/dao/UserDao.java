/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entities.User;
import com.helpers.FactoryProvider;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class UserDao {

    public static User saveUser(User user) throws ConstraintViolationException, Exception {
        User returnUser = null;

        Session session = FactoryProvider.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        returnUser = user;

        return returnUser;
    }

    public static User updateUser(User user) throws ConstraintViolationException {
        User returnUser = null;

        Session session = FactoryProvider.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        returnUser = user;

        return returnUser;
    }

    public static User loginUsingEmailAndPW(String email, String password) {
        User user = null;

        try {

            Session session = FactoryProvider.getFactory().openSession();
            String query = "from User where email=:e and password=: p";
            Query q = session.createQuery(query);
            q.setParameter("e", email);
            q.setParameter("p", password);
            user = (User) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<User> searchUser(String username, int userId) {
        List<User> list = null;

        try {
            String query = "from User where username like :p";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", "%" + username + "%");
            list = q.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list.size() == 1) {
            if (list.get(0).getId() == userId) {
                list.remove(0);
            }
        } else {
            for (User user : list) {
                if (user.getId() == userId) {
                    list.remove(user);
                }
            }
        }
        return list;
    }

    public static User fetchUserByUsername(String username) {
        User user = null;

        try {
            String query = "from User where username=:p";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", username);
            user = (User) q.uniqueResult();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User fetchUserById(int id) {
        User user = null;

        try {
            String query = "from User where id=:p";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", id);
            user = (User) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User isUsernameAvailable(String username) {
        User user = null;
        try {
            String query = "from User where username=:u";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("u", username);
            user = (User) q.uniqueResult();

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
