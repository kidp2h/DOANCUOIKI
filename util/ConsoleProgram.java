package DOANCUOIKI.util;

import java.util.Scanner;

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
}
