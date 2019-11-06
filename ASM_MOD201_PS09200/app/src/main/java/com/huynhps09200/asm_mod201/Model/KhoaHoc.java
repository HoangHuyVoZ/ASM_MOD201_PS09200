package com.huynhps09200.asm_mod201.Model;

public class KhoaHoc {
public String maKH,Mon,GV,NgayBD,NgayKT,Mota;

    public KhoaHoc(String maKH, String mon, String GV, String ngayBD, String ngayKT, String mota) {
        this.maKH = maKH;
        this.Mon = mon;
        this.GV = GV;
        this.NgayBD = ngayBD;
        this.NgayKT = ngayKT;
        this.Mota = mota;
    }

    public KhoaHoc() {
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMon() {
        return Mon;
    }

    public void setMon(String mon) {
        Mon = mon;
    }

    public String getGV() {
        return GV;
    }

    public void setGV(String GV) {
        this.GV = GV;
    }

    public String getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(String ngayBD) {
        NgayBD = ngayBD;
    }

    public String getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(String ngayKT) {
        NgayKT = ngayKT;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }
}
