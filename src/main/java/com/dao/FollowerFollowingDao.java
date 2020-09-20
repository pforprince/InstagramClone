/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entities.Followers;
import com.entities.User;
import com.helpers.FactoryProvider;
import com.sun.prism.impl.BaseMesh;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class FollowerFollowingDao {

    public static boolean followUser(int followerId, int followingId) {
        boolean output = false;

        try {
            Followers followers = new Followers(followerId, followingId, new Date());
            Session session = FactoryProvider.getFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(followers);
            transaction.commit();
            session.close();
            output = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public static boolean isFollowed(int followerId, int followingId) {
        boolean output = false;

        try {
            String query = "from Followers where followerId=:f1 and followingId=:f2";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("f1", followerId);
            q.setParameter("f2", followingId);
            Followers followers = (Followers) q.uniqueResult();
            if (followers != null) {
                output = true;
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public static long getNoOfFollowing(int userId) {
        long following = 0;

        try {

            String query = "Select count(*) from Followers where followerId=:p";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", userId);
            following = (long) q.uniqueResult();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return following;
    }

    public static long getNoOfFollowers(int userId) {
        long followers = 0;
        try {
            String query = "Select count(*) from Followers where followingId=:p";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", userId);
            followers = (long) q.uniqueResult();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return followers;
    }

    public static boolean unfollow(int followerId, int followingId) {
        boolean isDeleted = false;
        try {
            String query = "delete from Followers where followerId=:followerId and followingId=:followingId";
            Session session = FactoryProvider.getFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query q = session.createQuery(query);
            q.setParameter("followerId", followerId);
            q.setParameter("followingId", followingId);
            q.executeUpdate();
            transaction.commit();
            isDeleted = true;
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    public static List<Followers> getFollowersById(int id) {
        List<Followers> followers = null;

        try {
            String query = "from Followers where followingId=:id";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("id", id);
            followers = q.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return followers;
    }

    public static List<Followers> getFollowingsById(int id) {
        List<Followers> followers = null;

        try {
            String query = "from Followers where followerId=:id";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("id", id);
            followers = q.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return followers;
    }
    
    
}
