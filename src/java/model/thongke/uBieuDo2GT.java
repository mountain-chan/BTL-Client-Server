
package model.thongke;


public class uBieuDo2GT {
    private String ten ;

    public uBieuDo2GT(String ten, int giaTri1, int giaTri2) {
        this.ten = ten;
        this.giaTri1 = giaTri1;
        this.giaTri2 = giaTri2;
    }

    public uBieuDo2GT() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGiaTri1() {
        return giaTri1;
    }

    public void setGiaTri1(int giaTri1) {
        this.giaTri1 = giaTri1;
    }

    public int getGiaTri2() {
        return giaTri2;
    }

    public void setGiaTri2(int giaTri2) {
        this.giaTri2 = giaTri2;
    }
    private int giaTri1;
    private int giaTri2;
}
