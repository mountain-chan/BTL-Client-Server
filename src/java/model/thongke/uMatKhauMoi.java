
package model.thongke;

import org.hibernate.validator.constraints.NotEmpty;


public class uMatKhauMoi {
    private String matKhau ;
    private String matKhauMoi ; 
    private String xacThucMatKhau;

    
    public boolean xacThuc(){
        return matKhauMoi.equals(xacThucMatKhau);
    }
    @NotEmpty(message = "Mật khẩu không trống")
    public String getMatKhau() {
        return matKhau;
    }
    
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    @NotEmpty(message = "Mật khẩu mới không trống")
    public String getMatKhauMoi() {
        return matKhauMoi;
    }

    public void setMatKhauMoi(String matKhauMoi) {
        this.matKhauMoi = matKhauMoi;
    }
    @NotEmpty(message = "Xác thực mật khẩu không trống")
    public String getXacThucMatKhau() {
        return xacThucMatKhau;
    }

    public void setXacThucMatKhau(String xacThucMatKhau) {
        this.xacThucMatKhau = xacThucMatKhau;
    }
}
