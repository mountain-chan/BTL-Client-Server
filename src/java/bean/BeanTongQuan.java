package bean;


import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.thongke.uBieuDo;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "beanTongQuan")
@RequestScoped
public class BeanTongQuan {

    private PieChartModel PiePhongTheoTinh;
    private BarChartModel BarKhachTheoTinh;
    private int LuongKhachLuuTru;
    private int SoPhongTrong;
    private int TongSoPhong;

    private int thangTK;
    private int namTK;

    public BeanTongQuan() {
        initDuLieu();
        createPiePhongTheoTinh();
        createBarKhachTheoTinh();
    }

    private void initDuLieu() {
        thangTK = 12;
        namTK = 2019;
        SoPhongTrong = dao.DAOThongKe.getTongPhongTrong(new Date());
        TongSoPhong = dao.DAOThongKe.getTongSoPhong();
        LuongKhachLuuTru = dao.DAOThongKe.getTongSoKhachThue(new Date());
    }

    private void createPiePhongTheoTinh() {

        List<uBieuDo> bd = dao.DAOThongKe.getSoLuongPhongTheoTP(4);
        PiePhongTheoTinh = new PieChartModel();

        bd.forEach((b) -> {
            PiePhongTheoTinh.set(b.getTen(), b.getGiaTri());
        });

       
        PiePhongTheoTinh.setTitle("Thống kê số phòng theo tỉnh ");
        PiePhongTheoTinh.setShowDataLabels(true);
        PiePhongTheoTinh.setShowDatatip(true);

        PiePhongTheoTinh.setLegendPosition("w");
        PiePhongTheoTinh.setShadow(false);
    }

    private void createBarKhachTheoTinh() {
        int max = 0;

        List<uBieuDo> dsDatPhongNamTruoc = dao.DAOThongKe.getDatPhongTheoTP(thangTK, namTK, 5);
        List<uBieuDo> dsDatPhongNamSau = dao.DAOThongKe.getDatPhongTheoTP(thangTK, namTK - 1, 5);

        BarKhachTheoTinh = new BarChartModel();

        ChartSeries bd_hientai = new ChartSeries();
        bd_hientai.setLabel(String.valueOf(namTK));
        for (uBieuDo b : dsDatPhongNamTruoc) {
            bd_hientai.set(b.getTen(), b.getGiaTri());
            if (b.getGiaTri() > max) {
                max = b.getGiaTri();
            }
        }
        ChartSeries bd_cungky = new ChartSeries();
        bd_cungky.setLabel(String.valueOf(namTK - 1));
        for (uBieuDo b : dsDatPhongNamSau) {
            bd_cungky.set(b.getTen(), b.getGiaTri());
            if (b.getGiaTri() > max) {
                max = b.getGiaTri();
            }
        }

        BarKhachTheoTinh.setTitle("Lượng khách theo tỉnh " + "(Tháng " + thangTK + " năm " + namTK + ")");
        BarKhachTheoTinh.addSeries(bd_hientai);
        BarKhachTheoTinh.addSeries(bd_cungky);
        BarKhachTheoTinh.setLegendPosition("ne");

        Axis yAxis = BarKhachTheoTinh.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setTickFormat("%d");
        yAxis.setMax(max * 3 / 2 + 5);

    }

    public PieChartModel getPiePhongTheoTinh() {
        return PiePhongTheoTinh;
    }

    public BarChartModel getBarKhachTheoTinh() {
        return BarKhachTheoTinh;
    }

    public int getLuongKhachLuuTru() {
        return LuongKhachLuuTru;
    }

    public int getSoPhongTrong() {
        return SoPhongTrong;
    }

    public int getTongSoPhong() {
        return TongSoPhong;
    }

}
