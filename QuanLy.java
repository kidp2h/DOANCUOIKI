package DOANCUOIKI;

public class QuanLy extends NhanVien {

    private double luongThuong;

    public double getLuongThuong() {
        return this.luongThuong;
    }

    public void setLuongThuong(double luongThuong) {
        this.luongThuong = luongThuong;
    }

    public QuanLy(String taiKhoan, String matKhau, String hoTen, int tuoi, String gioiTinh, String soDienThoai, double luongCoBan, double luongThuong) {
        super(taiKhoan, matKhau, hoTen, tuoi, gioiTinh, soDienThoai, luongCoBan);
        this.luongThuong  = luongThuong;
    }

    @Override
    public double TinhLuong() {
        return luongCoBan + luongThuong;
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
        + luongThuong+ "\t   "
        + "Quan Ly" ;
    }




    
    
}
