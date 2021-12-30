package DOANCUOIKI.management;

import java.io.*;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import DOANCUOIKI.Person;
import DOANCUOIKI.util.Color;
import DOANCUOIKI.util.RWFile;

public class PersonManagement implements IManagement<Person> {

  private List<Person> list;

  private String path = new File("DOANCUOIKI/data/Person.data").getAbsolutePath();
  private String pathLogin = new File("DOANCUOIKI/data/Login.data").getAbsolutePath();
  private int listSize = 0;

  private static PersonManagement instance;

  public static PersonManagement Instance() {
    if (instance == null) {
      instance = new PersonManagement();
    }
    return instance;
  }

  private PersonManagement() {
    list = new ArrayList<>();
    LoadFile();
  }

  @Override
  public void LoadFile() {
    File file = new File(path);
    try {
      if (file.exists()) {
        list = (ArrayList<Person>) RWFile.readObject(path);
        listSize = list.size();
      } else {
        file.createNewFile();
      }
    } catch (Exception e) {
    }
  }

  @Override
  public List<Person> GetList() {
    return list;
  }

  @Override
  public void PrintList() {
    System.out.println(Color.YELLOW);
    if (listSize == 0) {
      System.out.println("\n\t\t\t\t\tDANH SACH DANG TRONG");
      System.out.println(Color.RESET);
      return;
    }

    for (int i = 1; i <= listSize; i++) {
      System.out.println("[" + i + "]\t" + list.get(i - 1).Info());
    }
    System.out.println(Color.RESET);
  }

  @Override
  public void Add(Person obj) {
    list.add(obj);
    listSize++;

    try {
      RWFile.writeObject(path, list);
    } catch (Exception e) {
    }
  }

  @Override
  public void Update(int id, Person obj) {
    list.set(id, obj);
    try {
      RWFile.writeObject(path, list);
    } catch (Exception e) {
    }
  }

  @Override
  public void Delete(int id) {
    list.remove(id);
    listSize--;
    try {
      RWFile.writeObject(path, list);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Person> SearchByName(String name) {
    return list.stream()
        .filter(nhansu -> nhansu.getFullName().toUpperCase().contains(name.toUpperCase()))
        .collect(Collectors.toList());
  }

  public Person CheckAccount(String taiKhoan, String matKhau) {
    List<Person> listTemp = list.stream()
        .filter(nhansu -> nhansu.getUsername().equals(taiKhoan) && nhansu.getPassword().equals(matKhau))
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

  public Person CheckLogin() {
    File file = new File(pathLogin);
    try {
      if (file.exists()) {
        return (Person) RWFile.readObject(pathLogin);
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
