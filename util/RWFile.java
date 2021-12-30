package DOANCUOIKI.util;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RWFile {

    public static Object readObject (String path) throws Exception {

        try (
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            Object obj = ois.readObject();
            return obj;
        }
      
    }
    
    public static void writeObject (String path, Object obj) throws Exception {
        
        try (
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(obj);
        }
    }
}
