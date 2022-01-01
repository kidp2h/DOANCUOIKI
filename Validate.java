package DOANCUOIKI;

import java.util.Scanner;

import DOANCUOIKI.management.ProductManagement;
import DOANCUOIKI.util.Color;
import DOANCUOIKI.util.ConsoleProgram;

public class Validate {

    public static String CheckEmpty(String mess) {

        String value;
        boolean isError = false;

        do {
            System.out.print(mess);
            value = ConsoleProgram.INPUT.nextLine();
            if(value.trim().equals("")) isError = true;
            else isError = false;

            if(isError) System.out.println("Gia tri khong duoc de trong !!!");
            
        }while(isError);

        return value.trim();
    }

    public static int CheckNumberInt(String mess) {

        String value;
        boolean isError = false;

        do {
            value = CheckEmpty(mess);

            try {
                Integer.parseInt(value);
                isError = false;
            } catch(Exception e) {
                isError = true;
            }

            if(isError) System.out.println("Gia tri nay phai la so !!!");
            
        }while(isError);

        return Integer.parseInt(value);
    }

    public static double CheckNumberDouble(String mess) {

        String value;
        boolean isError = false;

        do {
            value = CheckEmpty(mess);
            try {
                Double.parseDouble(value);
                isError = false;
            } catch(Exception e) {
                isError = true;
            }

            if(isError) System.out.println("Gia tri nay phai la so !!!");
            
        }while(isError);

        return Double.parseDouble(value);
    }

    public static String CheckIDProduct() {
        boolean isExist = false;
        String idProduct;
        do {

            idProduct = CheckEmpty("Ma san pham: ");
            
            if(!ProductManagement.Instance().checkId(idProduct)){
                System.out.println("Ma san pham bi trung hay nhap lai !!!");
                isExist = true;
            } else {
                isExist = false;
            }
            
        }while(isExist);

        return idProduct;
    }

}
