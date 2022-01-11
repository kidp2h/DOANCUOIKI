package DOANCUOIKI.util;

import java.util.List;
import java.util.Scanner;

import DOANCUOIKI.Entity;


public class ConsoleProgram {
    public static final Scanner INPUT = new Scanner(System.in);

    public static void clearConsole() {
        try {
          if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
          else
            Runtime.getRuntime().exec("clear");
        } catch (Exception e) {}
    }

    public static void printMessageNoti(String mess) {
      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);

      switch(mess) {
        case "add": 
          System.out.print("\n~~> DA THEM THANH CONG !!! <~~" + Color.RESET);
        break;

        case "update": 
          System.out.print("\n~~> DA SUA THANH CONG !!! <~~" + Color.RESET);
        break;

        case "delete": 
          System.out.print("\n~~> DA XOA THANH CONG !!! <~~" + Color.RESET);
        break;
      }

      try {
        Thread.sleep(800);
      } catch (Exception e) {}
    }

    public static  <T extends Entity> void printTable(List<T> list, String nameTable, String column) {

      System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
      System.out.println(Color.Line(42, '=') + " DANH SACH "+nameTable+" " + Color.Line(42, '=') + Color.GREEN);
      System.out.println(Color.Line(102, '-')+ column +Color.Line(102, '-'));

      int listSize = list.size();

      System.out.print(Color.YELLOW);
      if(listSize == 0 ) {
        System.out.println("\n\t\t\t\t\tDANH SACH DANG TRONG");
      }
      else {
        for (int i = 1; i <= listSize; i++) 
          System.out.println("[" + i + "]\t" + list.get(i - 1).Info());
      }
      System.out.println(Color.GREEN);
      System.out.println(Color.Line(102, '-'));
      System.out.print(Color.RESET);
      
    }
}
