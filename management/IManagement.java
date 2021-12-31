package DOANCUOIKI.management;

import java.util.List;

public interface IManagement<T> {
    public void LoadFile(String path);  
    List<T> GetList();
    void PrintList();
    void Add(T obj, String path);
    void Update(int id, T obj, String path);
    void Delete(int id, String path);
    List<T> SearchByName(String name);
}
