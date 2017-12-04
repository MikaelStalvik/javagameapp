package com.imploded.javagameapp.models;

/**
 * Created by Mikael on 2017-12-04.
 */

public class LoginRequest {

    private String username;
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    private String password;
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
