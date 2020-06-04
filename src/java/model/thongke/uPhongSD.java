package model.thongke;

import java.util.Date;
import model.Phong;
import model.TaiKhoan;

public class uPhongSD {

    private Phong phong;
    private TaiKhoan taiKhoan;
    private Date ngayDat;
    private Date ngayDen;
    private Date ngayTra;
    private int thanhTien;

    public uPhongSD() {
    }

    public uPhongSD(Phong phong, TaiKhoan taiKhoan, Date ngayDat, Date ngayDen, Date ngayTra, int thanhTien) {
        this.phong = phong;
        this.taiKhoan = taiKhoan;
        this.ngayDat = ngayDat;
        this.ngayDen = ngayDen;
        this.ngayTra = ngayTra;
        this.thanhTien = thanhTien;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Date getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

 

}
