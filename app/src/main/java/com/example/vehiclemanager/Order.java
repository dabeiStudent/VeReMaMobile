package com.example.vehiclemanager;

import java.io.Serializable;

public class Order implements Serializable {
    private String ma_psc;
    private int ma_nv;
    private String bien_so;
    private String ten_kh;
    private String ngay_nhan;
    private String ngay_sua;
    private String tg_du_kien;
    private int id_dv;
    private long tong_tien;
    private String ten_xe;
    private String img;
    private String trang_thai;

    public Order(String ma_psc, int ma_nv, String bien_so, String ten_kh, String ngay_nhan, String ngay_sua, String tg_du_kien, int id_dv, long tong_tien, String ten_xe, String img, String trang_thai) {
        this.ma_psc = ma_psc;
        this.ma_nv = ma_nv;
        this.bien_so = bien_so;
        this.ten_kh = ten_kh;
        this.ngay_nhan = ngay_nhan;
        this.ngay_sua = ngay_sua;
        this.tg_du_kien = tg_du_kien;
        this.id_dv = id_dv;
        this.tong_tien = tong_tien;
        this.ten_xe = ten_xe;
        this.img = img;
        this.trang_thai = trang_thai;
    }

    public String getMa_psc() {
        return ma_psc;
    }

    public void setMa_psc(String ma_psc) {
        this.ma_psc = ma_psc;
    }

    public int getMa_nv() {
        return ma_nv;
    }

    public void setMa_nv(int ma_nv) {
        this.ma_nv = ma_nv;
    }

    public String getBien_so() {
        return bien_so;
    }

    public void setBien_so(String bien_so) {
        this.bien_so = bien_so;
    }

    public String getTen_kh() {
        return ten_kh;
    }

    public void setTen_kh(String ten_kh) {
        this.ten_kh = ten_kh;
    }

    public String getNgay_nhan() {
        return ngay_nhan;
    }

    public void setNgay_nhan(String ngay_nhan) {
        this.ngay_nhan = ngay_nhan;
    }

    public String getNgay_sua() {
        return ngay_sua;
    }

    public void setNgay_sua(String ngay_sua) {
        this.ngay_sua = ngay_sua;
    }

    public String getTg_du_kien() {
        return tg_du_kien;
    }

    public void setTg_du_kien(String tg_du_kien) {
        this.tg_du_kien = tg_du_kien;
    }

    public int getId_dv() {
        return id_dv;
    }

    public void setId_dv(int id_dv) {
        this.id_dv = id_dv;
    }

    public long getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(long tong_tien) {
        this.tong_tien = tong_tien;
    }

    public String getTen_xe() {
        return ten_xe;
    }

    public void setTen_xe(String ten_xe) {
        this.ten_xe = ten_xe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }
}
