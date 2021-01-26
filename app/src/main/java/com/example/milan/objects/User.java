package com.example.milan.objects;

import java.util.UUID;

public class User {
    private String uid;
    private String username;
    private String mail;
    private String phone;

    public User() {}

    public User(String username, String first_name, String last_name, String mail, String phone){
        this.uid = UUID.randomUUID().toString();
        this.username = username;
        this.mail = mail;
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
