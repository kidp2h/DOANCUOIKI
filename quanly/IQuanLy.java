package DOANCUOIKI.quanly;

import java.util.List;

public interface IQuanLy<T> {
    void LoadFile();
    List<T> LayList();
    void XuatDanhSach();
    void Them(T obj);
    void Sua(int id, T obj);
    void Xoa(int id);
    List<T> TimKiem(String name);
}
