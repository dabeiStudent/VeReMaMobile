package com.example.vehiclemanager;

public class LoginResponse {
    private String success;
    private String message;
    private User user;

    public LoginResponse(String success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
