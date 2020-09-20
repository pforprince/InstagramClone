/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author princ
 */
@Entity
public class User {

    public User(int id, String name, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User(int id, String name, String email, String username) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public User(String name, String email, String password, String username) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User(String name, String email, String password, String username, String dpName) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.dpName = dpName;
    }

    public User(int id, String name, String email, String password, String username, String dpName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dpName = dpName;
        this.password = password;
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String dpName;

    public String getDpName() {
        return dpName;
    }

    public void setDpName(String dpName) {
        this.dpName = dpName;
    }

    public User() {
    }
    private String password;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
