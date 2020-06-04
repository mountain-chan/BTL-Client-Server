package model.thongke;

public class uBieuDo {

    String ten;
    int giaTri;

    public uBieuDo(String ten, int giaTri) {
        this.ten = ten;
        this.giaTri = giaTri;
    }

    public uBieuDo() {
        ten = "null";
        giaTri = 0;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }

}
