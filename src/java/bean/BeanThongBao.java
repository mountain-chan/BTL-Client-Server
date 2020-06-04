package bean;

import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.DatPhong;
import model.thongke.uThongBao;

@ManagedBean(name = "beanThongBao")
@ViewScoped
public class BeanThongBao {

    private List<uThongBao> dsThongBao;
    private List<DatPhong> dsDatPhongTT;

    public BeanThongBao() {
        initDuLieu();
    }

    private void initDuLieu() {
        dsThongBao = dao.DAOThongKe.getDatphongTrongNgay(new Date());
        dsDatPhongTT = dao.DAOThongKe.getTomTatThongBaoDatPhong(new Date());
    }

    public void updateDatPhong(uThongBao tb) {
        DatPhong dp = tb.getDatPhong();

        if (dp.getNgayDen().getTime() > dp.getNgayTra().getTime()) {
            addMessage("Ngày Đến phải nhỏ hơn Ngày trả ", FacesMessage.SEVERITY_WARN);
            return;
        }

        if (dp.getNgayDen().getTime() < (new Date()).getTime()) {
            addMessage("Ngày Đến phải lớn Hiện tại ", FacesMessage.SEVERITY_WARN);
            return;
        }

        String trangthai = dao.DAOThongKe.updateDatPhong(dp);
        if (trangthai == null) {
            addMessage("Cập nhật thành công !", FacesMessage.SEVERITY_INFO);

        } else {
            addMessage(trangthai, FacesMessage.SEVERITY_ERROR);
        }
    }

    public void cancelDatPhong(uThongBao tb) {
        DatPhong dp = tb.getDatPhong();
        String trangthai = dao.DAOThongKe.deleteDatPhong(dp);
        if (trangthai == null) {
            addMessage("Hủy đặt phòng thành công !", FacesMessage.SEVERITY_INFO);
            dsThongBao.remove(tb);
        } else {
            addMessage(trangthai, FacesMessage.SEVERITY_ERROR);
        }
    }

    public void addMessage(String thongbao, FacesMessage.Severity state) {
        FacesMessage message = new FacesMessage(state, thongbao, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<uThongBao> getDsThongBao() {
        return dsThongBao;
    }

    public List<DatPhong> getDsDatPhongTT() {
        return dsDatPhongTT;
    }

}
