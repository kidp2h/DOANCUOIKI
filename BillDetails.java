package DOANCUOIKI;

import java.io.Serializable;

public class BillDetails implements Serializable {

    private Product product;
    private int amount;

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BillDetails(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public double Sum() {
        return product.getPrice() * amount;
    }

    public String Info() {
        return 
        product.Info() + "\t\t" 
        + amount;
    }

}
