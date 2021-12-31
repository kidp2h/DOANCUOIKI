package DOANCUOIKI.management;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import DOANCUOIKI.ENV;
import DOANCUOIKI.Product;
import DOANCUOIKI.util.RWFile;
// extends AbstractManagement<Product>
// implements IManagement<Product>
public class ProductManagement extends Management<Product> implements IManagement<Product> {
  private static ProductManagement instance;

  public static ProductManagement Instance() {
    if (instance == null) instance = new ProductManagement();
    return instance;
  }

  private ProductManagement(){
    list = new ArrayList<>();
    LoadFile(ENV.pathProduct);
  }

  @Override
  public List<Product> SearchByName(String name) {
    return list.stream()
    .filter(product -> product.getName().toUpperCase().contains(name.toUpperCase()))
    .collect(Collectors.toList());
  }
}
