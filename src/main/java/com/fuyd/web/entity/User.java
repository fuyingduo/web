package com.fuyd.web.entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 9200315871109541104L;
    private Long id;

    private String name;

    private String token;

    private Integer tokenTime;

    private String username;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(Integer tokenTime) {
        this.tokenTime = tokenTime;
    }

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
