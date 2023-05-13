package com.example.vehiclemanager;

import java.util.ArrayList;

public class StaffResponse {
    private ArrayList<Staff> allStaffs;

    public StaffResponse(ArrayList<Staff> allStaffs) {
        this.allStaffs = allStaffs;
    }

    public ArrayList<Staff> getAllStaffs() {
        return allStaffs;
    }

    public void setAllStaffs(ArrayList<Staff> allStaffs) {
        this.allStaffs = allStaffs;
    }
}
