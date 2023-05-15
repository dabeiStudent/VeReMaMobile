package com.example.vehiclemanager;

import java.io.Serializable;

public class Customer implements Serializable {
    private String ma_kh, ten,dia_chi,sdt,ten_tk;

    public Customer(String ma_kh, String ten, String dia_chi, String sdt, String ten_tk) {
        this.ma_kh = ma_kh;
        this.ten = ten;
        this.dia_chi = dia_chi;
        this.sdt = sdt;
        this.ten_tk = ten_tk;
    }

    public String getMa_kh() {
        return ma_kh;
    }

    public void setMa_kh(String ma_kh) {
        this.ma_kh = ma_kh;
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

    public String getTen_tk() {
        return ten_tk;
    }

    public void setTen_tk(String ten_tk) {
        this.ten_tk = ten_tk;
    }
}
