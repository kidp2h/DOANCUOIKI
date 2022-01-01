package DOANCUOIKI;

import java.util.List;

import DOANCUOIKI.util.Color;

public class PersonTable<T extends Person> extends TableTemplate<T> {

  private static PersonTable instance;

  public static PersonTable Instance() {
    if (instance == null) {
      instance = new PersonTable();
    }
    return instance;
  }

  @Override
  protected void showHeader() {
    System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
    System.out.println(Color.Line(41, '=') + " DANH SACH NHAN SU " + Color.Line(41, '=') + Color.GREEN);
    System.out.print(Color.Line(100, '-') +
      "\nSTT\tTK  \t HO VA TEN\t   TUOI\t   GT\t  SODIENTHOAI\t  LUONG \tLUONG+\t   CHUC VU\n" +
      Color.Line(100, '-'));
  }

  @Override
  protected void showBody(List<T> list) {
    System.out.println(Color.YELLOW);
    if (list.size() == 0) {
      System.out.println("\n\t\t\t\t\tKHONG CO KET QUA NAO TRUNG KHOP");
    } else
      for (int i = 1; i <= list.size(); i++) {
        System.out.println("[" + i + "]\t" + list.get(i - 1).Info());
      }
    System.out.println(Color.RESET);
  }

  @Override
  protected void showFooter() {
    System.out.println(Color.Line(100, '-'));
    System.out.print(Color.RESET);
  }
  
  @Override
  protected void showTable(List<T> list) {
    showHeader();
    showBody(list);
    showFooter();
  }
  
}
