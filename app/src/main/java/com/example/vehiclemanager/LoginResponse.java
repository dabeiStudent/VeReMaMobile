package com.example.vehiclemanager;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse {
    private String success;
    private String message;
    private String token;
    private ListUser listuser;


    public LoginResponse(String success, String message, String token, ListUser listuser) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.listuser = listuser;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ListUser getUser() {
        return listuser;
    }

    public void setUser(ListUser user) {
        this.listuser = listuser;
    }
}
