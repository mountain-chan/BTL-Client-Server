package model.thongke;

import model.Phong;


public class uPhongT {
    private Phong phong;
    private String tenThanhPho;

    public uPhongT() {
    }

    public uPhongT(Phong phong, String tenThanhPho) {
        this.phong = phong;
        this.tenThanhPho = tenThanhPho;
      
    }

    public String getTenThanhPho() {
        return tenThanhPho;
    }

    public void setTenThanhPho(String tenThanhPho) {
        this.tenThanhPho = tenThanhPho;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }
}
