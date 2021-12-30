package DOANCUOIKI.management;

import java.util.List;

public interface IManagement<T> {
    void LoadFile();
    List<T> GetList();
    void PrintList();
    void Add(T obj);
    void Update(int id, T obj);
    void Delete(int id);
    List<T> SearchByName(String name);
}
