package DOANCUOIKI;

import java.util.List;

public class DataTable<T extends Entity>{
  public void show(List<T> data){
    for (int i = 1; i <= data.size(); i++) {
      System.out.println("[" + i + "]\t" + data.get(i - 1).Info());
    }
  }
}
