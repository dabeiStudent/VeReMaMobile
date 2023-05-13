package com.example.vehiclemanager;

public class Staff {
    private String ma_nv,ten_nv,gioi_tinh,dia_chi,sdt,ngay_sinh,ngay_gianhap,luong,ten_tk;

    public Staff(String ma_nv, String ten_nv, String gioi_tinh, String dia_chi, String sdt, String ngay_sinh, String ngay_gianhap, String luong, String ten_tk) {
        this.ma_nv = ma_nv;
        this.ten_nv = ten_nv;
        this.gioi_tinh = gioi_tinh;
        this.dia_chi = dia_chi;
        this.sdt = sdt;
        this.ngay_sinh = ngay_sinh;
        this.ngay_gianhap = ngay_gianhap;
        this.luong = luong;
        this.ten_tk = ten_tk;
    }

    public String getMa_nv() {
        return ma_nv;
    }

    public void setMa_nv(String ma_nv) {
        this.ma_nv = ma_nv;
    }

    public String getTen_nv() {
        return ten_nv;
    }

    public void setTen_nv(String ten_nv) {
        this.ten_nv = ten_nv;
    }

    public String getGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(String gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
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

    public String getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(String ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public String getNgay_gianhap() {
        return ngay_gianhap;
    }

    public void setNgay_gianhap(String ngay_gianhap) {
        this.ngay_gianhap = ngay_gianhap;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public String getTen_tk() {
        return ten_tk;
    }

    public void setTen_tk(String ten_tk) {
        this.ten_tk = ten_tk;
    }
}
