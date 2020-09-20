/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.Date;
import javax.persistence.Column;
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
@Table(name = "Posts")
public class Post {

    public Post(String caption, String tags, String imageName, int userId, Date date) {
        this.caption = caption;
        this.tags = tags;
        this.imageName = imageName;
        this.userId = userId;
        this.date = date;
    }

    public Post(int Postid, String caption, String tags, String imageName, int userId, Date date) {
        this.Postid = Postid;
        this.caption = caption;
        this.tags = tags;
        this.imageName = imageName;
        this.userId = userId;
        this.date = date;
    }

    public Post() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Postid;
    
    @Column(length = 5000)
    private String caption;
    private String tags;
    private String imageName;
    private int userId;
    private Date date;

    public int getPostid() {
        return Postid;
    }

    public void setPostid(int Postid) {
        this.Postid = Postid;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
