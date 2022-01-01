package DOANCUOIKI;

import java.io.Console;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import DOANCUOIKI.management.BillManagement;
import DOANCUOIKI.management.PersonManagement;
import DOANCUOIKI.management.ProductManagement;
import DOANCUOIKI.util.Color;
import DOANCUOIKI.util.ConsoleProgram;

public class Main {
  // phuong thuc static dc goi ma ko tao mot instance
  public static Person MenuDangNhap() {
    Console csl = System.console();
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLUE_BACKGROUND);
      System.out.println("======== UNG DUNG QUAN LY BAN HANG ========\n" + Color.RESET
                        +"===========================================\n"
                        +"||  1. Dang Nhap                         ||\n"
                        +"||  0. Thoat                             ||\n"
                        +"===========================================");
      System.out.print("~~> Lua chon: ");
      String line = ConsoleProgram.INPUT.nextLine();

      switch (line) {
        case "1":
          ConsoleProgram.clearConsole();
          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.println("=========== DANG NHAP ===========\n" + Color.RESET
                            +"=================================");

          System.out.print(Color.GREEN + "Tai Khoan: " + Color.RESET);
          String taiKhoan = ConsoleProgram.INPUT.nextLine().trim();

          System.out.print(Color.GREEN + "Mat Khau: " + Color.RESET);
          char[] pwd = csl.readPassword();
          String matKhau = String.valueOf(pwd).trim();

          Person isLogin = PersonManagement.Instance().CheckAccount(taiKhoan, matKhau);

          if (isLogin != null) {
            return isLogin;
          } 
          else {
            ConsoleProgram.clearConsole();
            System.out.println(Color.YELLOW + Color.RED_BACKGROUND);
            System.out.print("Tai khoan hoac mat khau sai !!!" + Color.RESET);

            try {
              Thread.sleep(800);
            } catch (Exception e) {
             
            }
          }

          break;

        case "0":
          return null;

        default:
          continue;

      }
    }
  }




  public static boolean Menu(Person person) {
    while (true) {
      ConsoleProgram.clearConsole();
      String chucVu;

      if (person instanceof Manager)
        chucVu = "Quan Ly";
      else
        chucVu = "Nhan Vien";

      System.out.print(Color.BLUE_BACKGROUND);
      System.out.println("======== UNG DUNG QUAN LY BAN HANG ========\n" + Color.RESET
                        +"Tai khoan: " + person.getUsername() + "  \tChuc vu: " + chucVu
                        +"\n===========================================\n"
                        +"||  1. Ban Hang                          ||\n"
                        +"||  2. Quan Ly                           ||\n"
                        +"||  3. Thong ke                          ||\n"
                        +"||  4. Dang Xuat                         ||\n"
                        +"||  0. Thoat                             ||\n"
                        +"===========================================");
      System.out.print("~~> Lua chon: ");
      String line = ConsoleProgram.INPUT.nextLine();

      switch (line) {
        case "1":
          MenuBanHang();
          break;

        case "2":
          if (person instanceof Manager) {
            MenuQuanLy();
          }
          else {
            ConsoleProgram.clearConsole();
            System.out.print(Color.YELLOW + Color.RED_BACKGROUND);
            System.out.println("Chuc nang chi danh cho quan ly !!!" + Color.RESET);
            System.out.print("\nNhap phim bat ky de quay lai: ");
            ConsoleProgram.INPUT.nextLine();
          }
          break;

        case "3":
          MenuThongKe();
          break;
          
        case "4":
          return true;

        case "0":

          return false;

        default:
          continue;

      }
    }
  }


  //BAN HANGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
  public static void MenuBanHang() {
    ConsoleProgram.clearConsole();
    System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
    System.out.println("==================== BAN HANG ====================" + Color.BLUE);
    System.out.println("------------ NHAP THONG TIN KHACH HANG -----------" + Color.RESET);
    
    System.out.print(Color.YELLOW + "> Ten Khach Hang: " + Color.RESET);
    String hoTen = ConsoleProgram.INPUT.nextLine();
    System.out.print(Color.YELLOW + "> So Dien Thoai: " + Color.RESET);
    String sdt = ConsoleProgram.INPUT.nextLine();

    Bill bill = new Bill(hoTen, sdt);
    
    System.out.println(Color.BLUE + "------------- ------------------------ ----------" + Color.RESET);

    while (true) {
      ConsoleProgram.clearConsole();
      System.out.println(bill.InfoDetails());

      System.out.println(Color.GREEN);
      System.out.println("================================================\n"
                        +"||  1. Them san pham cho hoa don              ||\n"
                        +"||  2. Sua so luong san pham trong hoa don    ||\n"
                        +"||  3. Xoa san pham khoi hoa don              ||\n"
                        +"||  4. Thanh toan hoa don                     ||\n"
                        +"||  0. Quay lai                               ||\n"
                        +"================================================");
      System.out.println(Color.YELLOW);
      System.out.print("~~> Lua chon: " + Color.RESET);
      String line = ConsoleProgram.INPUT.nextLine();

      switch (line) {
        case "1":
          MenuThemSPVaoBill(bill);
          break;

        case "2":
          MenuSuaSLSpTrongBill(bill);
          break;

        case "3":
          MenuXoaSpKhoiBill(bill);
          break;

        case "4":
          ConsoleProgram.clearConsole();
          System.out.println(bill.InfoDetails());
        
          bill.setDate(LocalDate.now());
          BillManagement.Instance().Add(bill, ENV.pathBill);

          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.println("\n~~> DA THANH TOAN THANH CONG !!! <~~" + Color.RESET);
          System.out.print("Nhan phim bat ki de quay lai trang chu: ");
          ConsoleProgram.INPUT.nextLine();
          return;

        case "0":

          System.out.println(Color.RED + Color.YELLOW_BACKGROUND);
          System.out.print("Neu quay lai hoa don tren se mat (Dong y nhan 'Y'/'y'):" + Color.RESET + " ");
          line = ConsoleProgram.INPUT.nextLine();
          if(line.equals("Y") || line.equals("y")) return;

        default:
          continue;
      }
    }
  }

  public static void MenuThemSPVaoBill(Bill bill) {
    while(true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(
        Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN +
        Color.Line(100, '-') +
          "\nSTT\tTEN SP\tLOAI\tGIA\n" +
        Color.Line(100, '-')
      );

      ProductManagement.Instance().PrintList();

      System.out.println(Color.Line(100, '-'));

      System.out.println(Color.GREEN);
      System.out.println("================================================\n"
                        +"||  a. Loc san pham theo loai                 ||\n"
                        +"||  b. Loc san pham theo khoang gia           ||\n"
                        +"||  c. Loc san pham theo ten                  ||\n"
                        +"||  0. Thoat                                  ||\n"
                        +"================================================");
      System.out.println(Color.YELLOW);
      System.out.print("~~> Nhap stt san pham can mua \n(hoac su dung chuc nang): " + Color.RESET);
      String line = ConsoleProgram.INPUT.nextLine();
      switch(line) {
        case "a":
        break;
        case "b":
        break;
        case "c":
        break;
        case "0":
        return;
        default:

          int stt;
          try {
            stt =  Integer.parseInt(line);
          } catch(Exception e) {
            continue;
          }
          if(stt > ProductManagement.Instance().getListSize()) continue;

          Product product = ProductManagement.Instance().GetList().get(stt - 1 );

          int amount = Validate.CheckNumberInt("So luong: ");
          BillDetails details = new BillDetails(product, amount);
          bill.AddDetails(details);

          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.print("\n~~> DA THEM THANH CONG !!! <~~" + Color.RESET);

          try {
            Thread.sleep(800);
          } catch (Exception e) {}
      }
    }
  }

  public static void MenuSuaSLSpTrongBill(Bill bill) {
    while(true) {
      ConsoleProgram.clearConsole();
      System.out.println(bill.InfoDetails());

      System.out.println(Color.YELLOW);
      System.out.println("Nhap 0 de quay lai");
      int stt = Validate.CheckNumberInt("Nhap stt san pham muon sua: " + Color.RESET);

      if(stt == 0) return;

      System.out.println(Color.YELLOW);
      int amount = Validate.CheckNumberInt("So luong thay doi (lon hon 0): " + Color.RESET);


      if(bill.UpdateDetails(stt - 1 , amount)) {
        System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
        System.out.print("\n~~> DA SUA THANH CONG !!! <~~" + Color.RESET);

        try {
          Thread.sleep(800);
        } catch (Exception e) {}
      }

      

    }
  }

  public static void MenuXoaSpKhoiBill(Bill bill) {
    while(true) {
      ConsoleProgram.clearConsole();
      System.out.println(bill.InfoDetails());

      System.out.println(Color.YELLOW);
      System.out.println("Nhap 0 de quay lai");
      System.out.print("Nhap stt san pham muon xoa: " + Color.RESET);
      int stt = Validate.CheckNumberInt("Nhap stt san pham muon sua: " + Color.RESET);

      if(stt == 0) return;

      if(bill.DeleteDetails(stt - 1 )) {
        System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
        System.out.print("\n~~> DA XOA THANH CONG !!! <~~" + Color.RESET);

        try {
          Thread.sleep(800);
        } catch (Exception e) {}
      }
    }
  }
  //BAN HANGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG



  public static void MenuQuanLy() {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println("================= QUAN LY =================\n" + Color.RESET
                        +"===========================================\n"
                        +"||  1. Quan ly san pham                  ||\n"
                        +"||  2. Quan ly hoa don                   ||\n"
                        +"||  3. Quan ly nhan su                   ||\n"
                        +"||  0. Quay lai                          ||\n"
                        +"===========================================\n");
      System.out.print("~~> Lua chon: ");

      String line = ConsoleProgram.INPUT.nextLine();
      switch (line) {
        case "1":
          MenuQuanLySanPham();
          break;

        case "2":
          MenuQuanLyHoaDon();
          break;

        case "3":
          MenuQuanLyNhanSu();
          break;

        case "0":
          return;

        default:
          continue;

      }
    }
  }



  public static void MenuQuanLySanPham() {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLUE_BACKGROUND);
      System.out.println("======== UNG DUNG QUAN LY BAN HANG ========" + Color.RESET);
      System.out.println("===========================================\n"
                      + "||  1. Xuat danh sach san pham            ||\n"
                      + "||  2. Them san pham                      ||\n"
                      + "||  3. Sua san pham                       ||\n"
                      + "||  4. Xoa san pham                       ||\n"
                      + "||  5. Tim kiem san pham                  ||\n"
                      + "||  0. Quay lai                           ||\n"
                      + "===========================================");
      System.out.print("~~> Lua chon: ");
      String line = ConsoleProgram.INPUT.nextLine();
      switch (line) {
        case "1":
          ConsoleProgram.clearConsole();
          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.println(Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN);
          System.out.print(Color.Line(100, '-') +
              "\nSTT\tMA SP\tTEN SP\tLOAI\tGIA\n" +
              Color.Line(100, '-'));

          ProductManagement.Instance().PrintList();

          System.out.println(Color.Line(100, '-'));
          System.out.print(Color.RESET);

          System.out.print("\nNhan phim bat ki de quay lai: ");
          ConsoleProgram.INPUT.nextLine();
          break;
        
        case "2": 
          MenuThemSanPhamVaoDS();
          break;

        case "3":
          MenuSuaSanPhamTrongDS();
          break;
          
        case "4":
          MenuXoaSanPhamTrongDS();
          break;
          
        case "5":
          MenuTimKiemSanPhamTrongDS();
          break;

        case "0":
          return;

        default:
          continue;

      }
    }
  }

  public static void MenuThemSanPhamVaoDS() {
    System.out.print(Color.GREEN);
    System.out.println("\n--------------- NHAP THONG TIN SAN PHAM --------------" + Color.RESET);

    String idProduct = Validate.CheckIDProduct();
    String name = Validate.CheckEmpty("Ten san pham: ");
    String category = Validate.CheckEmpty("Loai san pham: ");
    Double price = Validate.CheckNumberDouble("Gia ban: ");

    Product product = new Product(idProduct,name, category, price);
    ProductManagement.Instance().Add(product, ENV.pathProduct);

    System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
    System.out.print("\n~~> DA THEM THANH CONG !!! <~~" + Color.RESET);

    try {
      Thread.sleep(800);
    } catch (Exception e) { }

  }

  public static void MenuSuaSanPhamTrongDS() {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN);
      System.out.print(Color.Line(100, '-') +
          "\nSTT\tMA SP\tTEN SP\tLOAI\tGIA\n" +
          Color.Line(100, '-'));

      ProductManagement.Instance().PrintList();

      System.out.println(Color.Line(100, '-'));
      System.out.print(Color.RESET);

      System.out.println("\nNhap 0 de quay lai");
      int index = Validate.CheckNumberInt("Nhap STT san pham can sua: ");


      if (index == 0) return;
      if(index > PersonManagement.Instance().getListSize()) continue;
      Product product = ProductManagement.Instance().GetList().get(index - 1);

      while (true) {
        ConsoleProgram.clearConsole();
        System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
        System.out.println("======== THONG TIN SAN PHAM MUON SUA ========" + Color.RESET);
        System.out.println("============================================\n"
            + "||" + Color.GREEN + "  1. Ten san pham: " + Color.YELLOW + product.getName() + "\n" + Color.RESET
            + "||" + Color.GREEN + "  2. Loai: " + Color.YELLOW + product.getCategory() + "\n" + Color.RESET
            + "||" + Color.GREEN + "  3. Gia: " + Color.YELLOW + product.getPrice() + "\n" + Color.RESET
            + "||" + Color.GREEN + "  0. Quay lai\n"
            + Color.RESET + "============================================");
        System.out.print("~~> Lua chon muc de sua: ");

        String line = ConsoleProgram.INPUT.nextLine();
        switch (line) {
          case "1": {
            String nameProduct = Validate.CheckEmpty("Nhap lai ten san pham: ");
            product.setName(nameProduct);
            ProductManagement.Instance().Update(index - 1, product, ENV.pathProduct);
            break;
          }
          case "2": {
            String categoryProduct = Validate.CheckEmpty("Nhap lai loai san pham: ");
            product.setCategory(categoryProduct);
            ProductManagement.Instance().Update(index - 1, product, ENV.pathProduct);
            break;
          }
          case "3": {
            double priceProduct = Validate.CheckNumberDouble("Nhap lai gia san pham: ");
            product.setPrice(priceProduct);
            ProductManagement.Instance().Update(index - 1, product, ENV.pathProduct);
            break;
          }
          case "0":
            return;

          default:
        }
      }
    }
  }

  public static void MenuXoaSanPhamTrongDS() {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN);
      System.out.print(Color.Line(100, '-') +
          "\nSTT\tMA SP\tTEN SP\tLOAI\tGIA\n" +
          Color.Line(100, '-'));

      ProductManagement.Instance().PrintList();

      System.out.println(Color.Line(100, '-'));
      System.out.print(Color.RESET);

      System.out.println("\nNhap 0 de quay lai");
      int id = Validate.CheckNumberInt("Nhap STT san pham can xoa: ");

      if(id == 0) return;
      
      if(ProductManagement.Instance().Delete(id - 1, ENV.pathProduct)) {
        System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
        System.out.print("\n~~> DA XOA THANH CONG !!! <~~" + Color.RESET);

        try {
          Thread.sleep(800);
        } catch (Exception e) {}
      }  
    }
  }
  
  public static void MenuTimKiemSanPhamTrongDS() {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN);
      System.out.print(Color.Line(100, '-') +
          "\nSTT\tTEN SP\tLOAI\tGIA\n" +
          Color.Line(100, '-'));

      ProductManagement.Instance().PrintList();

      System.out.println(Color.Line(100, '-'));
      System.out.print(Color.RESET);

      System.out.println("===========================================\n"
                      + "||  1. Tim kiem san pham theo ten         ||\n"
                      + "||  2. Tim kiem san pham theo loai        ||\n"
                      + "||  3. Tim kiem san pham theo gia         ||\n"
                      + "||  0. Quay lai                           ||\n"
                      + "===========================================");
      System.out.print("~~> Lua chon: ");
      String line = ConsoleProgram.INPUT.nextLine();

      switch(line) {
        case "1": {
          System.out.print("\nTim kiem theo ten san pham: ");
          String nameProduct = ConsoleProgram.INPUT.nextLine();
          MenuTimKiemSanPhamTheoTen(nameProduct);
          break;
        }

        case "2": {
          System.out.print("\nTim kiem theo loai san pham: ");
          String categoryProduct = ConsoleProgram.INPUT.nextLine();
          MenuTimKiemSanPhamTheoLoai(categoryProduct);
          break;
        }

        case "3": {

        }

        case "0":
          return;
        
        default:
          continue;

      }
  

      
    }
  }

  public static void MenuTimKiemSanPhamTheoTen(String nameProduct) {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN);
      System.out.print(Color.Line(100, '-') +
        "\nSTT\tTEN SP\tLOAI\tGIA\n" +
        Color.Line(100, '-'));

      List<Product> productList = ProductManagement.Instance().SearchByName(nameProduct);
      int count = productList.size();

      System.out.println(Color.YELLOW);
      if (count == 0) {
        System.out.println("\n\t\t\t\t\tKHONG CO KET QUA NAO TRUNG KHOP");
      } else
        for (int i = 1; i <= count; i++)
          System.out.println("[" + i + "]\t" + productList.get(i - 1).Info());
          
      System.out.println(Color.RESET);

      System.out.println(Color.Line(100, '-'));
      System.out.print(Color.RESET);

      System.out.print("\nNhap 0 de quay lai");
      System.out.print("\nTim kiem theo ten san pham: ");
      nameProduct = ConsoleProgram.INPUT.nextLine();

      if (nameProduct.equals("0")) return;
    }
  }

  public static void MenuTimKiemSanPhamTheoLoai(String categoryProduct) {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN);
      System.out.print(Color.Line(100, '-') +
        "\nSTT\tMA SP\tTEN SP\tLOAI\tGIA\n" +
        Color.Line(100, '-'));

      List<Product> productList = ProductManagement.Instance().SearchByCategory(categoryProduct);
      int count = productList.size();
      System.out.println(Color.YELLOW);

      if (count == 0) {
        System.out.println("\n\t\t\t\t\tKHONG CO KET QUA NAO TRUNG KHOP");
      } else
        for (int i = 1; i <= count; i++)
          System.out.println("[" + i + "]\t" + productList.get(i - 1).Info());

      System.out.println(Color.RESET);

      System.out.println(Color.Line(100, '-'));
      System.out.print(Color.RESET);

      System.out.print("\nNhap 0 de quay lai");
      System.out.print("\nTim kiem theo loai san pham: ");
      categoryProduct = ConsoleProgram.INPUT.nextLine();

      if (categoryProduct.equals("0")) return;
    }
  }



  public static void MenuQuanLyHoaDon() {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLUE_BACKGROUND);
      System.out.println("============== QUAN LY HOA DON ============" + Color.RESET);
      System.out.println("===========================================\n"
                      + "||  1. Xuat danh sach hoa don            ||\n"
                      + "||  2. Them hoa don                      ||\n"
                      + "||  3. Sua hoa don                       ||\n"
                      + "||  4. Xoa hoa don                       ||\n"
                      + "||  5. Tim kiem hoa don                  ||\n"
                      + "||  0. Quay lai                          ||\n"
                      + "===========================================");
      System.out.print("~~> Lua chon: ");
      String line = ConsoleProgram.INPUT.nextLine();

      switch (line) {
        case "1":
          ConsoleProgram.clearConsole();
          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.println(Color.Line(41, '=') + " DANH SACH HOA DON " + Color.Line(41, '=') + Color.GREEN);
          System.out.print(
          Color.Line(100, '-') +
          "\nSTT\tKHACHHANG\tSODIENTHOAI\tTONGTIEN\tNGAYTHANHTOAN\n" +
          Color.Line(100, '-'));

          BillManagement.Instance().PrintList();

          System.out.println(Color.Line(100, '-'));
          System.out.print(Color.RESET);

          System.out.print("\nNhan phim bat ki de quay lai: ");
          ConsoleProgram.INPUT.nextLine();
          break;
        case "2":
          MenuBanHang();
          break;

        case "3":
          MenuSuaBill();
          break;

        case "4":
          MenuXoaBill();
          break;

        case "5":
          
          break;

        case "0":
          return;

        default:
          continue;

      }
    }
  }

  public static void MenuSuaBill() {
    while(true) {
          ConsoleProgram.clearConsole();
          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.println(Color.Line(41, '=') + " DANH SACH HOA DON " + Color.Line(41, '=') + Color.GREEN);
          System.out.print(
          Color.Line(100, '-') +
          "\nSTT\tKHACHHANG\tSODIENTHOAI\tTONGTIEN\tNGAYTHANHTOAN\n" +
          Color.Line(100, '-'));

          BillManagement.Instance().PrintList();

          System.out.println(Color.Line(100, '-'));
          System.out.println(Color.RESET);
          System.out.println("Nhap 0 de quay lai");

          int stt = Validate.CheckNumberInt("Stt hoa don can sua: ");
          if(stt == 0) return;
          if(stt > BillManagement.Instance().getListSize()) continue;

          MenuSuaBillChiTiet(stt);
          
    }
  }

  public static void MenuSuaBillChiTiet(int stt) {
    while(true) {
      ConsoleProgram.clearConsole();
      Bill bill = BillManagement.Instance().GetList().get(stt - 1);
      System.out.println(bill.InfoDetails());

      System.out.println("===========================================\n"
                      + "||  1. Ho ten khach hang                  ||\n"
                      + "||  2. So dien thoai khach hang           ||\n"
                      + "||  3. Them san pham                      ||\n"
                      + "||  4. Sua so luong san pham              ||\n"
                      + "||  5. Xoa san pham                       ||\n"
                      + "||  6. Ngay thanh toan                    ||\n"
                      + "||  0. Quay lai                           ||\n"
                      + "===========================================");
      System.out.print("~~> Lua chon muc can sua: ");
      String line = ConsoleProgram.INPUT.nextLine();
      switch(line) {
        case "1": 
          System.out.print("\nNhap lai ho ten khach hang: ");
          String customerName = ConsoleProgram.INPUT.nextLine();
          bill.setCustomerName(customerName);
          BillManagement.Instance().Update(stt-1, bill, ENV.pathBill);
          break;

        case "2": 
          System.out.print("\nNhap lai sdt khach hang: ");
          String customerPhone = ConsoleProgram.INPUT.nextLine();
          bill.setCustomerPhone(customerPhone);
          BillManagement.Instance().Update(stt-1, bill, ENV.pathBill);
          break;

        case "3": 
          MenuThemSPVaoBill(bill);
          BillManagement.Instance().Update(stt-1, bill, ENV.pathBill);
          break;

        case "4": 
          MenuSuaSLSpTrongBill(bill);
          BillManagement.Instance().Update(stt-1, bill, ENV.pathBill);
          break;

        case "5": 
          MenuXoaSpKhoiBill(bill);
          BillManagement.Instance().Update(stt-1, bill, ENV.pathBill);
          break;
          
        case "6":
          System.out.println("\nNhap lai ngay thanh toan: ");

          int dayOfMonth = Validate.CheckNumberInt("Ngay: ");
          int month = Validate.CheckNumberInt("Thang: ");
          int year = Validate.CheckNumberInt("Nam: ");

          bill.setDate(LocalDate.of(year, month, dayOfMonth));
          BillManagement.Instance().Update(stt -1, bill, ENV.pathBill);
          break; 

        case "0": 
          return;

        default:
      }
    }
  }

  public static void MenuXoaBill() {
    while(true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(41, '=') + " DANH SACH HOA DON " + Color.Line(41, '=') + Color.GREEN);
      System.out.print(
      Color.Line(100, '-') +
      "\nSTT\tKHACHHANG\tSODIENTHOAI\tTONGTIEN\tNGAYTHANHTOAN\n" +
      Color.Line(100, '-'));

      BillManagement.Instance().PrintList();

      System.out.println(Color.Line(100, '-'));
      System.out.println(Color.RESET);

      System.out.println("Nhap 0 de quay lai");
      int stt = Validate.CheckNumberInt("Stt hoa don can sua: ");

      if(stt == 0) return;

      if(BillManagement.Instance().Delete(stt - 1, ENV.pathBill)) {
        System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
        System.out.print("\n~~> DA XOA THANH CONG !!! <~~" + Color.RESET);

        try {
          Thread.sleep(800);
        } catch (Exception e) {}
      }
    }
  }





  public static void MenuQuanLyNhanSu() {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println("============= QUAN LY NHAN SU =============" + Color.RESET);
      System.out.println("===========================================\n"
                      + "||  1. Xuat danh sach nhan su            ||\n"
                      + "||  2. Them nhan su                      ||\n"
                      + "||  3. Sua nhan su                       ||\n"
                      + "||  4. Xoa nhan su                       ||\n"
                      + "||  5. Tim kiem nhan su                  ||\n"
                      + "||  0. Quay lai                          ||\n"
                      + "===========================================");
      System.out.print("~~> Lua chon: ");
      String line = ConsoleProgram.INPUT.nextLine();

      switch (line) {
        case "1":
          ConsoleProgram.clearConsole();
          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.println(Color.Line(41, '=') + " DANH SACH NHAN SU " + Color.Line(41, '=') + Color.GREEN);
          System.out.print(Color.Line(100, '-') +
              "\nSTT\tTK  \t HO VA TEN\t   TUOI\t   GT\t  SODIENTHOAI\t  LUONG \tLUONG+\t   CHUC VU\n" +
              Color.Line(100, '-'));

          PersonManagement.Instance().PrintList();

          System.out.println(Color.Line(100, '-'));
          System.out.print(Color.RESET);

          System.out.print("\nNhan phim bat ki de quay lai: ");
          ConsoleProgram.INPUT.nextLine();
          break;
        case "2":
          MenuThemNhanSu();
          break;

        case "3":
          MenuSuaNhanSu();
          break;

        case "4":
          MenuXoaNhanSu();
          break;

        case "5":
          MenuTimKiemNhanSu();
          break;

        case "0":
          return;

        default:
          continue;

      }
    }
  }

  public static void MenuThemNhanSu() {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println("=============== THEM NHAN SU ==============" + Color.RESET);
      System.out.println("===========================================\n"
                        + "||  1. Them mot nhan vien                ||\n"
                        + "||  2. Them mot quan ly                  ||\n"
                        + "||  0. Quay lai.                         ||\n"
                        + "===========================================");
      System.out.print("~~> Lua chon: ");
      String line = ConsoleProgram.INPUT.nextLine();
      switch (line) {
        case "1": {
          System.out.print(Color.GREEN);
          System.out.println("\n--------------- NHAP THONG TIN NHAN VIEN --------------" + Color.RESET);
   
          String taiKhoan = Validate.CheckEmpty("Tai Khoan: ");
          String matKhau = Validate.CheckEmpty("Mat khau: ");       
          String hoTen = Validate.CheckEmpty("Ho ten: ");        
          int tuoi = Validate.CheckNumberInt("Tuoi: ");       
          String gioiTinh = Validate.CheckEmpty("Gioi tinh (Nam/Nu): ");
          String soDienThoai = Validate.CheckEmpty("So dien thoai: ");        
          double salary = Validate.CheckNumberDouble("Tuoi: ");
        

          Person nhanvien = new Staff(taiKhoan, matKhau, hoTen, tuoi, gioiTinh, soDienThoai, salary);
          PersonManagement.Instance().Add(nhanvien, ENV.pathPerson);

          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.print("\n~~> DA THEM THANH CONG !!! <~~" + Color.RESET);

          try {
            Thread.sleep(800);
          } catch (Exception e) {}

          break;
        }

        case "2": {
          System.out.print(Color.GREEN);
          System.out.println("\n--------------- NHAP THONG TIN QUAN LY --------------" + Color.RESET);
          String taiKhoan = Validate.CheckEmpty("Tai Khoan: ");
          String matKhau = Validate.CheckEmpty("Mat khau: ");       
          String hoTen = Validate.CheckEmpty("Ho ten: ");        
          int tuoi = Validate.CheckNumberInt("Tuoi: ");       
          String gioiTinh = Validate.CheckEmpty("Gioi tinh (Nam/Nu): ");
          String soDienThoai = Validate.CheckEmpty("So dien thoai: ");        
          double salary = Validate.CheckNumberDouble("Luong co ban: ");
          double salaryBonus = Validate.CheckNumberDouble("Luong thuong: ");


          Person quanly = new Manager(taiKhoan, matKhau, hoTen, tuoi, gioiTinh, soDienThoai, salary, salaryBonus);
          PersonManagement.Instance().Add(quanly, ENV.pathPerson);

          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.print("\n~~> DA THEM THANH CONG !!! <~~" + Color.RESET);

          try {
            Thread.sleep(800);
          } catch (Exception e) {}

          break;
        }
        case "0":
          return;

        default:
          continue;

      }
    }
  }

  public static void MenuXoaNhanSu() {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(41, '=') + " DANH SACH NHAN SU " + Color.Line(41, '=') + Color.GREEN);
      System.out.print(Color.Line(100, '-') +
          "\nSTT\tTK  \t HO VA TEN\t   TUOI\t   GT\t  SODIENTHOAI\t  LUONG \tLUONG+\t   CHUC VU\n" +
          Color.Line(100, '-'));

      PersonManagement.Instance().PrintList();

      System.out.println(Color.Line(100, '-'));
      System.out.print(Color.RESET);

      System.out.println("\nNhap 0 de quay lai");
      int stt = Validate.CheckNumberInt("Nhap STT nhan su can xoa: ");

     
      

      if(stt == 0) return;

      if(PersonManagement.Instance().Delete(stt - 1, ENV.pathPerson)) {
        System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
        System.out.print("\n~~> DA XOA THANH CONG !!! <~~" + Color.RESET);

        try {
          Thread.sleep(800);
        } catch (Exception e) {}
      }
      
        
    }
    

  }

  public static void MenuSuaNhanSu() {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(41, '=') + " DANH SACH NHAN SU " + Color.Line(41, '=') + Color.GREEN);
      System.out.print(Color.Line(100, '-') +
          "\nSTT\tTK  \t HO VA TEN\t   TUOI\t   GT\t  SODIENTHOAI\t  LUONG \tLUONG+\t   CHUC VU\n" +
          Color.Line(100, '-'));

      PersonManagement.Instance().PrintList();

      System.out.println(Color.Line(100, '-'));
      System.out.print(Color.RESET);

      System.out.println("\nNhap 0 de quay lai");
      int stt = Validate.CheckNumberInt("Nhap STT nhan su can sua: ");


      if (stt == 0) return;
      if (stt > PersonManagement.Instance().getListSize()) continue;

      Person person = PersonManagement.Instance().GetList().get(stt - 1);
      double salary = 0;
      double salaryBonus = 0;

      if (person instanceof Manager) {
        Manager quanLy = (Manager) person;
        salary = quanLy.getSalary();
        salaryBonus = quanLy.getSalaryBonus();
      } else {
        Staff nhanVien = (Staff) person;
        salary = nhanVien.getSalary();
      }

      while (true) {
        ConsoleProgram.clearConsole();
        System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
        System.out.println("======== THONG TIN NHAN SU MUON SUA ========" + Color.RESET);
        System.out.println("============================================\n"
        + "||" + Color.GREEN + "  Tai khoan: " + Color.YELLOW + person.getUsername() + "\n" + Color.RESET
        + "||" + Color.GREEN + "  1. Ho va ten: " + Color.YELLOW + person.getFullName() + "\n" + Color.RESET
        + "||" + Color.GREEN + "  2. Tuoi: " + Color.YELLOW + person.getAge() + "\n" + Color.RESET
        + "||" + Color.GREEN + "  3. Gioi tinh: " + Color.YELLOW + person.getGender() + "\n" + Color.RESET
        + "||" + Color.GREEN + "  4. So dien thoai: " + Color.YELLOW + person.getPhone() + "\n" + Color.RESET
        + "||" + Color.GREEN + "  5. Luong co ban: " + Color.YELLOW + salary + "\n" + Color.RESET
        + "||" + Color.GREEN + "  6. Luong thuong (QL): " + Color.YELLOW + salaryBonus + "\n" + Color.RESET
        + "||" + Color.GREEN + "  0. Quay lai\n"
        + Color.RESET + "============================================");
        System.out.print("~~> Lua chon muc de sua: ");
        String line = ConsoleProgram.INPUT.nextLine();
        
        switch (line) {
          case "1": {
            String fullName = Validate.CheckEmpty("Nhap lai ho va ten: ");  
            person.setFullName(fullName);
            PersonManagement.Instance().Update(stt - 1, person, ENV.pathPerson);
            break;
          }
          case "2": {
            int age = Validate.CheckNumberInt("Nhap lai tuoi: ");  
            person.setAge(age);
            PersonManagement.Instance().Update(stt - 1, person, ENV.pathPerson);
            break;
          }
          case "3": {
            String gender = Validate.CheckEmpty("Nhap lai gioi tinh (Nam/Nu): ");
            person.setGender(gender);
            PersonManagement.Instance().Update(stt - 1, person, ENV.pathPerson);
            break;
          }
          case "4": {
            String phone = Validate.CheckEmpty("Nhap lai so dien thoai: ");
            person.setPhone(phone);
            PersonManagement.Instance().Update(stt - 1, person, ENV.pathPerson);
            break;
          }
          case "5": {
            salary = Validate.CheckNumberDouble("Nhap lai luong co ban: ");
            if (person instanceof Manager) {
              Manager quanLy = (Manager) person;
              quanLy.setSalary(salary);
            } else {
              Staff nhanVien = (Staff) person;
              nhanVien.setSalary(salary);
            }
            PersonManagement.Instance().Update(stt - 1, person, ENV.pathPerson);
            break;
          }
          case "6": {

            if (person instanceof Staff) {
              System.out.print(Color.YELLOW + Color.RED_BACKGROUND);
              System.out.println("Quan ly moi co luong thuong !!!" + Color.RESET);
              System.out.print("Nhan phim bat ky de tiep tuc: ");
              ConsoleProgram.INPUT.nextLine();
              PersonManagement.Instance().Update(stt - 1, person, ENV.pathPerson);
              break;
            }

            salaryBonus = Validate.CheckNumberDouble("Nhap lai luong thuong: ");
            Manager quanLy = (Manager) person;
            quanLy.setSalaryBonus(salaryBonus);
            PersonManagement.Instance().Update(stt - 1, person, ENV.pathPerson);
            break;
          }

          case "0":
            return;

          default:
        }
      }
    }

  }

  public static void MenuTimKiemNhanSu() {
    while (true) {
      ConsoleProgram.clearConsole();
      PersonTable.Instance().showTable(PersonManagement.Instance().GetList());
      System.out.print("\nNhap 0 de quay lai");
      System.out.print("\nTim kiem theo ho ten nhan su: ");
      String hoTen = ConsoleProgram.INPUT.nextLine();

      if (hoTen.equals("0")) return;

      while (true) {
        ConsoleProgram.clearConsole();

        List<Person> personList = PersonManagement.Instance().SearchByName(hoTen);
        PersonTable.Instance().showTable(personList);

        System.out.print("\nNhap 0 de quay lai");
        System.out.print("\nTim kiem theo ho ten nhan su: ");
        hoTen = ConsoleProgram.INPUT.nextLine();

        if (hoTen.equals("0")) return;

      }
    }
  }


  public static void MenuThongKe(){
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLUE_BACKGROUND);
      System.out.println("============== THONG KE HOA DON ===============" + Color.RESET);
      System.out.println("===============================================\n"
                      + "||  1. Thong ke hoa don ngay/thang/nam       ||\n"
                      + "||  2. Thong ke hoa don trong ngay hom nay   ||\n"
                      + "||  3. Thong ke tat ca hoa don               ||\n"
                      + "||  0. Quay lai                              ||\n"
                      + "===============================================");
      System.out.print("~~> Lua chon: ");
      String line = ConsoleProgram.INPUT.nextLine();

      switch (line) {
        case "1":
          ThongKeHoaDonDMY();
          break;

        case "2":
          ThongKeHoaDonHomNay();
          break;

        case "3":
          ThongKeTatCa();
          break;

        case "0":
          return;

        default:
          continue;

      }
    }
  }

  private static void ThongKeTatCa() {
    ConsoleProgram.clearConsole();
    System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
    System.out.println(Color.Line(41, '=') + " DANH SACH HOA DON " + Color.Line(41, '=') + Color.GREEN);
    System.out.print(
      Color.Line(100, '-') +
      "\nSTT\tKHACHHANG\tSODIENTHOAI\tTONGTIEN\tNGAYTHANHTOAN\n" +
      Color.Line(100, '-')+Color.RESET);

    List<Bill> listBill = BillManagement.Instance().GetList();
    int count = listBill.size();
    int sum = 0;

    System.out.println(Color.YELLOW);

    if (count == 0) 
      System.out.println("\n\t\t\t\t\tKHONG CO KET QUA NAO TRUNG KHOP");
    else{
      for (int i = 1; i <= count; i++){
        sum += listBill.get(i - 1).SumPrice();
        System.out.println("[" + i + "]\t" + listBill.get(i - 1).Info());
      }
    }

    System.out.println(Color.RED);
    System.out.print("\nTong tat ca hoa don la: "+sum);
    System.out.println(Color.RESET);

    System.out.print("\nNhap phim bat ky de quay lai: ");
    ConsoleProgram.INPUT.nextLine();
  }

  private static void ThongKeHoaDonHomNay() {
    ConsoleProgram.clearConsole();
    System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
    System.out.println(Color.Line(41, '=') + " DANH SACH HOA DON " + Color.Line(41, '=') + Color.GREEN);
    System.out.print(
    Color.Line(100, '-') +
    "\nSTT\tKHACHHANG\tSODIENTHOAI\tTONGTIEN\tNGAYTHANHTOAN\n" +
    Color.Line(100, '-')+Color.RESET);

    List<Bill> listBill = BillManagement.Instance().getBillToday();
    int count = listBill.size();
    int sum = 0;

    System.out.println(Color.YELLOW);
    if (count == 0) 
      System.out.println("\n\t\t\t\t\tKHONG CO KET QUA NAO TRUNG KHOP");
    else{
      for (int i = 1; i <= count; i++){
        sum += listBill.get(i - 1).SumPrice();
        System.out.println("[" + i + "]\t" + listBill.get(i - 1).Info());
      }
    }

    System.out.println(Color.RED);
    System.out.print("\nTong hoa don trong khoang la: "+sum);

    System.out.println(Color.RESET);
    System.out.print("\nNhap phim bat ky de quay lai: ");
    ConsoleProgram.INPUT.nextLine();
  }

  public static void ThongKeHoaDonDMY() {
    ConsoleProgram.clearConsole();
    System.out.print("Nhap from (nam/thang/ngay): ");
    String from = ConsoleProgram.INPUT.nextLine();
    System.out.print("Nhap to (nam/thang/ngay): ");
    String to = ConsoleProgram.INPUT.nextLine();

    System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
    System.out.println(Color.Line(41, '=') + " DANH SACH HOA DON " + Color.Line(41, '=') + Color.GREEN);
    System.out.print(
      Color.Line(100, '-') +
      "\nSTT\tKHACHHANG\tSODIENTHOAI\tTONGTIEN\tNGAYTHANHTOAN\n" +
      Color.Line(100, '-')+Color.RESET);

    List<Bill> listBill = BillManagement.Instance().getBillByRange(LocalDate.parse(from), LocalDate.parse(to));
    int count = listBill.size();
    int sum = 0;

    System.out.println(Color.YELLOW);
    if (count == 0) 
      System.out.println("\n\t\t\t\t\tKHONG CO KET QUA NAO TRUNG KHOP");
    else{
      for (int i = 1; i <= count; i++){
        sum += listBill.get(i - 1).SumPrice();
        System.out.println("[" + i + "]\t" + listBill.get(i - 1).Info());
      }
    }

    System.out.println(Color.RED);
    System.out.print("\nTong hoa don trong khoang la: "+sum);

    System.out.println(Color.RESET);
    System.out.print("\nNhap phim bat ky de quay lai: ");

    ConsoleProgram.INPUT.nextLine();
  }



  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);
    Person person = PersonManagement.Instance().CheckLogin();
    boolean isLogin = false;
    // Neu ko dang nhat dc
    // Person manager = new Manager("admin", "admin", "abc", 12, "nam", "09", 12, 12);
    // Menu(input, manager);

    do {
      if (person == null) person = MenuDangNhap();

      if (person == null) {
        input.close();
        return;
      }

      if (person != null) isLogin = Menu(person);
      person = null;

    } while (isLogin);
  }
}
