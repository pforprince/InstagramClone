/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entities.Notification;
import com.helpers.FactoryProvider;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author princ
 */
public class NotificationDao {

    public static boolean saveNotification(Notification notification) {
        boolean isSaved = false;
        try {
            Session session = FactoryProvider.getFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(notification);
            transaction.commit();
            session.close();
            isSaved = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSaved;
    }

    public static List<Notification> getAllNotifications(int userId) {
        List<Notification> notifications = null;
        try {
            String query = "from Notification where mainUserId=:id ORDER BY id desc ";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("id", userId);
            notifications = q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notifications;

    }

}
