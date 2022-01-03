package DOANCUOIKI;
import java.util.List;
public abstract class TableTemplate<T extends Entity>{
  public abstract void showHeader(String nameTable, String column);
  public abstract void showBody(List<T> list);
  public abstract void showFooter();
  public abstract void showTable(String nameTable,String column,List<T> data);
}




