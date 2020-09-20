/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

/**
 *
 * @author princ
 */
public class AlertMessage {

    private String cssClass;
    private String message;
    private String description;

    public AlertMessage() {
    }

    public AlertMessage(String cssClass, String message, String description) {
        this.cssClass = cssClass;
        this.message = message;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
