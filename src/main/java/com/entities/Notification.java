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
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int mainUserId;
    private int interactedUserId;
    private String content;
    private Date date;
    private int postId;

    public Notification() {
    }

    public Notification(int mainUserId, int interactedUserId, String content, Date date, int postId) {
        this.mainUserId = mainUserId;
        this.interactedUserId = interactedUserId;
        this.content = content;
        this.date = date;
        this.postId = postId;
    }

    public Notification(int id, int mainUserId, int interactedUserId, String content, Date date) {
        this.id = id;
        this.mainUserId = mainUserId;
        this.interactedUserId = interactedUserId;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMainUserId() {
        return mainUserId;
    }

    public void setMainUserId(int mainUserId) {
        this.mainUserId = mainUserId;
    }

    public int getInteractedUserId() {
        return interactedUserId;
    }

    public void setInteractedUserId(int interactedUserId) {
        this.interactedUserId = interactedUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPostId() {
        return postId;
    }

    public void setImageName(int postId) {
        this.postId = postId;
    }

}
