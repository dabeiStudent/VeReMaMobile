package com.example.vehiclemanager;

public class SubmitResponse {
    private String message;

    public SubmitResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
