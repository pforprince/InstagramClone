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

/**
 *
 * @author princ
 */
@Entity
public class Followers {

    public Followers(int followerId, int followingId, Date date) {
        this.followerId = followerId;
        this.followingId = followingId;
        this.date = date;
    }

    public Followers() {
    }

    public Followers(int id, int followerId, int followingId, Date date) {
        this.id = id;
        this.followerId = followerId;
        this.followingId = followingId;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int followerId;
    private int followingId;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
