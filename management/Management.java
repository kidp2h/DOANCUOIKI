package DOANCUOIKI.management;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import DOANCUOIKI.Entity;
import DOANCUOIKI.util.Color;
import DOANCUOIKI.util.RWFile;

public class Management<T extends Entity> {
  protected List<T> list;
  protected int listSize = 0;
  

  public List<T> GetList() {
    return list;
  }
  public void LoadFile(String path) {
    File file = new File(path);
    try {
      if (file.exists()) {
        list = (ArrayList<T>) RWFile.readObject(path);
        listSize = list.size();
      } else {
        file.createNewFile();
      }
    } catch (Exception e) {
    }
  }
  public void Delete(int id,String path) {
    list.remove(id);
    listSize--;
    try {
      RWFile.writeObject(path, list);
    } catch (Exception e) {
    }
  }
  public void Update(int id, T obj, String path) {
    list.set(id, obj);
    try {
      RWFile.writeObject(path, list);
    } catch (Exception e) {
      
    }
  }
  public void Add(T obj, String path) {
    list.add(obj);
    listSize++;

    try {
      RWFile.writeObject(path, list);
    } catch (Exception e) {
    }
  }
  public void PrintList() {
    System.out.println(Color.YELLOW);
    if (listSize == 0) {
      System.out.println("\n\t\t\t\t\tDANH SACH DANG TRONG");
      System.out.println(Color.RESET);
      return;
    }
    for (int i = 1; i <= listSize; i++)
      System.out.println("[" + i + "]\t" + list.get(i - 1).Info());
    System.out.println(Color.RESET);
  }
}
