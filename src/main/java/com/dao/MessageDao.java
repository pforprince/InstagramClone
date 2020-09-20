/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entities.Message;
import com.entities.User;
import com.helpers.FactoryProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author princ
 */
public class MessageDao {

    public static boolean saveMessage(Message message) {
        boolean isSaved = false;

        try {
            Session session = FactoryProvider.getFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(message);
            transaction.commit();
            session.close();
            isSaved = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSaved;
    }

    public static List<Message> getMessages(int senderId, int recieverId) {
        List<Message> messages = null;

        try {
            String query = "from Message where senderId=:s and recieverId=:r order by date desc";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("s", senderId);
            q.setParameter("r", recieverId);
            messages = q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }

    public static List<Message> getLatestMessages(int senderId, int recieverId) {
        List<Message> latestMessages = null;
        List senderMessages = getMessages(senderId, recieverId);
        List recieverMessages = getMessages(recieverId, senderId);

        latestMessages = senderMessages;
        if (latestMessages != null) {
            latestMessages.addAll(recieverMessages);
        } else {
            latestMessages = recieverMessages;
        }
        Collections.sort(latestMessages,
                (Message m1, Message m2) -> {
                    if (m1.getMessageId() > m2.getMessageId()) {
                        return 1;
                    }
                    return -1;
                }
        );

        return latestMessages;
    }

    public static List<Message> senderMessages(int userId) {
        List<Message> sendedMessages = null;

        try {
            String query = "SELECT DISTINCT m FROM Message m where senderId=:p order by messageId DESC ";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", userId);
            sendedMessages = q.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sendedMessages;
    }

    public static List<Message> recieverMessages(int userId) {
        List<Message> recievedMessages = null;

        try {
            String query = "SELECT DISTINCT m FROM Message m where recieverId=:p order by messageId DESC ";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", userId);
            recievedMessages = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recievedMessages;
    }

    public static List<User> getAllMessagesOfUser(int userId) {
        List<Message> getAllMessagedUsers = null;
        List<User> afterRemovingDuplicates = new ArrayList<>();

        try {

            List<Message> senderSide = senderMessages(userId);
            List<Message> recieverSide = recieverMessages(userId);

            getAllMessagedUsers = senderSide;
            if (getAllMessagedUsers == null || getAllMessagedUsers.isEmpty()) {
                getAllMessagedUsers = recieverSide;
            } else {
                getAllMessagedUsers.addAll(recieverSide);
            }

            Collections.sort(getAllMessagedUsers, (Message u1, Message u2) -> {

                if (u1.getMessageId() < u2.getMessageId()) {
                    return 1;
                } else {
                    return -1;
                }
            });
            Set<Integer> set = new LinkedHashSet();
            for (Message message : getAllMessagedUsers) {
                if (!set.contains(message.getSenderId())) {
                    set.add(message.getSenderId());
                }
                if (!set.contains(message.getRecieverId())) {
                    set.add(message.getRecieverId());

                }
            }
            for (int x : set) {
                afterRemovingDuplicates.add(UserDao.fetchUserById(x));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return afterRemovingDuplicates;
    }
}
