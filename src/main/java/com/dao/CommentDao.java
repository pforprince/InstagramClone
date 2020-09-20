/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entities.Comment;
import com.helpers.FactoryProvider;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author princ
 */
public class CommentDao {

    public static Comment saveComment(Comment comment) {
        Comment postedComment = null;

        try {
            Session session = FactoryProvider.getFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(comment);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postedComment;
    }

    public static List<Comment> getAllCommentsByPostId(int postId) {
        List<Comment> list = null;

        try {
            String query = "from Comment where postId=:p order by Date desc";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", postId);
            list = q.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean deleteCommentById(int commentId) {
        boolean isDeleted = false;

        try {
            String query = "Delete from Comment where id=:p";
            Session session = FactoryProvider.getFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query q = session.createQuery(query);
            q.setParameter("p", commentId);
            q.executeUpdate();
            transaction.commit();
            session.close();
            isDeleted = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isDeleted;
    }

    public static int noOfCommentsByPostId(int postId) {
        int comments = 0;
        try {
            String query = "SELECT COUNT(*) from Comment where postId=:p";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", postId);
            comments = q.list().size();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }
}
