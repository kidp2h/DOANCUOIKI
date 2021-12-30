package DOANCUOIKI.quanly;

import java.io.*;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import DOANCUOIKI.NhanSu;
import DOANCUOIKI.util.Color;
import DOANCUOIKI.util.RWFile;

public class QuanLyNhanSu implements IQuanLy<NhanSu> {

  private List<NhanSu> list;

  private String path = new File("DOANCUOIKI/data/NhanSu.data").getAbsolutePath();
  private String pathLogin = new File("DOANCUOIKI/data/NhanSu.data").getAbsolutePath();
  private int listSize = 0;

  private static QuanLyNhanSu instance;

  public static QuanLyNhanSu Instance() {
    if (instance == null) {
      instance = new QuanLyNhanSu();
    }
    return instance;
  }

  private QuanLyNhanSu() {
    list = new ArrayList<>();
    LoadFile();
  }

  @Override
  public void LoadFile() {
    File file = new File(path);
    try {
      if (file.exists()) {
        list = (ArrayList<NhanSu>) RWFile.readObject(path);
        listSize = list.size();
      } else {
        file.createNewFile();
      }
    } catch (Exception e) {
    }
  }

  @Override
  public List<NhanSu> LayList() {
    return list;
  }

  @Override
  public void XuatDanhSach() {
    System.out.println(Color.YELLOW);
    if (listSize == 0) {
      System.out.println("\n\t\t\t\t\tDANH SACH DANG TRONG");
      System.out.println(Color.RESET);
      return;
    }

    for (int i = 1; i <= listSize; i++) {
      System.out.println("[" + i + "]\t" + list.get(i - 1).ThongTin());
    }
    System.out.println(Color.RESET);
  }

  @Override
  public void Them(NhanSu obj) {
    list.add(obj);
    listSize++;

    try {
      RWFile.writeObject(path, list);
    } catch (Exception e) {
    }
  }

  @Override
  public void Sua(int id, NhanSu obj) {
    list.set(id, obj);
    try {
      RWFile.writeObject(path, list);
    } catch (Exception e) {
    }
  }

  @Override
  public void Xoa(int id) {
    list.remove(id);
    listSize--;
    try {
      RWFile.writeObject(path, list);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<NhanSu> TimKiem(String name) {
    return list.stream()
        .filter(nhansu -> nhansu.getHoTen().toUpperCase().contains(name.toUpperCase()))
        .collect(Collectors.toList());
  }

  public NhanSu KiemTraTaiKhoan(String taiKhoan, String matKhau) {
    List<NhanSu> listTemp = list.stream()
        .filter(nhansu -> nhansu.getTaiKhoan().equals(taiKhoan) && nhansu.getMatKhau().equals(matKhau))
        .collect(Collectors.toList());

    if (listTemp.size() == 0) {
      return null;
    } else {
      File file = new File(pathLogin);
      try {
        if (!file.exists())
          file.createNewFile();
        RWFile.writeObject(pathLogin, listTemp.get(0));
      } catch (Exception e) {
      }

      return listTemp.get(0);
    }
  }

  public NhanSu KiemTraNhanSuDaDangNhap() {
    File file = new File(pathLogin);
    try {
      if (file.exists()) {
        return (NhanSu) RWFile.readObject(pathLogin);
      } else {
        return null;
      }
    } catch (Exception e) {
    }

    return null;
  }

  // public int IdLonNhat() {
  // list.stream().map(nhansu -> nhansu.getId())
  // }

}
