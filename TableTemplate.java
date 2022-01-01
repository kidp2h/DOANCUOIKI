package DOANCUOIKI;

import java.util.List;

import DOANCUOIKI.management.Management;

public abstract class TableTemplate<T extends Entity>{
  protected abstract void showHeader(String nameTable, String column);
  protected abstract void showBody(List<T> list);
  protected abstract void showFooter();
  protected abstract void showTable(String nameTable,String column,List<T> data);
}
