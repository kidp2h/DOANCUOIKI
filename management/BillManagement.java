package DOANCUOIKI.management;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import DOANCUOIKI.Bill;
import DOANCUOIKI.ENV;
@SuppressWarnings("all")
public class BillManagement extends Management<Bill>{

  private static BillManagement instance;

  public static BillManagement Instance() {
    if (instance == null)
      instance = new BillManagement();
    return instance;
  }

  private BillManagement() {
    list = new ArrayList<>();
    LoadFile(ENV.pathBill);
  }


  public List<Bill> SearchByCustomerName(String customerName) {
    return list.stream()
    .filter(bill -> bill.getCustomerName().toUpperCase().contains(customerName.toUpperCase()))
    .collect(Collectors.toList());
  }

  public List<Bill> SearchByCustomerPhone(String customerPhone) {
    return list.stream()
    .filter(bill -> bill.getCustomerPhone().contains(customerPhone))
    .collect(Collectors.toList());
  }


  public List<Bill> getBillByRange(LocalDate from, LocalDate to) {
    return list.stream()
      .filter(bill -> bill.getDate().isAfter(from) && bill.getDate().isBefore(to))
      .collect(Collectors.toList());
  }

  public List<Bill> getBillToday() {
    LocalDate now = LocalDate.now();
    return list.stream()
      .filter(bill -> bill.getDate().isEqual(now))
      .collect(Collectors.toList());
  }
  

}
