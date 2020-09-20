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
@Table(name = "Comments")
public class Comment {

    public Comment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Comment(int postId, int userId, String comment, Date date) {
        this.postId = postId;
        this.userId = userId;
        this.comment = comment;
        this.date = date;
    }

    public Comment(int id, int postId, int userId, String comment, Date date) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.comment = comment;
        this.date = date;
    }
    private int postId;
    private int userId;
    private String comment;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
