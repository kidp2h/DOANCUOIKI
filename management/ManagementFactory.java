package DOANCUOIKI.management;

public class ManagementFactory {
  private ManagementFactory(){

  }
  public static final <T> IManagement<T> produceManagement(ManagementType type){
    switch (type){
      case PERSON:
        return (IManagement<T>) PersonManagement.Instance();
      case PRODUCT:
        return (IManagement<T>) ProductManagement.Instance();
      default:
        return null;
    }
  }
}