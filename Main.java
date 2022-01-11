package DOANCUOIKI;

import java.io.Console;
import java.time.LocalDate;
import java.util.List;

import DOANCUOIKI.management.BillManagement;
import DOANCUOIKI.management.PersonManagement;
import DOANCUOIKI.management.ProductManagement;
import DOANCUOIKI.util.Color;
import DOANCUOIKI.util.ConsoleProgram;
import DOANCUOIKI.util.Validate;

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
      System.out.print(Color.YELLOW + "~~> Lua chon: " + Color.RESET);
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
      System.out.print(Color.YELLOW + "~~> Lua chon: " + Color.RESET);
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

    String hoTen = Validate.CheckEmpty("> Ten Khach Hang: ");
    String sdt = Validate.CheckEmpty("> So Dien Thoai: ");
    Bill bill = new Bill(hoTen, sdt);

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
      System.out.print(Color.YELLOW + "~~> Lua chon: " + Color.RESET);
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
      Table.<Product>Instance().showTable("SAN PHAM",ENV.columnTableProduct,ProductManagement.Instance().GetList());
      
      System.out.println("================================================\n"
                        +"||  a. Loc san pham theo ten                  ||\n"
                        +"||  b. Loc san pham theo loai                 ||\n"
                        +"||  c. Loc san pham theo khoang gia           ||\n"            
                        +"||  0. Quay lai                               ||\n"
                        +"================================================");
      System.out.println(Color.YELLOW);
      System.out.print("~~> Nhap stt san pham can mua \n(hoac su dung chuc nang): " + Color.RESET);
      String line = ConsoleProgram.INPUT.nextLine();
      switch(line) {
        case "a":
          System.out.print("\nTim kiem theo ten san pham: ");
          String nameProduct = ConsoleProgram.INPUT.nextLine();
          MenuLocSanPhamTheoTen(bill, nameProduct);
          break;

        case "b":
          System.out.print("\nTim kiem theo loai san pham: ");
          String categoryProduct = ConsoleProgram.INPUT.nextLine();
          MenuLocSanPhamTheoLoai(bill, categoryProduct);
          break;

        case "c":
          double from = Validate.CheckNumberDouble("Tim kiem san pham gia tu (gia > 0): ");
          double to = Validate.CheckNumberDouble("den gia: ");
          MenuLocSanPhamTheoKhoangGia(bill, from, to);
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

          ConsoleProgram.printMessageNoti("add");
          
      }
    }
  }

  public static void MenuSuaSLSpTrongBill(Bill bill) {
    while(true) {
      ConsoleProgram.clearConsole();
      System.out.println(bill.InfoDetails());

      System.out.println("\n(Nhap 0 de quay lai)");
      int stt = Validate.CheckNumberInt("~~> Nhap stt san pham muon sua: ");

      if(stt == 0) return;
      int amount = Validate.CheckNumberInt("So luong thay doi (lon hon 0): ");

      if(bill.UpdateDetails(stt - 1 , amount)) {
        ConsoleProgram.printMessageNoti("update");
      }
    }
  }

  public static void MenuXoaSpKhoiBill(Bill bill) {
    while(true) {
      ConsoleProgram.clearConsole();
      System.out.println(bill.InfoDetails());

      System.out.println("\n(Nhap 0 de quay lai)");
      int stt = Validate.CheckNumberInt("~~> Nhap stt san pham muon xoa: ");

      if(stt == 0) return;

      if(bill.DeleteDetails(stt - 1 )) {
        ConsoleProgram.printMessageNoti("delete");
      }
    }
  }
  
  public static void MenuLocSanPhamTheoTen(Bill bill, String nameProduct) {
    while(true) {
      ConsoleProgram.clearConsole();
      List<Product> productList = ProductManagement.Instance().SearchByCategory(nameProduct);
      Table.<Product>Instance().showTable("SAN PHAM",ENV.columnTableProduct,productList);
      
      System.out.println("\n(Nhap 0 de quay lai)");
      int stt = Validate.CheckNumberInt("~~> Nhap stt san pham can mua: ");
    
      if(stt == 0) return; 
      if(stt > productList.size()) continue;

      Product product = productList.get(stt - 1 );

      int amount = Validate.CheckNumberInt("So luong: ");
      BillDetails details = new BillDetails(product, amount);
      bill.AddDetails(details);

      ConsoleProgram.printMessageNoti("add");
      
    }
  }

  public static void MenuLocSanPhamTheoLoai(Bill bill, String categoryProduct) {
    while(true) {
      ConsoleProgram.clearConsole();
      List<Product> productList = ProductManagement.Instance().SearchByCategory(categoryProduct);
      Table.<Product>Instance().showTable("SAN PHAM",ENV.columnTableProduct,productList);

  
      System.out.println("\n(Nhap 0 de quay lai)");
      int stt = Validate.CheckNumberInt("~~> Nhap stt san pham can mua: ");
    
      if(stt == 0) return;
      if(stt > productList.size()) continue;

      Product product = productList.get(stt - 1 );

      int amount = Validate.CheckNumberInt("So luong: ");
      BillDetails details = new BillDetails(product, amount);
      bill.AddDetails(details);

      ConsoleProgram.printMessageNoti("add");
      
    }
  }
  
  public static void MenuLocSanPhamTheoKhoangGia(Bill bill, double from, double to) {

    while(true) {
      ConsoleProgram.clearConsole();
      List<Product> productList = ProductManagement.Instance().SearchByPrice(from, to);
      Table.<Product>Instance().showTable("SAN PHAM",ENV.columnTableProduct,productList);
      

      System.out.println("\n(Nhap 0 de quay lai)");
      int stt = Validate.CheckNumberInt("~~> Nhap stt san pham can mua: ");
    
      if(stt == 0) return;
      if(stt > productList.size()) continue;

      Product product = productList.get(stt - 1 );

      int amount = Validate.CheckNumberInt("So luong: ");
      BillDetails details = new BillDetails(product, amount);
      bill.AddDetails(details);

      ConsoleProgram.printMessageNoti("add");
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
                        +"===========================================");
      System.out.print(Color.YELLOW + "~~> Lua chon: " + Color.RESET);

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
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println("============= QUAN LY SAN PHAM =============" + Color.RESET);
      System.out.println("============================================\n"
                      + "||  1. Xuat danh sach san pham             ||\n"
                      + "||  2. Them san pham                       ||\n"
                      + "||  3. Sua san pham                        ||\n"
                      + "||  4. Xoa san pham                        ||\n"
                      + "||  5. Tim kiem san pham                   ||\n"
                      + "||  0. Quay lai                            ||\n"
                      + "============================================");
      System.out.print(Color.YELLOW + "~~> Lua chon: " + Color.RESET);
      String line = ConsoleProgram.INPUT.nextLine();
      switch (line) {
        case "1":
          ConsoleProgram.clearConsole();
          Table.<Product>Instance().showTable("SAN PHAM",ENV.columnTableProduct,ProductManagement.Instance().GetList());

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

    ConsoleProgram.printMessageNoti("add");

  }

  public static void MenuSuaSanPhamTrongDS() {
    while (true) {
      ConsoleProgram.clearConsole();
      Table.<Product>Instance().showTable("SAN PHAM",ENV.columnTableProduct,ProductManagement.Instance().GetList());

      System.out.println("\n(Nhap 0 de quay lai)");
      int index = Validate.CheckNumberInt("~~> Nhap stt san pham can sua: ");


      if (index == 0) return;
      if(index > ProductManagement.Instance().getListSize()) continue;
      Product product = ProductManagement.Instance().GetList().get(index - 1);

      while (true) {
        ConsoleProgram.clearConsole();
        System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
        System.out.println("======== THONG TIN SAN PHAM MUON SUA ========" + Color.RESET);
        System.out.println("============================================\n"
        + "||  1. Ten san pham: " + Color.YELLOW + product.getName() + "\n" + Color.RESET
        + "||  2. Loai: " + Color.YELLOW + product.getCategory() + "\n" + Color.RESET
        + "||  3. Gia: " + Color.YELLOW + product.getPrice() + "\n" + Color.RESET
        + "||  0. Luu va quay lai\n" 
        + Color.RESET + "============================================");
        System.out.print(Color.YELLOW + "~~> Lua chon muc de sua: " + Color.RESET);

        String line = ConsoleProgram.INPUT.nextLine();
        switch (line) {
          case "1": {
            String nameProduct = Validate.CheckEmpty("Nhap lai ten san pham: ");
            product.setName(nameProduct);
            break;
          }
          case "2": {
            String categoryProduct = Validate.CheckEmpty("Nhap lai loai san pham: ");
            product.setCategory(categoryProduct);
            break;
          }
          case "3": {
            double priceProduct = Validate.CheckNumberDouble("Nhap lai gia san pham: ");
            product.setPrice(priceProduct);
            break;
          }
          case "0":
            ProductManagement.Instance().Update(index - 1, product, ENV.pathProduct);
            ConsoleProgram.printMessageNoti("update");
            return;

          default:
        }
      }
    }
  }

  public static void MenuXoaSanPhamTrongDS() {
    while (true) {
      ConsoleProgram.clearConsole();
      Table.<Product>Instance().showTable("SAN PHAM",ENV.columnTableProduct,ProductManagement.Instance().GetList());

      System.out.println("\n(Nhap 0 de quay lai)");
      int id = Validate.CheckNumberInt("~~> Nhap stt san pham can xoa: ");

      if(id == 0) return;
      
      if(ProductManagement.Instance().Delete(id - 1, ENV.pathProduct)) {
        ConsoleProgram.printMessageNoti("delete");
      }  
    }
  }
  
  public static void MenuTimKiemSanPhamTrongDS() {
    while (true) {
      ConsoleProgram.clearConsole();
      Table.<Product>Instance().showTable("SAN PHAM",ENV.columnTableProduct,ProductManagement.Instance().GetList());
      System.out.println("===========================================\n"
                      + "||  1. Tim kiem san pham theo ten         ||\n"
                      + "||  2. Tim kiem san pham theo loai        ||\n"
                      + "||  3. Tim kiem san pham theo gia         ||\n"
                      + "||  0. Quay lai                           ||\n"
                      + "===========================================");
      System.out.print(Color.YELLOW + "~~> Lua chon: " + Color.RESET);
      String line = ConsoleProgram.INPUT.nextLine();

      switch(line) {
        case "1": {
          System.out.print("\nNhap ten san pham can tim: ");
          String nameProduct = ConsoleProgram.INPUT.nextLine();
          MenuTimKiemSanPhamTheoTen(nameProduct);
          break;
        }

        case "2": {
          System.out.print("\nNhap loai san pham can tim: ");
          String categoryProduct = ConsoleProgram.INPUT.nextLine();
          MenuTimKiemSanPhamTheoLoai(categoryProduct);
          break;
        }

        case "3": {
          double from = Validate.CheckNumberDouble("\nNhap gia san pham can tim \ntu (gia > 0): ");
          double to = Validate.CheckNumberDouble("den: ");
          MenuTimKiemSanPhamTheoGia(from, to);
          break;
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
      List<Product> productList = ProductManagement.Instance().SearchByName(nameProduct);
      Table.<Product>Instance().showTable("SAN PHAM",ENV.columnTableProduct,productList);
      System.out.println("\n(Nhap 0 de quay lai)");
      System.out.print("~~> Nhap ten san pham can tim: ");
      nameProduct = ConsoleProgram.INPUT.nextLine();

      if (nameProduct.equals("0")) return;
    }
  }

  public static void MenuTimKiemSanPhamTheoLoai(String categoryProduct) {
    while (true) {
      ConsoleProgram.clearConsole();
      List<Product> productList = ProductManagement.Instance().SearchByCategory(categoryProduct);
      Table.<Product>Instance().showTable("SAN PHAM",ENV.columnTableProduct,productList);

      System.out.println("\n(Nhap 0 de quay lai)");
      System.out.print("~~> Nhap loai san pham can tim: ");
      categoryProduct = ConsoleProgram.INPUT.nextLine();

      if (categoryProduct.equals("0")) return;
    }
  }

  public static void MenuTimKiemSanPhamTheoGia(double from, double to) {
    while (true) {
      ConsoleProgram.clearConsole();

      List<Product> productList = ProductManagement.Instance().SearchByPrice(from, to);
      Table.<Product>Instance().showTable("SAN PHAM",ENV.columnTableProduct,productList);

      System.out.println("\n(Nhap 0 de quay lai)");
      from = Validate.CheckNumberDouble("~~> Nhap gia san pham can tim \ntu (gia > 0): ");
      if (from == 0) return;
      to =   Validate.CheckNumberDouble("den: ");
      
    }
  }



  public static void MenuQuanLyHoaDon() {
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
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
          Table.<Bill>Instance().showTable("HOA DON",ENV.columnTableBill,BillManagement.Instance().GetList());

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
          MenuTimKiemBill();
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
          Table.<Bill>Instance().showTable("HOA DON",ENV.columnTableBill,BillManagement.Instance().GetList());

          System.out.println("\n(Nhap 0 de quay lai)");
          int stt = Validate.CheckNumberInt("~~> Nhap stt hoa don can sua: ");

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
                      + "||  0. Luu va quay lai                    ||\n"
                      + "===========================================");
      System.out.print(Color.YELLOW + "~~> Lua chon mua de sua: " + Color.RESET);
      String line = ConsoleProgram.INPUT.nextLine();
      switch(line) {
        case "1": 
          System.out.print("\nNhap lai ho ten khach hang: ");
          String customerName = ConsoleProgram.INPUT.nextLine();
          bill.setCustomerName(customerName);
          break;

        case "2": 
          System.out.print("\nNhap lai sdt khach hang: ");
          String customerPhone = ConsoleProgram.INPUT.nextLine();
          bill.setCustomerPhone(customerPhone);
          break;

        case "3": 
          MenuThemSPVaoBill(bill);
          break;

        case "4": 
          MenuSuaSLSpTrongBill(bill);
          break;

        case "5": 
          MenuXoaSpKhoiBill(bill);
          break;
          
        case "6":
          System.out.println("\nNhap lai ngay thanh toan: ");

          int dayOfMonth = Validate.CheckNumberInt("Ngay: ");
          int month = Validate.CheckNumberInt("Thang: ");
          int year = Validate.CheckNumberInt("Nam: ");

          bill.setDate(LocalDate.of(year, month, dayOfMonth));
          break; 

        case "0": 
          if(BillManagement.Instance().Update(stt-1, bill, ENV.pathBill)) {
            ConsoleProgram.printMessageNoti("update");
          }
          return;

        default:
      }
    }
  }

  public static void MenuXoaBill() {
    while(true) {
      ConsoleProgram.clearConsole();
       Table.<Bill>Instance().showTable("HOA DON",ENV.columnTableBill,BillManagement.Instance().GetList());

      System.out.println("\n(Nhap 0 de quay lai)");
      int stt = Validate.CheckNumberInt("~~> Nhap stt hoa don can sua: ");

      if(stt == 0) return;

      if(BillManagement.Instance().Delete(stt - 1, ENV.pathBill)) {
        ConsoleProgram.printMessageNoti("delete");
      }
    }
  }

  public static void MenuTimKiemBill() {
    while(true) {
      ConsoleProgram.clearConsole();
      Table.<Bill>Instance().showTable("HOA DON",ENV.columnTableBill,BillManagement.Instance().GetList());
      System.out.println("===========================================\n"
                      + "||  1. Tim kiem hoa don theo ten          ||\n"
                      + "||  2. Tim kiem hoa don theo SDT          ||\n"
                      + "||  0. Quay lai                           ||\n"
                      + "===========================================");
      System.out.print("~~> Lua chon: ");
      String line = ConsoleProgram.INPUT.nextLine();

      switch(line) {
        case "1":
        System.out.print("\nNhap ten khach hang can tim: ");
        String customerName = ConsoleProgram.INPUT.nextLine();
        MenuTimKiemBillTheoTen(customerName);
        break;
        case "2":
        System.out.print("\nNhap so dien thoai khach hang can tim: ");
        String customerPhone = ConsoleProgram.INPUT.nextLine();
        MenuTimKiemBillTheoSDT(customerPhone);
        break;
        case "0":
        return;
        default:
        continue;
      }

    }
  }

  public static void MenuTimKiemBillTheoTen(String customerName) {
    while(true) {
      ConsoleProgram.clearConsole();
      List<Bill> billList = BillManagement.Instance().SearchByCustomerName(customerName);
      Table.<Bill>Instance().showTable("HOA DON",ENV.columnTableBill,billList);

      System.out.println("\n(Nhap 0 de quay lai)");
      System.out.print("~~> Nhap ten khach hang can tim: ");
      customerName = ConsoleProgram.INPUT.nextLine();

      if(customerName.equals("0")) return;
    }
  }

  public static void MenuTimKiemBillTheoSDT(String customerPhone) {
    while(true) {
      ConsoleProgram.clearConsole();
      List<Bill> billList = BillManagement.Instance().SearchByCustomerPhone(customerPhone);
      Table.<Bill>Instance().showTable("HOA DON",ENV.columnTableBill,billList);

      System.out.println("\n(Nhap 0 de quay lai)");
      System.out.print("~~> Nhap so dien thoai khach hang can tim: ");
      customerPhone = ConsoleProgram.INPUT.nextLine();

      if(customerPhone.equals("0")) return;
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
          Table.<Person>Instance().showTable("HOA DON",ENV.columnTablePerson,PersonManagement.Instance().GetList());

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
   
          String username = Validate.CheckUsername();
          String password = Validate.CheckEmpty("Mat khau: ");       
          String fullName = Validate.CheckEmpty("Ho ten: ");        
          int age = Validate.CheckNumberInt("Tuoi: ");       
          String gender = Validate.CheckEmpty("Gioi tinh (Nam/Nu): ");
          String phone = Validate.CheckEmpty("So dien thoai: ");        
          double salary = Validate.CheckNumberDouble("Luong co ban: ");
        
          Person staff = new Staff(username, password, fullName, age, gender, phone, salary);
          PersonManagement.Instance().Add(staff, ENV.pathPerson);

          ConsoleProgram.printMessageNoti("add");
          break;
        }

        case "2": {
          System.out.print(Color.GREEN);
          System.out.println("\n--------------- NHAP THONG TIN QUAN LY --------------" + Color.RESET);
          String username = Validate.CheckUsername();
          String password = Validate.CheckEmpty("Mat khau: ");       
          String fullName = Validate.CheckEmpty("Ho ten: ");        
          int age = Validate.CheckNumberInt("Tuoi: ");       
          String gender = Validate.CheckEmpty("Gioi tinh (Nam/Nu): ");
          String phone = Validate.CheckEmpty("So dien thoai: ");        
          double salary = Validate.CheckNumberDouble("Luong co ban: ");
          double salaryBonus = Validate.CheckNumberDouble("Luong thuong: ");


          Person manager = new Manager(username, password, fullName, age, gender, phone, salary, salaryBonus);
          PersonManagement.Instance().Add(manager, ENV.pathPerson);

          ConsoleProgram.printMessageNoti("add");
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
      Table.<Person>Instance().showTable("HOA DON",ENV.columnTablePerson,PersonManagement.Instance().GetList());

      System.out.println("\n(Nhap 0 de quay lai)");
      int stt = Validate.CheckNumberInt("~~> Nhap stt nhan su can xoa: ");

      if(stt == 0) return;

      if(PersonManagement.Instance().Delete(stt - 1, ENV.pathPerson)) {
        ConsoleProgram.printMessageNoti("delete");
      }
    }
    

  }

  public static void MenuSuaNhanSu() {
    while (true) {
      ConsoleProgram.clearConsole();
      Table.<Person>Instance().showTable("HOA DON",ENV.columnTablePerson,PersonManagement.Instance().GetList());

      System.out.println("\n(Nhap 0 de quay lai)");
      int stt = Validate.CheckNumberInt("~~> Nhap stt nhan su can sua: ");


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
        + "|| Tai khoan: " + Color.YELLOW + person.getUsername() + "\n" + Color.RESET
        + "|| 1. Ho va ten: " + Color.YELLOW + person.getFullName() + "\n" + Color.RESET
        + "|| 2. Tuoi: " + Color.YELLOW + person.getAge() + "\n" + Color.RESET
        + "|| 3. Gioi tinh: " + Color.YELLOW + person.getGender() + "\n" + Color.RESET
        + "|| 4. So dien thoai: " + Color.YELLOW + person.getPhone() + "\n" + Color.RESET
        + "|| 5. Luong co ban: " + Color.YELLOW + salary + "\n" + Color.RESET
        + "|| 6. Luong thuong (QL): " + Color.YELLOW + salaryBonus + "\n" + Color.RESET
        + "|| 0. Luu va quay lai\n"
        + Color.RESET + "============================================");
        System.out.print(Color.YELLOW + "~~> Lua chon muc de sua: " + Color.RESET);
        String line = ConsoleProgram.INPUT.nextLine();
        
        switch (line) {
          case "1": {
            String fullName = Validate.CheckEmpty("Nhap lai ho va ten: ");  
            person.setFullName(fullName);
            break;
          }
          case "2": {
            int age = Validate.CheckNumberInt("Nhap lai tuoi: ");  
            person.setAge(age);
            break;
          }
          case "3": {
            String gender = Validate.CheckEmpty("Nhap lai gioi tinh (Nam/Nu): ");
            person.setGender(gender);
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
            break;
          }
          case "6": {

            if (person instanceof Staff) {
              System.out.print(Color.YELLOW + Color.RED_BACKGROUND);
              System.out.println("Quan ly moi co luong thuong !!!" + Color.RESET);
              System.out.print("Nhan phim bat ky de tiep tuc: ");
              ConsoleProgram.INPUT.nextLine();
              break;
            }

            salaryBonus = Validate.CheckNumberDouble("Nhap lai luong thuong: ");
            Manager quanLy = (Manager) person;
            quanLy.setSalaryBonus(salaryBonus);

            break;
          }
          case "0":
            if(PersonManagement.Instance().Update(stt - 1, person, ENV.pathPerson))
            ConsoleProgram.printMessageNoti("update");
            return;

          default:
            continue;
        }
      }
    }

  }

  public static void MenuTimKiemNhanSu() {
    while (true) {
      ConsoleProgram.clearConsole();
      Table.<Person>Instance().showTable("HOA DON",ENV.columnTablePerson,PersonManagement.Instance().GetList());

      System.out.println("\n(Nhap 0 de quay lai)");
      System.out.print(Color.YELLOW);
      System.out.print("~~> Nhap ho ten nhan su can tim: ");
      System.out.print(Color.RESET);
      String hoTen = ConsoleProgram.INPUT.nextLine();

      if (hoTen.equals("0")) return;

      while (true) {
        ConsoleProgram.clearConsole();

        List<Person> personList = PersonManagement.Instance().SearchByName(hoTen);
        Table.<Person>Instance().showTable("HOA DON",ENV.columnTablePerson,personList);

        System.out.println("\n(Nhap 0 de quay lai)");
        System.out.print(Color.YELLOW);
        System.out.print("~~> Nhap ho ten nhan su can tim: ");
        System.out.print(Color.RESET);
        hoTen = ConsoleProgram.INPUT.nextLine();

        if (hoTen.equals("0")) return;

      }
    }
  }



  public static void MenuThongKe(){
    while (true) {
      ConsoleProgram.clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println("============== THONG KE HOA DON ===============" + Color.RESET);
      System.out.println("===============================================\n"
                      + "||  1. Thong ke hoa don ngay/thang/nam        ||\n"
                      + "||  2. Thong ke hoa don trong ngay hom nay    ||\n"
                      + "||  3. Thong ke tat ca hoa don                ||\n"
                      + "||  0. Quay lai                               ||\n"
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

    List<Bill> billList = BillManagement.Instance().GetList();
    int count = billList.size();
    double sum = 0;
    
    ConsoleProgram.printTable(billList, "HOA DON", ENV.columnTableBill);

    for (int i = 1; i <= count; i++) {
      sum += billList.get(i - 1).SumPrice();
    }

    System.out.println(Color.WHITE + Color.RED_BACKGROUND);
    System.out.print("\nTong tat ca hoa don la:" + Color.YELLOW + "   " + sum);
    System.out.println(Color.RESET);

    System.out.print("\nNhap phim bat ky de quay lai: ");
    ConsoleProgram.INPUT.nextLine();
  }

  private static void ThongKeHoaDonHomNay() {
    ConsoleProgram.clearConsole();

    List<Bill> billList = BillManagement.Instance().getBillToday();
    int count = billList.size();
    int sum = 0;

    ConsoleProgram.printTable(billList, "HOA DON", ENV.columnTableBill);

    for (int i = 1; i <= count; i++){
      sum += billList.get(i - 1).SumPrice();
      System.out.println("[" + i + "]\t" + billList.get(i - 1).Info());
    }

    System.out.println(Color.RED);
    System.out.print("\nTong tien cac hoa don la: "+sum);
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


    List<Bill> billList = BillManagement.Instance().getBillByRange(LocalDate.parse(from), LocalDate.parse(to));
    int count = billList.size();
    int sum = 0;

    ConsoleProgram.printTable(billList, "HOA DON", ENV.columnTableBill);

    for (int i = 1; i <= count; i++){
      sum += billList.get(i - 1).SumPrice();
      System.out.println("[" + i + "]\t" + billList.get(i - 1).Info());
    }

    System.out.println(Color.RED);
    System.out.print("\nTong tien cac hoa don la: "+sum);
    System.out.println(Color.RESET);

    System.out.print("\nNhap phim bat ky de quay lai: ");
    ConsoleProgram.INPUT.nextLine();
  }



  public static void main(String[] args) {

    Person person = PersonManagement.Instance().CheckLogin();
    boolean isLogin = false;

    // Neu ko dang nhat dc
    // Person manager = new Manager("admin", "admin", "abc", 12, "nam", "09", 12, 12);
    // Menu(input, manager);

    do {
      if (person == null) person = MenuDangNhap();

      if (person == null) return;
      
      if (person != null) isLogin = Menu(person);

      person = null;

    } while (isLogin);
  }

}
