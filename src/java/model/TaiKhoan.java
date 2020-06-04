package model;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;

public class TaiKhoan implements Serializable {

    private static final long serialVersionUID = 45312311L;
    
    String tenTaiKhoan;
    String matKhau;
    String hoTen;
    boolean gioiTinh;
    String soDienThoai;
    String email;
    boolean isAdmin;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenTaiKhoan, String matKhau, String hoTen, boolean gioiTinh, String soDienThoai, String email, boolean isAdmin) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.isAdmin = isAdmin;
    }
    
    public TaiKhoan(TaiKhoan tk){
        this.tenTaiKhoan = tk.tenTaiKhoan;
        this.matKhau = tk.matKhau;
        this.hoTen = tk.hoTen;
        this.gioiTinh = tk.gioiTinh;
        this.soDienThoai = tk.soDienThoai;
        this.email = tk.email;
        this.isAdmin = tk.isAdmin;
    }
    
    public void reload(String tenTaiKhoan, String matKhau, String hoTen, boolean gioiTinh, String soDienThoai, String email, boolean isAdmin) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    @NotEmpty(message = "Tài khoản không trống")
    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }
    
    @NotEmpty(message = "Mât khẩu không trống")
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @NotEmpty(message = "Họ tên không trống")
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @NotEmpty(message = "Số điện thoại không trống")
    public String getSoDienThoai() {
        return soDienThoai;
    }
    
    
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    
    @NotEmpty(message = "Email không trống")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
}
