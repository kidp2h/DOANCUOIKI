package DOANCUOIKI;

public class NhanVien extends NhanSu {
    protected double luongCoBan;

    public double getLuongCoBan() {
        return this.luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public NhanVien(String taiKhoan, String matKhau, String hoTen, int tuoi, String gioiTinh, String soDienThoai,
    double luongCoBan) {
        super(taiKhoan, matKhau, hoTen, tuoi, gioiTinh, soDienThoai);
        this.luongCoBan = luongCoBan;
    }

    @Override
    public double TinhLuong() {
        return luongCoBan;
    }

    @Override
    public String ThongTin() {
        return  
        taiKhoan + "\t"
        + hoTen + "\t   "
        + tuoi + " \t   "
        + gioiTinh + "\t  "
        + soDienThoai + "\t  "
        + luongCoBan + "\t"
        + "1000.0\t   "
        + "Nhan Vien" ;

    }
}
