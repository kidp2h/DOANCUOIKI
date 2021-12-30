package DOANCUOIKI;

public class Staff extends Person {
    protected double salary;

    public Staff(String taiKhoan, String matKhau, String hoTen, int tuoi, String gioiTinh, String soDienThoai,
    double salary) {
        super(taiKhoan, matKhau, hoTen, tuoi, gioiTinh, soDienThoai);
        this.salary = salary;
    }

    @Override
    public double SumSalary() {
        return salary;
    }

    @Override
    public String Info() {
        return  
        username + "\t"
        + fullName + "\t   "
        + age + " \t   "
        + gender + "\t  "
        + phone + "\t  "
        + salary + "\t"
        + "1000.0\t   "
        + "Nhan Vien" ;

    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
