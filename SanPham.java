package DOANCUOIKI;

public class SanPham {
   
    private int id;
    private String tenSanPham;
    private int giaBan;
    private String tenLoai;

    public SanPham(int id, String tenSanPham, int giaBan, String tenloai) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.giaBan = giaBan;
        this.tenLoai = tenloai;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return this.tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaBan() {
        return this.giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public String getTenLoai() {
        return this.tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String ThongTinSanPham() {
        return "Id: " + id 
        + " Ten san pham: " + tenSanPham 
        + " Gia ban: " + giaBan 
        + " Ten loai: " + tenLoai; 
    }

}
