/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entities.Likes;
import com.entities.User;
import com.helpers.FactoryProvider;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class LikesDao {

    public static boolean postLiked(Likes like) {
        boolean liked = false;

        try {
            Session session = FactoryProvider.getFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(like);
            transaction.commit();
            session.close();
            liked = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liked;
    }

    public static boolean postUnliked(int userId, int postId) {
        boolean unliked = false;

        try {
            String query = "delete from Likes where likedUserId=:u and postId=:p";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            Transaction transaction = session.beginTransaction();
            q.setParameter("p", postId);
            q.setParameter("u", userId);
            q.executeUpdate();
            transaction.commit();
            session.close();
            unliked = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unliked;
    }

    public static long likesOfPost(int postId) {

        long totalLikes = 0;
        try {
            String query = "SELECT COUNT(*) FROM Likes where postId=:p ";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", postId);
            totalLikes = (long) q.uniqueResult();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalLikes;
    }

    public static boolean isPostLikedByMe(int userId, int PostId) {
        boolean liked = false;
        Likes dummyObject = null;
        try {
            String query = "from Likes where postId=:p and likedUserId=:u";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", PostId);
            q.setParameter("u", userId);
            dummyObject = (Likes) q.uniqueResult();
            if (dummyObject != null) {
                liked = true;
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liked;
    }

    public static List<Likes> postLikedBy(int postId) {
        List<Likes> likesList = null;
        try {
            String query = "from Likes where postId=:p";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", postId);
            likesList = q.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return likesList;
    }    
}
