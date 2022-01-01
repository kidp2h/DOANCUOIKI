package DOANCUOIKI;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import DOANCUOIKI.util.Color;

public class Bill extends Entity implements Serializable {

    private String customerName;
    private String customerPhone;
    private LocalDate date;
    private List<BillDetails> list = new ArrayList<>();
    private int listSize = 0;

    public Bill(String customerName, String customerPhone) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

    public boolean AddDetails(BillDetails details) {

        boolean isExist = false;
        for(int i =0; i<list.size();i++) {
            if(list.get(i).getProduct().getId() == details.getProduct().getId())
            {
                isExist = true;
                int amount = list.get(i).getAmount();
                list.get(i).setAmount(amount + details.getAmount());
            }
        }
        if(!isExist) {
            list.add(details);
            listSize++;
        }

        return true;  
    }

    public boolean UpdateDetails(int stt, int amount) {
        if(stt >= listSize) return false;
        list.get(stt).setAmount(amount);
        return true;
    }

    public boolean DeleteDetails(int stt) {
        if(stt >= listSize) return false;
        list.remove(stt);
        listSize--;
        return true;
    }

    public int SumPrice() {
        int sum = 0;
        for(int i =0; i<list.size(); i++) {
            sum += list.get(i).Sum();
        }
        return sum;
    }

    public List<BillDetails> getList() {
        return this.list;
    }

    public void setList(List<BillDetails> list) {
        this.list = list;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return this.customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String Info() {
        return 
        customerName + "\t"
        + customerPhone + "\t"
        + SumPrice() + "\t\t"
        + date.toString() ;
    }

    public String InfoDetails() {

        String infoDetails =     
            Color.YELLOW +"#Khach hang: " + Color.RESET + customerName
        +   Color.YELLOW +"\n#So dien thoai: " + Color.RESET + customerPhone;

        if(date!= null) {
            infoDetails +=   Color.YELLOW +"\n#Ngay thanh toan: " + Color.RESET + date.toString();
        }

        infoDetails +=   Color.YELLOW +"\n\t------------- HOA DON -------------"
        +       "\n=================================================="
        +               "\nSTT\tTENSP\t\tLOAI\tGIABAN\t\tSOLUONG" 
        +       "\n--------------------------------------------------" + Color.RESET;
        
        for(int i = 0; i < list.size(); i++) {
            infoDetails += "\n[" + (i + 1) + "]\t" + list.get(i).Info();
        }
        
        infoDetails += Color.YELLOW +"\n==================================================" 
        + Color.RESET + "\nTONG TIEN: " + this.SumPrice();

        return infoDetails;
    }


}
