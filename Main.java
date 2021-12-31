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

public class Main {
  // phuong thuc static dc goi ma ko tao mot instance
  public static Person MenuDangNhap(Scanner input) {
    Console csl = System.console();
    while (true) {
      clearConsole();
      System.out.print(Color.BLUE_BACKGROUND);
      System.out.println("======== UNG DUNG QUAN LY BAN HANG ========" + Color.RESET);
      System.out.println("===========================================");
      System.out.println("||  1. Dang Nhap                         ||");
      System.out.println("||  0. Thoat                             ||");
      System.out.println("===========================================");
      System.out.print("~~> Lua chon: ");
      String line = input.nextLine();

      switch (line) {
        case "1":
          clearConsole();
          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.println("=========== DANG NHAP ===========" + Color.RESET);
          System.out.println("=================================");
          System.out.print(Color.GREEN + "Tai Khoan: " + Color.RESET);
          String taiKhoan = input.nextLine().trim();

          System.out.print(Color.GREEN + "Mat Khau: " + Color.RESET);
          char[] pwd = csl.readPassword();
          String matKhau = String.valueOf(pwd).trim();

          Person isLogin = PersonManagement.Instance().CheckAccount(taiKhoan, matKhau);

          if (isLogin != null) {
            return isLogin;
          } else {
            clearConsole();
            System.out.println(Color.YELLOW + Color.RED_BACKGROUND);
            System.out.print("Tai khoan hoac mat khau sai !!!" + Color.RESET);

            try {
              Thread.sleep(800);
            } catch (InterruptedException e) {
              e.printStackTrace();
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




  public static boolean Menu(Scanner input, Person person) {
    while (true) {
      clearConsole();
      String chucVu;
      if (person instanceof Manager)
        chucVu = "Quan Ly";
      else
        chucVu = "Nhan Vien";

      System.out.print(Color.BLUE_BACKGROUND);
      System.out.println("======== UNG DUNG QUAN LY BAN HANG ========" + Color.RESET);
      System.out.println("Tai khoan: " + person.getUsername() + "\tChuc vu: " + chucVu);
      System.out.println("===========================================");
      System.out.println("||  1. Ban Hang                          ||");
      System.out.println("||  2. Quan Ly                           ||");
      System.out.println("||  3. Dang Xuat                         ||");
      System.out.println("||  0. Thoat                             ||");
      System.out.println("===========================================");
      System.out.print("~~> Lua chon: ");
      String line = input.nextLine();

      switch (line) {
        case "1":
          MenuBanHang(input);
          break;

        case "2":
          if (person instanceof Manager)
            MenuQuanLy(input);
          else {
            clearConsole();
            System.out.print(Color.YELLOW + Color.RED_BACKGROUND);
            System.out.println("Chuc nang chi danh cho quan ly !!!" + Color.RESET);
            System.out.print("\nNhap phim bat ky de quay lai: ");
            input.nextLine();
          }
          break;

        case "3":

          return true;

        case "0":

          return false;

        default:
          continue;

      }
    }
  }




  //BAN HANGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
  public static void MenuBanHang(Scanner input) {
    clearConsole();
    System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
    System.out.println("==================== BAN HANG ====================" + Color.BLUE);
    System.out.println("------------ NHAP THONG TIN KHACH HANG -----------" + Color.RESET);
    
    System.out.print(Color.YELLOW + "> Ten Khach Hang: " + Color.RESET);
    String hoTen = input.nextLine();
    System.out.print(Color.YELLOW + "> So Dien Thoai: " + Color.RESET);
    String sdt = input.nextLine();
    Bill bill = new Bill(hoTen, sdt);
    
    System.out.println(Color.BLUE + "------------- ------------------------ ----------" + Color.RESET);

    while (true) {
      clearConsole();
      System.out.println(bill.InfoDetails());

      System.out.println(Color.GREEN);
      System.out.println("================================================");
      System.out.println("||  1. Them san pham cho hoa don              ||");
      System.out.println("||  2. Sua so luong san pham trong hoa don    ||");
      System.out.println("||  3. Xoa san pham khoi hoa don              ||");
      System.out.println("||  4. Thanh toan hoa don                     ||");
      System.out.println("||  0. Quay lai                               ||");
      System.out.println("================================================");
      System.out.println(Color.YELLOW);
      System.out.print("~~> Lua chon: " + Color.RESET);
      String line = input.nextLine();

      switch (line) {
        case "1":
          MenuThemSPVaoBill(input, bill);
          break;

        case "2":
          MenuSuaSLSpTrongBill(input, bill);
          break;

        case "3":
          MenuXoaSpKhoiBill(input, bill);
          break;
        case "4":
          clearConsole();
          System.out.println(bill.InfoDetails());

        
          bill.setDate(LocalDate.now());
          BillManagement.Instance().Add(bill, ENV.pathBill);

          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.println("\n~~> DA THANH TOAN THANH CONG !!! <~~" + Color.RESET);
         
          System.out.print("Nhan phim bat ki de quay lai trang chu: ");
          input.nextLine();
          return;

        case "0":
          System.out.println(Color.RED + Color.YELLOW_BACKGROUND);
          System.out.print("Neu quay lai hoa don tren se mat (Dong y nhan 'Y'/'y'):" + Color.RESET + " ");
          line = input.nextLine();
          if(line.equals("Y") || line.equals("y")) return;

        default:
          continue;
      }
    }
  }

  public static void MenuThemSPVaoBill(Scanner input, Bill bill) {
    while(true) {
      clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN);
      System.out.print(Color.Line(100, '-') +
          "\nSTT\tTEN SP\tLOAI\tGIA\n" +
          Color.Line(100, '-'));

      ProductManagement.Instance().PrintList();

      System.out.println(Color.Line(100, '-'));

      System.out.println(Color.GREEN);
      System.out.println("================================================");
      System.out.println("||  a. Loc san pham theo loai                 ||");
      System.out.println("||  b. Loc san pham theo khoang gia           ||");
      System.out.println("||  c. Loc san pham theo ten                  ||");
      System.out.println("||  0. Thoat                                  ||");
      System.out.println("================================================");
      System.out.println(Color.YELLOW);
      System.out.print("~~> Nhap stt san pham can mua \n(hoac su dung chuc nang): " + Color.RESET);
      String line = input.nextLine();
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
          int stt =  Integer.parseInt(line);
          Product product = ProductManagement.Instance().GetList().get(stt - 1 );
          System.out.print("So luong: ");
          int amount = input.nextInt();
          input.nextLine();

          BillDetails details = new BillDetails(product, amount);
          bill.AddDetails(details);

          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.print("\n~~> DA THEM THANH CONG !!! <~~" + Color.RESET);

          try {
            Thread.sleep(800);
          } catch (InterruptedException e) {
            e.printStackTrace();

          }
          
      }
      
    }
  }

  public static void MenuSuaSLSpTrongBill(Scanner input, Bill bill) {
    while(true) {
      clearConsole();
      System.out.println(bill.InfoDetails());

      System.out.println(Color.YELLOW);
      System.out.println("Nhap 0 de quay lai");
      System.out.print("Nhap stt san pham muon sua: " + Color.RESET);
      int stt = input.nextInt();
      input.nextLine();

      if(stt == 0) return;
      System.out.println(Color.YELLOW);
      System.out.print("So luong thay doi (lon hon 0): " + Color.RESET);
      int amount = input.nextInt();


      bill.UpdateDetails(stt - 1 , amount);

      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.print("\n~~> DA SUA THANH CONG !!! <~~" + Color.RESET);

      try {
        Thread.sleep(800);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }

  public static void MenuXoaSpKhoiBill(Scanner input, Bill bill) {
    while(true) {
      clearConsole();
      System.out.println(bill.InfoDetails());

      System.out.println(Color.YELLOW);
      System.out.println("Nhap 0 de quay lai");
      System.out.print("Nhap stt san pham muon xoa: " + Color.RESET);
      int stt = input.nextInt();
      input.nextLine();

      if(stt == 0) return;

      bill.DeleteDetails(stt - 1 );

      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.print("\n~~> DA XOA THANH CONG !!! <~~" + Color.RESET);

      try {
        Thread.sleep(800);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }
 

  //BAN HANGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG



  public static void MenuQuanLy(Scanner input) {
    while (true) {
      clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println("================= QUAN LY =================" + Color.RESET);
      System.out.println("===========================================");
      System.out.println("||  1. Quan ly san pham                  ||");
      System.out.println("||  2. Quan ly hoa don                   ||");
      System.out.println("||  3. Quan ly nhan su                   ||");
      System.out.println("||  0. Quay lai                          ||");
      System.out.println("===========================================");
      System.out.print("~~> Lua chon: ");

      String line = input.nextLine();
      switch (line) {
        case "1":
          MenuQuanLySanPham(input);
          break;

        case "2":
          MenuQuanLyHoaDon(input);
          break;

        case "3":
          MenuQuanLyNhanSu(input);
          break;

        case "0":
          return;

        default:
          continue;

      }
    }
  }



  public static void MenuQuanLySanPham(Scanner input) {
    while (true) {
      clearConsole();
      System.out.print(Color.BLUE_BACKGROUND);
      System.out.println("======== UNG DUNG QUAN LY BAN HANG ========" + Color.RESET);
      System.out.println("===========================================\n"
          + "||  1. Xuat danh sach san pham           ||\n"
          + "||  2. Them san pham                     ||\n"
          + "||  3. Sua san pham                      ||\n"
          + "||  4. Xoa san pham                      ||\n"
          + "||  5. Tim kiem san pham theo ten        ||\n"
          + "||  6. Tim kiem san pham theo loai       ||\n"
          + "||  0. Quay lai                          ||\n"
          + "===========================================");
      System.out.print("~~> Lua chon: ");
      String line = input.nextLine();
      switch (line) {
        case "1":
          clearConsole();
          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.println(Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN);
          System.out.print(Color.Line(100, '-') +
              "\nSTT\tMA SP\tTEN SP\tLOAI\tGIA\n" +
              Color.Line(100, '-'));

          ProductManagement.Instance().PrintList();

          System.out.println(Color.Line(100, '-'));
          System.out.print(Color.RESET);

          System.out.print("\nNhan phim bat ki de quay lai: ");
          input.nextLine();
          break;
        case "2":
          System.out.print(Color.GREEN);
          System.out.println("\n--------------- NHAP THONG TIN SAN PHAM --------------" + Color.RESET);
          String idProduct = "";
          int flag = 0;
          do {
              System.out.print(Color.YELLOW + "Ma san pham: " + Color.RESET);
              idProduct = input.nextLine();
              if(!ProductManagement.Instance().checkId(idProduct)){
                System.out.println("Ma san pham bi trung hay nhap lai !!!");
              }else{
                flag = 1;
              }
                
          }while(flag == 0);

          
          System.out.print(Color.YELLOW + "Ten san pham: " + Color.RESET);
          String name = input.nextLine();

          System.out.print(Color.YELLOW + "Loai san pham: " + Color.RESET);
          String category = input.nextLine();

          System.out.print(Color.YELLOW + "Gia: " + Color.RESET);
          String price = input.nextLine();

          Product product = new Product(idProduct,name, category, Integer.parseInt(price));
          ProductManagement.Instance().Add(product, ENV.pathProduct);

          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.print("\n~~> DA THEM THANH CONG !!! <~~" + Color.RESET);

          try {
            Thread.sleep(800);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          break;
        case "3":
          while (true) {
            clearConsole();
            System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
            System.out.println(Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN);
            System.out.print(Color.Line(100, '-') +
                "\nSTT\tMA SP\tTEN SP\tLOAI\tGIA\n" +
                Color.Line(100, '-'));
            ProductManagement.Instance().PrintList();
            System.out.println(Color.Line(100, '-'));
            System.out.print(Color.RESET);
            System.out.println("\nNhap 0 de quay lai");
            System.out.print("Nhap STT san pham can sua: ");
            int index = input.nextInt();
            input.nextLine();
            if (index == 0)
              return;
            Product _product = ProductManagement.Instance().GetList().get(index - 1);
            while (true) {
              clearConsole();
              System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
              System.out.println("======== THONG TIN SAN PHAM MUON SUA ========" + Color.RESET);
              System.out.println("============================================\n"
                  + "||" + Color.GREEN + "  1. Ten san pham: " + Color.YELLOW + _product.getName() + "\n" + Color.RESET
                  + "||" + Color.GREEN + "  2. Loai: " + Color.YELLOW + _product.getCategory() + "\n" + Color.RESET
                  + "||" + Color.GREEN + "  3. Gia: " + Color.YELLOW + _product.getPrice() + "\n" + Color.RESET
                  + "||" + Color.GREEN + "  0. Quay lai\n"
                  + Color.RESET + "============================================");
              System.out.print("~~> Lua chon muc de sua: ");
              String _line = input.nextLine();
              switch (_line) {
                case "1": {
                  System.out.print("Nhap lai ten san pham: ");
                  String nameProduct = input.nextLine();
                  _product.setName(nameProduct);
                  break;
                }
                case "2": {
                  System.out.print("Nhap lai loai san pham: ");
                  String categoryProduct = input.nextLine();
                  _product.setCategory(categoryProduct);
                  break;
                }
                case "3": {
                  System.out.print("Nhap lai gia san pham: ");
                  int priceProduct = input.nextInt();
                  _product.setPrice(priceProduct);
                  break;
                }
                case "0":
                  ProductManagement.Instance().Update(index - 1, _product, ENV.pathProduct);
                  return;
                default:
              }
            }
          }
        case "4":
          while (true) {
            clearConsole();
            System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
            System.out.println(Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN);
            System.out.print(Color.Line(100, '-') +
                "\nSTT\tMA SP\tTEN SP\tLOAI\tGIA\n" +
                Color.Line(100, '-'));

            ProductManagement.Instance().PrintList();

            System.out.println(Color.Line(100, '-'));
            System.out.print(Color.RESET);

            System.out.println("\nNhap 0 de quay lai");
            System.out.print("Nhap STT san pham can xoa: ");
            int id = input.nextInt();
            switch (id) {
              case 0: {
                return;
              }
              default: {
                ProductManagement.Instance().Delete(id - 1, ENV.pathProduct);
                System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
                System.out.print("\n~~> DA XOA THANH CONG !!! <~~" + Color.RESET);
                try {
                  Thread.sleep(800);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            }
          }
        case "5":
          while (true) {
            clearConsole();
            System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
            System.out.println(Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN);
            System.out.print(Color.Line(100, '-') +
                "\nSTT\tTEN SP\tLOAI\tGIA\n" +
                Color.Line(100, '-'));

            ProductManagement.Instance().PrintList();

            System.out.println(Color.Line(100, '-'));
            System.out.print(Color.RESET);

            System.out.print("\nNhap 0 de quay lai");
            System.out.print("\nTim kiem theo ten san pham: ");
            String nameProduct = input.nextLine();

            if (nameProduct.equals("0"))
              return;

            while (true) {
              clearConsole();
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
              nameProduct = input.nextLine();
              if (nameProduct.equals("0"))
                return;
            }
          }
        case "6":
          while (true) {
            clearConsole();
            System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
            System.out.println(Color.Line(41, '=') + " DANH SACH SAN PHAM " + Color.Line(41, '=') + Color.GREEN);
            System.out.print(Color.Line(100, '-') +
                "\nSTT\tMA SP\tTEN SP\tLOAI\tGIA\n" +
                Color.Line(100, '-'));

            ProductManagement.Instance().PrintList();

            System.out.println(Color.Line(100, '-'));
            System.out.print(Color.RESET);

            System.out.print("\nNhap 0 de quay lai");
            System.out.print("\nTim kiem theo loai san pham: ");
            String categoryProduct = input.nextLine();

            if (categoryProduct.equals("0"))
              return;

            while (true) {
              clearConsole();
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
              categoryProduct = input.nextLine();
              if (categoryProduct.equals("0"))
                return;
            }
          }
        case "0":
          return;
        default:
          System.out.println("Vui long chon lai !!!");
          continue;

      }
    }
  }



  public static void MenuQuanLyHoaDon(Scanner input) {
    while (true) {
      clearConsole();
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
      String line = input.nextLine();

      switch (line) {
        case "1":
          clearConsole();
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
          input.nextLine();
          break;
        case "2":
          MenuBanHang(input);
          break;

        case "3":
          MenuSuaBill(input);
          break;

        case "4":
          
          break;
        case "5":
          
          break;
        case "0":
          return;
        default:
          System.out.println("Vui long chon lai !!!");
          continue;

      }
    }
  }

  public static void MenuSuaBill(Scanner input) {
    while(true) {
          clearConsole();
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
          System.out.print("Stt hoa don can sua: ");
          int stt = input.nextInt();
          input.nextLine();

          if(stt == 0) return;
          clearConsole();
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
          String line = input.nextLine();
          switch(line) {
            case "1": 
              System.out.print("\nNhap lai ho ten khach hang: ");
              String customerName = input.nextLine();
              bill.setCustomerName(customerName);
            break;
            case "2": 
              System.out.print("\nNhap lai sdt khach hang: ");
              String customerPhone = input.nextLine();
              bill.setCustomerPhone(customerPhone);
            break;
            case "3": 
            MenuThemSPVaoBill(input, bill);
            break;
            case "4": 
            MenuSuaSLSpTrongBill(input, bill);
            break;
            case "5": 
            MenuXoaSpKhoiBill(input, bill);
            break;
            case "6":
            System.out.println("\nNhap lai ngay thanh toan: ");
            System.out.print("Ngay: ");
            int dayOfMonth = input.nextInt();
            System.out.print("Thang: ");
            int month = input.nextInt();
            System.out.print("Nam: ");
            int year = input.nextInt();
            input.nextLine();
            bill.setDate(LocalDate.of(year, month, dayOfMonth));
            break; 
            case "0": 
            return;
          }
    }
  }



  public static void MenuQuanLyNhanSu(Scanner input) {
    while (true) {
      clearConsole();
      System.out.print(Color.BLUE_BACKGROUND);
      System.out.println("======== UNG DUNG QUAN LY BAN HANG ========" + Color.RESET);
      System.out.println("===========================================\n"
          + "||  1. Xuat danh sach nhan su            ||\n"
          + "||  2. Them nhan su                      ||\n"
          + "||  3. Sua nhan su                       ||\n"
          + "||  4. Xoa nhan su                       ||\n"
          + "||  5. Tim kiem nhan su                  ||\n"
          + "||  0. Quay lai                          ||\n"
          + "===========================================");
      System.out.print("~~> Lua chon: ");
      String line = input.nextLine();

      switch (line) {
        case "1":
          clearConsole();
          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.println(Color.Line(41, '=') + " DANH SACH NHAN SU " + Color.Line(41, '=') + Color.GREEN);
          System.out.print(Color.Line(100, '-') +
              "\nSTT\tTK  \t HO VA TEN\t   TUOI\t   GT\t  SODIENTHOAI\t  LUONG \tLUONG+\t   CHUC VU\n" +
              Color.Line(100, '-'));

          PersonManagement.Instance().PrintList();

          System.out.println(Color.Line(100, '-'));
          System.out.print(Color.RESET);

          System.out.print("\nNhan phim bat ki de quay lai: ");
          input.nextLine();
          break;
        case "2":
          MenuThemNhanSu(input);
          break;
        case "3":
          MenuSuaNhanSu(input);
          break;
        case "4":
          MenuXoaNhanSu(input);
          break;
        case "5":
          MenuTimKiemNhanSu(input);
          break;
        case "0":
          return;
        default:
          System.out.println("Vui long chon lai !!!");
          continue;

      }
    }
  }

  public static void MenuThemNhanSu(Scanner input) {
    while (true) {
      clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println("=============== THEM NHAN SU ==============" + Color.RESET);
      System.out.println("===========================================\n"
          + "||  1. Them mot nhan vien                ||\n"
          + "||  2. Them mot quan ly                  ||\n"
          + "||  0. Quay lai.                         ||\n"
          + "===========================================");
      System.out.print("~~> Lua chon: ");
      String line = input.nextLine();
      switch (line) {
        case "1": {
          System.out.print(Color.GREEN);
          System.out.println("\n--------------- NHAP THONG TIN NHAN VIEN --------------" + Color.RESET);
          System.out.print(Color.YELLOW + "Tai Khoan: " + Color.RESET);
          String taiKhoan = input.nextLine();

          System.out.print(Color.YELLOW + "Mat khau: " + Color.RESET);
          String matKhau = input.nextLine();

          System.out.print(Color.YELLOW + "Ho ten: " + Color.RESET);
          String hoTen = input.nextLine();

          System.out.print(Color.YELLOW + "Tuoi: " + Color.RESET);
          int tuoi = input.nextInt();
          input.nextLine();

          System.out.print(Color.YELLOW + "Gioi tinh (Nam/Nu): " + Color.RESET);
          String gioiTinh = input.nextLine();

          System.out.print(Color.YELLOW + "So dien thoai: " + Color.RESET);
          String soDienThoai = input.nextLine();

          System.out.print(Color.YELLOW + "Luong co ban: " + Color.RESET);
          double salary = input.nextDouble();
          input.nextLine();

          Person nhanvien = new Staff(taiKhoan, matKhau, hoTen, tuoi, gioiTinh, soDienThoai, salary);
          PersonManagement.Instance().Add(nhanvien, ENV.pathPerson);

          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.print("\n~~> DA THEM THANH CONG !!! <~~" + Color.RESET);

          try {
            Thread.sleep(800);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          break;
        }

        case "2": {
          System.out.print(Color.GREEN);
          System.out.println("\n--------------- NHAP THONG TIN QUAN LY --------------" + Color.RESET);
          System.out.print(Color.YELLOW + "Tai Khoan: " + Color.RESET);
          String taiKhoan = input.nextLine();

          System.out.print(Color.YELLOW + "Mat khau: " + Color.RESET);
          String matKhau = input.nextLine();

          System.out.print(Color.YELLOW + "Ho ten: " + Color.RESET);
          String hoTen = input.nextLine();

          System.out.print(Color.YELLOW + "Tuoi: " + Color.RESET);
          int tuoi = input.nextInt();
          input.nextLine();

          System.out.print(Color.YELLOW + "Gioi tinh (Nam/Nu): " + Color.RESET);
          String gioiTinh = input.nextLine();

          System.out.print(Color.YELLOW + "So dien thoai: " + Color.RESET);
          String soDienThoai = input.nextLine();

          System.out.print(Color.YELLOW + "Luong co ban: " + Color.RESET);
          double salary = input.nextDouble();
          input.nextLine();

          System.out.print(Color.YELLOW + "Luong thuong: " + Color.RESET);
          double salaryBonus = input.nextDouble();
          input.nextLine();

          Person quanly = new Manager(taiKhoan, matKhau, hoTen, tuoi, gioiTinh, soDienThoai, salary, salaryBonus);
          PersonManagement.Instance().Add(quanly, ENV.pathPerson);

          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.print("\n~~> DA THEM THANH CONG !!! <~~" + Color.RESET);

          try {
            Thread.sleep(800);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          break;
        }
        case "0":
          return;

        default:
          continue;

      }
    }
  }

  public static void MenuXoaNhanSu(Scanner input) {
    while (true) {
      clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(41, '=') + " DANH SACH NHAN SU " + Color.Line(41, '=') + Color.GREEN);
      System.out.print(Color.Line(100, '-') +
          "\nSTT\tTK  \t HO VA TEN\t   TUOI\t   GT\t  SODIENTHOAI\t  LUONG \tLUONG+\t   CHUC VU\n" +
          Color.Line(100, '-'));

      PersonManagement.Instance().PrintList();

      System.out.println(Color.Line(100, '-'));
      System.out.print(Color.RESET);

      System.out.println("\nNhap 0 de quay lai");
      System.out.print("Nhan id nhan su can xoa: ");
      int id = input.nextInt();
      switch (id) {
        case 0: {

          return;
        }
        default: {
          PersonManagement.Instance().Delete(id - 1, ENV.pathPerson);

          System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
          System.out.print("\n~~> DA XOA THANH CONG !!! <~~" + Color.RESET);

          try {
            Thread.sleep(800);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

        }
      }
    }

  }

  public static void MenuSuaNhanSu(Scanner input) {
    while (true) {
      clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(41, '=') + " DANH SACH NHAN SU " + Color.Line(41, '=') + Color.GREEN);
      System.out.print(Color.Line(100, '-') +
          "\nSTT\tTK  \t HO VA TEN\t   TUOI\t   GT\t  SODIENTHOAI\t  LUONG \tLUONG+\t   CHUC VU\n" +
          Color.Line(100, '-'));

      PersonManagement.Instance().PrintList();

      System.out.println(Color.Line(100, '-'));
      System.out.print(Color.RESET);

      System.out.println("\nNhap 0 de quay lai");
      System.out.print("Nhan id nhan su can sua: ");
      int id = input.nextInt();
      input.nextLine();

      if (id == 0)
        return;

      Person person = PersonManagement.Instance().GetList().get(id - 1);
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
        clearConsole();
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
        String line = input.nextLine();

        switch (line) {
          case "1": {
            System.out.print("Nhap lai ho va ten: ");
            String fullName = input.nextLine();
            person.setFullName(fullName);
            break;
          }
          case "2": {
            System.out.print("Nhap lai tuoi: ");
            int age = input.nextInt();
            person.setAge(age);
            break;
          }
          case "3": {
            System.out.print("Nhap lai gioi tinh (Nam/Nu): ");
            String gender = input.nextLine();
            person.setGender(gender);
            break;
          }
          case "4": {
            System.out.print("Nhap lai so dien thoai: ");
            String phone = input.nextLine();
            person.setPhone(phone);
            break;
          }
          case "5": {
            System.out.print("Nhap lai luong co ban: ");
            salary = input.nextDouble();
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
              input.nextLine();
              break;
            }

            System.out.print("Nhap lai luong thuong: ");
            salaryBonus = input.nextDouble();
            Manager quanLy = (Manager) person;
            quanLy.setSalaryBonus(salaryBonus);

            break;
          }
          case "0":
            PersonManagement.Instance().Update(id - 1, person, ENV.pathPerson);
            return;

          default:
        }
      }
    }

  }

  public static void MenuTimKiemNhanSu(Scanner input) {
    while (true) {
      clearConsole();
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(41, '=') + " DANH SACH NHAN SU " + Color.Line(41, '=') + Color.GREEN);
      System.out.print(Color.Line(100, '-') +
          "\nSTT\tTK  \t HO VA TEN\t   TUOI\t   GT\t  SODIENTHOAI\t  LUONG \tLUONG+\t   CHUC VU\n" +
          Color.Line(100, '-'));

      PersonManagement.Instance().PrintList();

      System.out.println(Color.Line(100, '-'));
      System.out.print(Color.RESET);

      System.out.print("\nNhap 0 de quay lai");
      System.out.print("\nTim kiem theo ho ten nhan su: ");
      String hoTen = input.nextLine();

      if (hoTen.equals("0"))
        return;

      while (true) {
        clearConsole();
        System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
        System.out.println(Color.Line(41, '=') + " DANH SACH NHAN SU " + Color.Line(41, '=') + Color.GREEN);
        System.out.print(Color.Line(100, '-') +
            "\nSTT\tTK  \t HO VA TEN\t   TUOI\t   GT\t  SODIENTHOAI\t  LUONG \tLUONG+\t   CHUC VU\n" +
            Color.Line(100, '-'));

        List<Person> personList = PersonManagement.Instance().SearchByName(hoTen);
        int soLuong = personList.size();
        System.out.println(Color.YELLOW);
        if (soLuong == 0) {
          System.out.println("\n\t\t\t\t\tKHONG CO KET QUA NAO TRUNG KHOP");
        } else
          for (int i = 1; i <= soLuong; i++) {
            System.out.println("[" + i + "]\t" + personList.get(i - 1).Info());
          }
        System.out.println(Color.RESET);

        System.out.println(Color.Line(100, '-'));
        System.out.print(Color.RESET);

        System.out.print("\nNhap 0 de quay lai");
        System.out.print("\nTim kiem theo ho ten nhan su: ");
        hoTen = input.nextLine();

        if (hoTen.equals("0"))
          return;

      }
    }
  }




  public static void clearConsole() {
    // System.out.println("\033[H\033[2J");
    // System.out.flush();
    try {
      if (System.getProperty("os.name").contains("Windows"))
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      else
        Runtime.getRuntime().exec("clear");
    } catch (IOException | InterruptedException ex) {
    }

  }




  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);
    Person person = PersonManagement.Instance().CheckLogin();
    boolean dangXuat = false;
    // Neu ko dang nhat dc
    // Person manager = new Manager("admin", "admin", "abc", 12, "nam", "09", 12, 12);
    // Menu(input, manager);

    do {
      if (person == null) person = MenuDangNhap(input);

      if (person == null) {
        input.close();
        return;
      }

      if (person != null) dangXuat = Menu(input, person);
      person = null;

    } while (dangXuat);

   

  }
}
