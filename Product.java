package DOANCUOIKI;

import java.io.Serializable;

public class Product extends Entity implements Serializable {
  private String id;
  public String name;
  public String category;
  public double price;

  public Product(String id, String name, String category, double price) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.price = price;
  }
  
public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
 
  public String getCategory() {
    return category;
  }
  
  public void setCategory(String category) {
    this.category = category;
  }
  
  public double getPrice() {
    return price;
  }
  
  public void setPrice(double price) {
    this.price = price;
  }
  
  @Override
  public String Info() {
    return
      id+"\t"
    + name + "\t"
    + category + "\t"
    + price;
  }
}
