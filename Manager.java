package DOANCUOIKI;

public class Manager extends Staff {

    private double salaryBonus;

    public double getSalaryBonus() {
        return this.salaryBonus;
    }

    public void setSalaryBonus(double salaryBonus) {
        this.salaryBonus = salaryBonus;
    }

    public Manager(String taiKhoan, String matKhau, String hoTen, int tuoi, String gioiTinh, String soDienThoai, double luongCoBan, double salaryBonus) {
        super(taiKhoan, matKhau, hoTen, tuoi, gioiTinh, soDienThoai, luongCoBan);
        this.salaryBonus  = salaryBonus;
    }

    @Override
    public double SumSalary() {
        return salary + salaryBonus;
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
        + salaryBonus+ "\t   "
        + "Quan Ly" ;
    }




    
    
}
