package com.example.vehiclemanager;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse {
    private String success;
    private String message;
    private String token;
    private List<User> user;

    private List<AccountDetail> detail;

    public LoginResponse(String success, String message, String token, List<User> user, List<AccountDetail> detail) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.user = user;
        this.detail = detail;
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

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<AccountDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<AccountDetail> detail) {
        this.detail = detail;
    }
}
