package com.example.vehiclemanager;

import java.util.ArrayList;

public class OrderStaffResponse {
    private String message;
    private ArrayList<Order> orders;

    public OrderStaffResponse(String message, ArrayList<Order> orders) {
        this.message = message;
        this.orders = orders;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
