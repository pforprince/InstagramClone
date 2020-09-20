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
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;
    private String message;
    private int senderId;
    private int recieverId;
    private Date date;

    public Message() {
    }

    public Message(String message, int senderId, int recieverId, Date date) {
        this.message = message;
        this.senderId = senderId;
        this.recieverId = recieverId;
        this.date = date;
    }

    public Message(int messageId, String message, int senderId, int recieverId, Date date) {
        this.messageId = messageId;
        this.message = message;
        this.senderId = senderId;
        this.recieverId = recieverId;
        this.date = date;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(int recieverId) {
        this.recieverId = recieverId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
