package DOANCUOIKI.management;

import java.util.List;

public interface IManagement<T> {
    void LoadFile(String path);  
    List<T> GetList();
    void PrintList();
    boolean Add(T obj, String path);
    boolean Update(int id, T obj, String path);
    boolean Delete(int id, String path);
}
