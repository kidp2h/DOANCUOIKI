package DOANCUOIKI;

import java.util.List;

interface ITable<T extends Entity> {
  void showHeader(String nameTable, String column);
  void showBody(List<T> list);
  void showFooter();
  void showTable(String nameTable,String column,List<T> data);
}
