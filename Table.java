package DOANCUOIKI;

import java.util.List;

import DOANCUOIKI.management.Management;
import DOANCUOIKI.util.Color;

public class Table<T extends Entity> extends TableTemplate<T>{

  public Management<T> management =  Management.Instance();
  private static Table instance;

  public static Table Instance() {
    if (instance == null) {
      instance = new Table();
    }
    return instance;
  }

  @Override
  protected void showHeader(String nameTable, String column) {
    System.out.print(Color.BLACK + Color.GREEN_BACKGROUND);
    System.out.println(Color.Line(41, '=') + " DANH SACH "+nameTable+" " + Color.Line(41, '=') + Color.GREEN);
    System.out.println(Color.Line(100, '-')+column+Color.Line(100, '-'));
  }

  @Override
  protected void showBody(List<T> data) {
    management.PrintList(data);
  }

  @Override
  protected void showFooter() {
    System.out.println(Color.Line(100, '-'));
    System.out.print(Color.RESET);
  }
  
  @Override
  protected void showTable(String nameTable,String column,List<T> data) {
    showHeader(nameTable,column);
    showBody(data);
    showFooter();
  }
  
}
