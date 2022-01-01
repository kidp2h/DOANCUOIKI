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
}
