package com.example.thuchiapplication;

public class ThuChi {

    String taikhoan;
    double sotien;
    String NgayThang;
    boolean thuChi;
    public ThuChi(){}
    public ThuChi( String taikhoan, double sotien, String ngayThang, boolean thuChi) {

        this.taikhoan = taikhoan;
        this.sotien = sotien;
        NgayThang = ngayThang;
        this.thuChi = thuChi;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public double getSotien() {
        return sotien;
    }

    public void setSotien(double sotien) {
        this.sotien = sotien;
    }

    public int isThuChi() {
        if(thuChi==true)
            return 1;
        else return 0;
    }

    public void setThuChi(boolean thuChi) {
        this.thuChi = thuChi;
    }

    public String getNgayThang() {
        return NgayThang;
    }

    public void setNgayThang(String ngayThang) {
        NgayThang = ngayThang;
    }
}
