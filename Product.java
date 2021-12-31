package DOANCUOIKI;

import java.io.Serializable;

public class Product extends Entity implements Serializable {
  private int id;
  public String name;
  public String category;
  public int price;

  public Product(String name, String category, int price) {
    this.id = 5;
    this.name = name;
    this.category = category;
    this.price = price;
}

  public int getId() {
    return id;
  }
  public void setId(int id) {
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
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  @Override
  public String Info() {
    return  
    name + "\t"
    + category + "\t"
    + price;
  }
}
