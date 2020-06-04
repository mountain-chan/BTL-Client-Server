package bean;

import model.thongke.uPhongSD;
import model.thongke.uPhongT;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


// Sử dụng @ViewScoped thay cho @RequestScoped giúp giữ giá trị thay đổi khi sử dụng ajax 
@ManagedBean(name = "beanThongKePhong")
@ViewScoped
public final class BeanThongKePhong {

    private List<uPhongT> dsPhongTrong;
    private List<uPhongSD> dsPhongSD;

    private List<uPhongT> filteredPhongTrongs;
    private List<uPhongSD> filteredPhongSDs;

    // Sử dụng hiển thị thông tin phòng + người thuê
    private uPhongSD phongSDChon;
    private uPhongT phongTChon;

    // Thời gian lấy dũ liệu
    private Date ngayLay;
    private Integer tabHoatDong;

    // Có 3 trạng thái : 0 bằng , 1 lớn hơn , 2 nhỏ hơn 
    private Integer dauNgayDen;
    private Integer dauNgayTra;

    public BeanThongKePhong() {
        initDuLieu();
    }

    //Khởi tạo và load dữ liệu
    private void initDuLieu() {
        ngayLay = new Date();
        // mặc định là so sánh bằng ;
        dauNgayDen = 0;
        dauNgayTra = 0;
        dsPhongTrong = dao.DAOThongKe.getDSPhongTrong(ngayLay);
        dsPhongSD = dao.DAOThongKe.getDSPhongSuDung(ngayLay);
       
    }

    //Thay đổi ngày lấy danh sách + cập nhật danh sách lọc
    public void changeNgayLay() {
        dsPhongTrong = dao.DAOThongKe.getDSPhongTrong(ngayLay);
        dsPhongSD = dao.DAOThongKe.getDSPhongSuDung(ngayLay);
        filteredPhongSDs = dsPhongSD;
        filteredPhongTrongs = dsPhongTrong;
    }

    public boolean filterNgayDen(Object ngaySoSanh, Object ngayLoc, Locale locale) {
        if (ngayLoc == null) {
            return true;
        }
        if (ngaySoSanh == null) {
            return false;
        }

        Date dt2 = (Date) ngayLoc;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", locale);
        String date1 = sdf.format(ngaySoSanh);

        // Trạng thái so sánh bằng : ngày lọc và ngày so sánh bằng nhau trong table
        if (0 == dauNgayDen) {
            String date2 = sdf.format(dt2);
            return date2.equals(date1);

        } else if (dauNgayDen == 1) {
            try {
                Date dt1 = sdf.parse(date1);
                return (dt1.getTime() < dt2.getTime());
            } catch (ParseException e) {
                return false;
            }

        } else {
            try {
                Date dt1 = sdf.parse(date1);
                return (dt1.getTime() > dt2.getTime());
            } catch (ParseException e) {
                return false;
            }
        }
    }

    public boolean filterNgayTra(Object ngaySoSanh, Object ngayLoc, Locale locale) {
        if (ngayLoc == null) {
            return true;
        }
        if (ngaySoSanh == null) {
            return false;
        }

        Date dt2 = (Date) ngayLoc;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", locale);
        String date1 = sdf.format(ngaySoSanh);

        // Trạng thái so sánh bằng : ngày lọc và ngày so sánh bằng nhau trong table
        if (0 == dauNgayTra) {
            String date2 = sdf.format(dt2);
            return date2.equals(date1);

        } else if (dauNgayTra == 1) {
            try {
                Date dt1 = sdf.parse(date1);
                return (dt1.getTime() < dt2.getTime());
            } catch (ParseException e) {
                return false;
            }

        } else {
            try {
                Date dt1 = sdf.parse(date1);
                return (dt1.getTime() > dt2.getTime());
            } catch (ParseException e) {
                return false;
            }
        }
    }

    public List<uPhongT> getDsPhongTrong() {
        return dsPhongTrong;
    }

    public List<uPhongSD> getDsPhongSD() {
        return dsPhongSD;
    }

    public List<String> getThanhPho() {
        return dao.DAOThongKe.getTenThanhPho();
    }

    public List<uPhongT> getFilteredPhongTrongs() {
        return filteredPhongTrongs;
    }

    public void setFilteredPhongTrongs(List<uPhongT> filteredPhongTrongs) {
        this.filteredPhongTrongs = filteredPhongTrongs;
    }

    public List<uPhongSD> getFilteredPhongSDs() {
        return filteredPhongSDs;
    }

    public void setFilteredPhongSDs(List<uPhongSD> filteredPhongSDs) {
        this.filteredPhongSDs = filteredPhongSDs;
    }

    public Date getNgayLay() {
        return ngayLay;
    }

    public void setNgayLay(Date ngayLay) {
        this.ngayLay = ngayLay;
    }

    public uPhongSD getPhongSDChon() {
        return phongSDChon;
    }

    public void setPhongSDChon(uPhongSD phongSDChon) {
        this.phongSDChon = phongSDChon;
    }

    public uPhongT getPhongTChon() {
        return phongTChon;
    }

    public void setPhongTChon(uPhongT phongTChon) {
        this.phongTChon = phongTChon;
    }

    public Integer getDauNgayDen() {
        return dauNgayDen;
    }

    public void setDauNgayDen(Integer dauNgayDen) {
        this.dauNgayDen = dauNgayDen;
    }

    public Integer getDauNgayTra() {
        return dauNgayTra;
    }

    public void setDauNgayTra(Integer dauNgayTra) {
        this.dauNgayTra = dauNgayTra;
    }

    public Integer getTabHoatDong() {
        return tabHoatDong;
    }

    public void setTabHoatDong(Integer tabHoatDong) {
        this.tabHoatDong = tabHoatDong;
    }

}
