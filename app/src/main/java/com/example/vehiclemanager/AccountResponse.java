package com.example.vehiclemanager;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountResponse implements Serializable {
    @SerializedName("success")
    private String success;
    private ArrayList<Account> allAccounts;

    public AccountResponse(String success, ArrayList<Account> allAccounts) {
        this.success = success;
        this.allAccounts = allAccounts;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    public void setAllAccounts(ArrayList<Account> allAccounts) {
        this.allAccounts = allAccounts;
    }
}
