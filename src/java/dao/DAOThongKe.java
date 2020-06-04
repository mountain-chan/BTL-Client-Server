package dao;

import model.thongke.uBieuDo;
import model.thongke.uBieuDo2GT;
import model.thongke.uPhongSD;
import model.thongke.uPhongT;
import model.thongke.uThongBao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.DatPhong;
import model.Phong;
import model.TaiKhoan;


public class DAOThongKe {

    private static Connection con;

    // ------------------------------ Tổng quan  -------------------------------
    // -------------------------------------------------------------------------
    public static int getTongSoKhachThue(Date ngaylay) {
        try {
            con = SQLConnection.getConnection();
            PreparedStatement st = con.prepareStatement("exec layTongSoKhachDangThue ?");
            st.setEscapeProcessing(true);
            st.setQueryTimeout(90);
            st.setDate(1, new java.sql.Date(ngaylay.getTime()));

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("Tong");
            }

        } catch (Exception e) {
        }
        return 0;
    }

    public static int getTongPhongTrong(Date ngaylay) {
        try {
            con = SQLConnection.getConnection();
            PreparedStatement st = con.prepareStatement("exec laySoPhongTrong ?");
            st.setEscapeProcessing(true);

            st.setDate(1, new java.sql.Date(ngaylay.getTime()));

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("Tong");
            }

        } catch (Exception e) {
        }
        return 0;
    }

    public static int getTongSoPhong() {
        try {
            con = SQLConnection.getConnection();
            Statement st = con.createStatement();
            st.setEscapeProcessing(true);
            st.setQueryTimeout(90);

            ResultSet rs = st.executeQuery("select count(*) as Tong from Phong ");

            while (rs.next()) {
                return rs.getInt("Tong");
            }

        } catch (Exception e) {
        }
        return 0;
    }

    public static List<uBieuDo> getSoLuongPhongTheoTP(int slhienthi) {
        List<uBieuDo> list = new ArrayList<>();
        try {
            con = SQLConnection.getConnection();
            PreparedStatement st = con.prepareStatement("exec laySoPhongTheoThanhPho");
            st.setEscapeProcessing(true);
            st.setQueryTimeout(90);

            ResultSet rs = st.executeQuery();
            int tongconlai = 0;
            int dem = 0;

            while (rs.next()) {
                if (dem < slhienthi) {
                    uBieuDo bd = new uBieuDo();
                    bd.setTen(rs.getString("ThanhPho") + " -" + rs.getInt("SoPhong"));
                    bd.setGiaTri(rs.getInt("SoPhong"));
                    list.add(bd);
                } else {
                    tongconlai += rs.getInt("SoPhong");
                }
                dem++;
            }
            if (dem >= slhienthi) {
                list.add(new uBieuDo("TP. Khác" + " -" + tongconlai, tongconlai));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static List<uBieuDo> getDatPhongTheoTP(int thang, int nam, int slhienthi) {
        List<uBieuDo> list = new ArrayList<>();
        try {
            con = SQLConnection.getConnection();
            PreparedStatement st = con.prepareStatement("exec laySoDatPhongThangCuaTinh ?,?");
            st.setEscapeProcessing(true);
            st.setQueryTimeout(90);
            st.setInt(1, thang);
            st.setInt(2, nam);

            ResultSet rs = st.executeQuery();
            int tongconlai = 0;
            int dem = 0;

            while (rs.next()) {
                if (dem < slhienthi) {
                    uBieuDo bd = new uBieuDo();
                    bd.setTen(rs.getString("ThanhPho"));
                    bd.setGiaTri(rs.getInt("SoDatPhong"));
                    list.add(bd);
                } else {
                    tongconlai += rs.getInt("SoDatPhong");
                }
                dem++;
            }
            if (dem > slhienthi) {
                list.add(new uBieuDo("TP. Khác", tongconlai));
            }

        } catch (Exception e) {
        }
        return list;
    }
    // ------------------------------ Thống kê phòng  --------------------------
    // -------------------------------------------------------------------------

    // -- Lấy danh sách phòng không được sử dụng trong một ngày + Khách sạn + thành phố
    public static List<uPhongT> getDSPhongTrong(Date ngaylay) {
        List<uPhongT> list = new ArrayList<>();
        try {
            con = SQLConnection.getConnection();
            PreparedStatement st = con.prepareStatement("exec phongTrongTrongNgay ?");
            st.setEscapeProcessing(true);
            st.setQueryTimeout(90);
            st.setDate(1, new java.sql.Date(ngaylay.getTime()));

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Phong p = new Phong();
                uPhongT pt = new uPhongT();

                p.setId(rs.getInt("Id"));
                p.setTen(rs.getString("Ten"));
                p.setGiaThue(rs.getInt("GiaThue"));
                p.setTienNghi(rs.getString("TienNghi"));
                p.setDienTich(rs.getInt("DienTich"));
                p.setTenKhachSan(rs.getString("KhachSan"));
                p.setMoTa(rs.getString("MoTa"));

                pt.setTenThanhPho(rs.getString("ThanhPho"));
                pt.setPhong(p);

                list.add(pt);
            }

        } catch (Exception e) {
        }
        return list;
    }

    // -- Lấy danh sách phòng đang được thuê trong một ngày + Thông tin khách hàng
    public static List<uPhongSD> getDSPhongSuDung(Date ngaylay) {
        List<uPhongSD> list = new ArrayList<>();
        try {
            con = SQLConnection.getConnection();
            PreparedStatement st = con.prepareStatement("exec phongDuocThueTrongNgay ?");
            st.setEscapeProcessing(true);
            st.setQueryTimeout(90);
            st.setDate(1, new java.sql.Date(ngaylay.getTime()));
          
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Phong p = new Phong();
                TaiKhoan tk = new TaiKhoan();
                uPhongSD pt = new uPhongSD();

                p.setId(rs.getInt("Id"));
                p.setMoTa(rs.getString("MoTa"));
                p.setTen(rs.getString("Ten"));
                p.setGiaThue(rs.getInt("GiaThue"));
                p.setTienNghi(rs.getString("TienNghi"));
                p.setDienTich(rs.getInt("DienTich"));

                tk.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                tk.setHoTen(rs.getString("HoTen"));
                tk.setGioiTinh(rs.getBoolean("GioiTinh"));
                tk.setEmail(rs.getString("Email"));
                tk.setSoDienThoai(rs.getString("SoDienThoai"));

                pt.setTaiKhoan(tk);
                pt.setPhong(p);
                pt.setNgayDat(rs.getDate("NgayDat"));
                pt.setThanhTien(rs.getInt("ThanhTien"));
                pt.setNgayDen(rs.getDate("NgayDen"));
                pt.setNgayTra(rs.getDate("NgayTra"));

                list.add(pt);
            }

        } catch (Exception e) {
        }
        return list;
    }

    // Lấy danh sách thành tên thành phố 
    public static List<String> getTenThanhPho() {
        List<String> list = new ArrayList<>();
        try {
            con = SQLConnection.getConnection();
            Statement st = con.createStatement();
            st.setEscapeProcessing(true);
            st.setQueryTimeout(90);

            ResultSet rs = st.executeQuery("select ThanhPho.Ten from ThanhPho");

            while (rs.next()) {
                list.add(rs.getString("Ten").trim());
            }

        } catch (Exception e) {
        }
        return list;
    }
    // ----------------------------Tổng hợp đặt phòng---------------------------
    // -------------------------------------------------------------------------

    public static List<uBieuDo> getTKDatPhongNam(int nam) {
        List<uBieuDo> list = new ArrayList<>();
        try {
            con = SQLConnection.getConnection();
            PreparedStatement st = con.prepareStatement("exec laySoDatPhongCuaNam ?");
            st.setEscapeProcessing(true);
            st.setQueryTimeout(90);
            st.setInt(1, nam);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                uBieuDo bd = new uBieuDo();
                bd.setTen(rs.getString("Thang"));
                bd.setGiaTri(rs.getInt("SoDatPhong"));
                list.add(bd);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static List<uBieuDo2GT> getTKDatPhongDoanhSoNNam(int nam, int khoanglay) {
        List<uBieuDo2GT> list = new ArrayList<>();
        try {
            con = SQLConnection.getConnection();
            PreparedStatement st = con.prepareStatement("exec doanhThuTheoKhoangNam ? , ?");
            st.setEscapeProcessing(true);
            st.setQueryTimeout(90);
            st.setInt(1, nam);
            st.setInt(2, khoanglay);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                uBieuDo2GT bd = new uBieuDo2GT();
                bd.setTen(rs.getString("Nam"));
                bd.setGiaTri1(rs.getInt("SoDatPhong"));
                bd.setGiaTri2(rs.getInt("TongTien"));
                list.add(bd);
            }
        } catch (Exception e) {
        }
        return list;
    }
    // ------------------------------ Thông báo  -------------------------------
    // -------------------------------------------------------------------------

    public static List<uThongBao> getDatphongTrongNgay(Date ngaylay) {
        List<uThongBao> list = new ArrayList<>();
        try {
            con = SQLConnection.getConnection();
            PreparedStatement st = con.prepareStatement("exec thongTinDatPhongNgay ?");
            st.setEscapeProcessing(true);
            st.setQueryTimeout(90);
            st.setDate(1, new java.sql.Date(ngaylay.getTime()));

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Phong p = new Phong();
                TaiKhoan tk = new TaiKhoan();
                DatPhong dp = new DatPhong();

                p.setId(rs.getInt("IdPhong"));
                p.setMoTa(rs.getString("MoTa"));
                p.setTen(rs.getString("Ten"));
                p.setGiaThue(rs.getInt("GiaThue"));
                p.setTienNghi(rs.getString("TienNghi"));
                p.setDienTich(rs.getInt("DienTich"));
                p.setTenKhachSan(rs.getString("TenKhachSan"));

                tk.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                tk.setHoTen(rs.getString("HoTen"));
                tk.setGioiTinh(rs.getBoolean("GioiTinh"));
                tk.setEmail(rs.getString("Email"));
                tk.setSoDienThoai(rs.getString("SoDienThoai"));

                dp.setDaHuy(rs.getBoolean("DaHuy"));
                dp.setNgayDen(rs.getDate("NgayDen"));
                dp.setNgayTra(rs.getDate("NgayTra"));
                dp.setDichVu(rs.getString("DichVu"));
                dp.setThanhTien(rs.getInt("ThanhTien"));
                dp.setId(rs.getInt("Id"));
                dp.setGhiChu(rs.getString("GhiChu"));

                list.add(new uThongBao(tk, dp, p));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    // Cập nhật đặt phòng trong thông báo =[Ngày đến , Ngày trả]
    public static String updateDatPhong(DatPhong dp) {
        String trangthai = null;

        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("update DatPhong \n"
                    + "set \n"
                    + "	NgayDen = ? ,\n"
                    + "	NgayTra = ?,\n"
                    + "	DaHuy = ?\n"
                    + "where DatPhong.Id = ?");
            stmt.setDate(1, new java.sql.Date(dp.getNgayDen().getTime()));
            stmt.setDate(2, new java.sql.Date(dp.getNgayTra().getTime()));
            stmt.setBoolean(3, dp.isDaHuy());
            stmt.setInt(4, dp.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            trangthai = "Cập nhật thất bại ";
        }
        return trangthai;
    }
    
    public static String deleteDatPhong(DatPhong dp) {
        String trangthai = null;
        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("delete from DatPhong where DatPhong.Id = ?");
            stmt.setInt(1, dp.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            trangthai = "Hủy đặt phòng thất bại ";
        }
        return trangthai;
    }

    // Lấy danh sách đặt phòng hiển thị thông báo =[Ngày đến , Ngày trả]
    public static List<DatPhong> getTomTatThongBaoDatPhong(Date ngaylay) {
        List<DatPhong> list = new ArrayList<>();

        try {
            con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("select *from DatPhong where DatPhong.NgayDat = ?");
            stmt.setDate(1, new java.sql.Date(ngaylay.getTime()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DatPhong dp = new DatPhong();
                dp.setTaiKhoan(rs.getString("TaiKhoan"));
                dp.setIdPhong(rs.getInt("IdPhong"));
                dp.setId(rs.getInt("Id"));
                dp.setDaHuy(rs.getBoolean("DaHuy"));
                list.add(dp);
            }
        } catch (Exception e) {
        }
        return list;
    }

}
