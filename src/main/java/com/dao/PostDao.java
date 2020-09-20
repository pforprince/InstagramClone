/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entities.Followers;
import com.entities.Post;
import com.helpers.FactoryProvider;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author princ
 */
public class PostDao {

    public static Post savePost(Post post) {
        Post returnPost = null;
        try {

            Session session = FactoryProvider.getFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
            session.close();
            returnPost = post;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnPost;
    }

    public static boolean deletePost(int postId) {
        boolean deleted = false;
        try {
            String query = "Delete from Post where Postid=:p";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", postId);
            Transaction transaction = session.beginTransaction();
            q.executeUpdate();
            transaction.commit();
            session.close();
            deleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }

    public static Post updatePost(Post post) {
        Post retunedPost = null;
        try {
            Session session = FactoryProvider.getFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(post);
            transaction.commit();
            session.close();
            retunedPost = post;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retunedPost;
    }

    public static List<Post> getUsersPost(int id) {
        List<Post> list = null;

        try {
            Session session = FactoryProvider.getFactory().openSession();
            String query = "from Post where userId=:p ORDER BY PostId DESC ";
            Query q = session.createQuery(query);
            q.setParameter("p", id);
            list = q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static int getNoOfPosts(int id) {
        int posts = 0;

        try {
            Session session = FactoryProvider.getFactory().openSession();
            String query = "from Post where userId=:p";
            Query q = session.createQuery(query);
            q.setParameter("p", id);
            posts = q.list().size();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static Post getPostById(int id) {
        Post post = null;

        try {
            String query = "from Post where postId=:p";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("p", id);
            post = (Post) q.uniqueResult();
            session.close();
        } catch (Exception e) {
        }
        return post;
    }

    public static List<Post> getFollowingsPost(int userId) {
        List<Post> followingsPosts = null;

        try {
            String query = "from Post where userId=:u order by PostId Desc";
            Session session = FactoryProvider.getFactory().openSession();
            Query q = session.createQuery(query);
            q.setParameter("u", userId);
            followingsPosts = q.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return followingsPosts;
    }

    public static List<Post> getPostsForHomeByID(List<Followers> following, int userId) {
        List<Post> followingsPosts = null;
        List<Post> latestPosts = null;
        try {
            Session session = FactoryProvider.getFactory().openSession();

            for (Followers followingId : following) {
                String query = "from Post where userId=:u";
                Query q = session.createQuery(query);
                q.setParameter("u", followingId.getFollowingId());
                List<Post> list = q.list();
                if (followingsPosts == null) {
                    followingsPosts = list;
                } else {
                    followingsPosts.addAll(list);
                    List<Post> myposts = getUsersPost(userId);
                    followingsPosts.addAll(myposts);
                }
            }
            session.close();
            latestPosts = getLatestPosts(followingsPosts);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return latestPosts;
    }

    public static List<Post> getLatestPosts(List<Post> followingsPosts) {
        List<Post> latestPosts = followingsPosts;
        try {
            Collections.sort(latestPosts, (Post post1, Post post2) -> {
                if (post1.getPostid() < post2.getPostid()) {
                    return 1;
                }
                return -1;
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return latestPosts;
    }

}
