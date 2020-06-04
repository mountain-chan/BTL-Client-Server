package model.thongke;

import model.DatPhong;
import model.Phong;
import model.TaiKhoan;

public class uThongBao {

    private TaiKhoan taiKhoan;
    private DatPhong datPhong;
    private Phong phong;

    public uThongBao() {
    }

    public uThongBao(TaiKhoan taiKhoan, DatPhong datPhong, Phong phong ) {
        this.taiKhoan = taiKhoan;
        this.datPhong = datPhong;
        this.phong = phong;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public DatPhong getDatPhong() {
        return datPhong;
    }

    public void setDatPhong(DatPhong datPhong) {
        this.datPhong = datPhong;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }
}
