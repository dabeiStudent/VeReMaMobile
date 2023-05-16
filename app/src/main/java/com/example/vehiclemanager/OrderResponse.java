package com.example.vehiclemanager;

import java.util.ArrayList;

public class OrderResponse {
    private ArrayList<Order> allOrders;

    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(ArrayList<Order> allOrders) {
        this.allOrders = allOrders;
    }
}
