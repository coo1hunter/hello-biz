package com.biz.std.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;


@Entity
@Table(name = "admin")
public class Admin {

    @javax.persistence.Id
    @Column(name = "username",length = 20)
    private String username;

    @Column(name = "password",length = 30)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
