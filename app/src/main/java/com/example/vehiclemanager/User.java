package com.example.vehiclemanager;

import java.io.Serializable;

public class User implements Serializable {

    private String ma_tk;
    private String ten_tk;
    private String mat_khau;
    private String quyen;
    public User(){

    }

    public User(String ma_tk, String ten_tk, String mat_khau, String quyen) {
        this.ma_tk = ma_tk;
        this.ten_tk = ten_tk;
        this.mat_khau = mat_khau;
        this.quyen = quyen;
    }
    public String getMa_tk() {
        return ma_tk;
    }

    public void setMa_tk(String ma_tk) {
        this.ma_tk = ma_tk;
    }

    public String getTen_tk() {
        return ten_tk;
    }

    public void setTen_tk(String ten_tk) {
        this.ten_tk = ten_tk;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

}
