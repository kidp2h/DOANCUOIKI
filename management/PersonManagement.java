package DOANCUOIKI.management;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import DOANCUOIKI.Person;
import DOANCUOIKI.util.RWFile;
import DOANCUOIKI.ENV;

public class PersonManagement extends Management<Person> implements IManagement<Person> {

  private static PersonManagement instance;

  public static PersonManagement Instance() {
    if (instance == null) {
      instance = new PersonManagement();
    }
    return instance;
  }

  private PersonManagement() {
    list = new ArrayList<>();
    LoadFile(ENV.pathPerson);
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
      File file = new File(ENV.pathLogin);
      try {
        if (!file.exists())
          file.createNewFile();
        RWFile.writeObject(ENV.pathLogin, listTemp.get(0));
      } catch (Exception e) {
      }

      return listTemp.get(0);
    }
  }

  public Person CheckLogin() {
    File file = new File(ENV.pathLogin);
    try {
      if (file.exists()) {
        return (Person) RWFile.readObject(ENV.pathLogin);
      } else {
        return null;
      }
    } catch (Exception e) {
    }

    return null;
  }
}
