package com.example.vehiclemanager;

import com.google.gson.annotations.SerializedName;

public class AccountDetail {
    private String ten;
    private String dia_chi;
    private String sdt;

    public AccountDetail(String ten, String dia_chi, String sdt) {
        this.ten = ten;
        this.dia_chi = dia_chi;
        this.sdt = sdt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
