package DOANCUOIKI;

import java.util.List;

public abstract class TableTemplate<T extends Entity>{
  protected abstract void showHeader();
  protected abstract void showBody(List<T> list);
  protected abstract void showFooter();
  protected abstract void showTable(List<T> list);
}
