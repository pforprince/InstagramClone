/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author princ
 */
@Entity
@Table(name = "likes")
public class Likes {

    public Likes(int likeId, int likedUserId, Date date, int postId) {
        this.likeId = likeId;
        this.likedUserId = likedUserId;
        this.date = date;
        this.postId = postId;
    }

    public Likes() {
    }

    public Likes(int likedUserId, Date date, int postId) {
        this.likedUserId = likedUserId;
        this.date = date;
        this.postId = postId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeId;
    private int likedUserId;
    private Date date;
    private int postId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getLikedUserId() {
        return likedUserId;
    }

    public void setLikedUserId(int likedUserId) {
        this.likedUserId = likedUserId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
