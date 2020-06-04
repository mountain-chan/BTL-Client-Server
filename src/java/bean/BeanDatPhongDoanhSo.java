package bean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.thongke.uBieuDo;
import model.thongke.uBieuDo2GT;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;

@ManagedBean(name = "beanDatPhongDoanhSo")
@ViewScoped
public class BeanDatPhongDoanhSo {

    private LineChartModel bdDatPhongNam;
    private LineChartModel bdDatPhongDanhThu5Nam;

    private Integer namTK;
    private Integer namSS;
    private Integer khoangNam;

    

    public BeanDatPhongDoanhSo() {
        initDuLieu();
        createBieuDoDatPhongNam();
        createBieuDoDatPhong5Nam();
    }

    // Khởi tạo dữ liệu 
    private void initDuLieu() {
        namTK = 2020;
        namSS = 2019;
        khoangNam = 5;
    }

    // Lấy danh sách năm từ 5 năm trước đến hiện tại
    public List<Integer> getDsNam() {
        List<Integer> dsNam  = new ArrayList<>();
        
        Date date = new Date();
        int namhientai = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
        
        for (Integer n = namhientai; n > namhientai - 5; n--) {
            dsNam.add(n);
        }
        return dsNam;
    }

    // Thay đổi giá trị select 
    public void changeNamTK() {
        bdDatPhongNam.clear();
        createBieuDoDatPhongNam();
    }

    public void changeNamSS() {
        bdDatPhongNam.clear();
        createBieuDoDatPhongNam();
    }

    public void changeKhoangNam() {
        bdDatPhongDanhThu5Nam.clear();
        createBieuDoDatPhong5Nam();
    }

    // Load dữ liệu và tạo biểu đồ  
    private void createBieuDoDatPhongNam() {

        List<uBieuDo> dsDPNamTK = dao.DAOThongKe.getTKDatPhongNam(namTK);
        List<uBieuDo> dsDPNamSS = dao.DAOThongKe.getTKDatPhongNam(namSS);

        bdDatPhongNam = new LineChartModel();
        int max = 0;
        int tong = 0;

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int namhientai = localDate.getYear();
        int thangHienTai = localDate.getMonthValue();

        LineChartSeries LineNamNay = new LineChartSeries();
        if (namTK <= namhientai) {
            for (uBieuDo b : dsDPNamTK) {
                if (namTK < namhientai || (Integer.parseInt(b.getTen()) <= thangHienTai)) {
                    LineNamNay.set(b.getTen(), b.getGiaTri());
                    tong += b.getGiaTri();
                    if (b.getGiaTri() > max) {
                        max = b.getGiaTri();
                    }
                }
            }
        }
        LineNamNay.setLabel(namTK + ": " + tong + " lượt");

        tong = 0;
        LineChartSeries LineNamTruoc = new LineChartSeries();

        if (namSS <= namhientai) {
            for (uBieuDo b : dsDPNamSS) {
                if (namSS < namhientai || (Integer.parseInt(b.getTen()) <= thangHienTai)) {
                    LineNamTruoc.set(b.getTen(), b.getGiaTri());
                    tong += b.getGiaTri();
                    if (b.getGiaTri() > max) {
                        max = b.getGiaTri();
                    }
                }
            }
        }
        LineNamTruoc.setLabel(namSS + ": " + tong + " lượt");

        bdDatPhongNam.addSeries(LineNamNay);
        bdDatPhongNam.addSeries(LineNamTruoc);
        bdDatPhongNam.setTitle("Lượng đặt phòng 12 tháng theo năm");
        bdDatPhongNam.setLegendPosition("ne");

        Axis yAxis = bdDatPhongNam.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setTickFormat("%d");
        yAxis.setMax(max * 3 / 2);

        Axis xAxis = bdDatPhongNam.getAxis(AxisType.X);
        xAxis.setTickFormat("%d");
        xAxis.setLabel("Tháng");
        xAxis.setMin(1);
        xAxis.setTickCount(12);
        xAxis.setMax(12);

    }

    private void createBieuDoDatPhong5Nam() {

        List<uBieuDo2GT> dsDatPhongDoanhThu = dao.DAOThongKe.getTKDatPhongDoanhSoNNam(namTK, khoangNam);

        bdDatPhongDanhThu5Nam = new LineChartModel();

        BarChartSeries dp = new BarChartSeries();
        dp.setLabel("Đặt phòng (lượt)");
        LineChartSeries dt = new LineChartSeries();
        dt.setLabel("Doanh thu (triệu VND)");
        dt.setXaxis(AxisType.X2);
        dt.setYaxis(AxisType.Y2);

        int maxdt = 0;
        int maxdp = 0;

        for (uBieuDo2GT b : dsDatPhongDoanhThu) {
            dp.set(b.getTen(), b.getGiaTri1());

            dt.set(b.getTen(), b.getGiaTri2() / 1000000);
            if (b.getGiaTri1() > maxdp) {
                maxdp = b.getGiaTri1();
            }
            if (b.getGiaTri2() / 1000000 > maxdt) {
                maxdt = b.getGiaTri2() / 1000000;
            }
        }

        bdDatPhongDanhThu5Nam.addSeries(dp);
        bdDatPhongDanhThu5Nam.addSeries(dt);
        bdDatPhongDanhThu5Nam.setLegendPosition("ne");

        bdDatPhongDanhThu5Nam.setTitle("Lượng đặt phòng và doanh số thu được 5 năm");
        bdDatPhongDanhThu5Nam.setMouseoverHighlight(false);

        bdDatPhongDanhThu5Nam.getAxes().put(AxisType.X, new CategoryAxis(""));
        bdDatPhongDanhThu5Nam.getAxes().put(AxisType.X2, new CategoryAxis(""));

        Axis yAxis = bdDatPhongDanhThu5Nam.getAxis(AxisType.Y);

        yAxis.setMin(0);
        yAxis.setTickFormat("%d");
        yAxis.setMax(maxdp * 3 / 2);

        Axis y2Axis = new LinearAxis();
        y2Axis.setMin(0);
        y2Axis.setTickFormat("%d");
        y2Axis.setMax(maxdt * 3 / 2);

        bdDatPhongDanhThu5Nam.getAxes().put(AxisType.Y2, y2Axis);
    }

    public LineChartModel getBdDatPhongNam() {
        return bdDatPhongNam;
    }

    public void setBdDatPhongNam(LineChartModel bdDatPhongNam) {
        this.bdDatPhongNam = bdDatPhongNam;
    }

    public LineChartModel getBdDatPhongDanhThu5Nam() {
        return bdDatPhongDanhThu5Nam;
    }

    public void setBdDatPhongDanhThu5Nam(LineChartModel bdDatPhongDanhThu5Nam) {
        this.bdDatPhongDanhThu5Nam = bdDatPhongDanhThu5Nam;
    }

    public Integer getNamTK() {
        return namTK;
    }

    public void setNamTK(Integer namTK) {
        this.namTK = namTK;
    }

    public Integer getNamSS() {
        return namSS;
    }

    public void setNamSS(Integer namSS) {
        this.namSS = namSS;
    }

    public Integer getKhoangNam() {
        return khoangNam;
    }

    public void setKhoangNam(Integer khoangNam) {
        this.khoangNam = khoangNam;
    }

}
