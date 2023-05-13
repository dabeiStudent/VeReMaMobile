package com.example.vehiclemanager;

import java.util.ArrayList;

public class CustomerResponse {
    private ArrayList<Customer> allCustomers;

    public ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public void setAllCustomers(ArrayList<Customer> allCustomers) {
        this.allCustomers = allCustomers;
    }
}
