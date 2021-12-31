package DOANCUOIKI.management;

import java.util.ArrayList;
import java.util.List;

import DOANCUOIKI.Bill;
import DOANCUOIKI.ENV;

public class BillManagement extends Management<Bill> implements IManagement<Bill>{

    private static BillManagement instance;

    public static BillManagement Instance() {
        if (instance == null) instance = new BillManagement();
        return instance;
    }

    private BillManagement(){
        list = new ArrayList<>();
        LoadFile(ENV.pathBill);
    }

    @Override
    public List<Bill> SearchByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
