package DOANCUOIKI.management;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import DOANCUOIKI.ENV;
import DOANCUOIKI.Product;
@SuppressWarnings("all")
public class ProductManagement extends Management<Product> {
  private static ProductManagement instance;

  public static ProductManagement Instance() {
    if (instance == null) instance = new ProductManagement();
    return instance;
  }

  private ProductManagement(){
    list = new ArrayList<>();
    LoadFile(ENV.pathProduct);
  }

  public List<Product> SearchByName(String name) {
    return list.stream()
    .filter(product -> product.getName().toUpperCase().contains(name.toUpperCase()))
    .collect(Collectors.toList());
  }
  
  public List<Product> SearchByCategory(String category){
    return list.stream()
    .filter(product -> product.getCategory().toUpperCase().contains(category.toUpperCase()))
    .collect(Collectors.toList());
  }

  public List<Product> SearchByPrice(double from, double to){
    return list.stream()
    .filter(product -> product.getPrice() >= from && product.getPrice() <= to)
    .collect(Collectors.toList());
  }
  
  public boolean checkId(String id){
    List<Product> listProduct = list.stream()
    .filter(product -> product.getId().trim().toUpperCase().equals(id.trim().toUpperCase()))
    .collect(Collectors.toList());
    return listProduct.size() == 0;
  }
}
