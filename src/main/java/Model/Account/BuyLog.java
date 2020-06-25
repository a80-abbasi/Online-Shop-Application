package Model.Account;

import Model.Product.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BuyLog extends Log {
    private double paidAmount;
    private double discountAmount;
    private boolean hasDelivered;
    private ArrayList<Product> products;
    private ArrayList<Integer> numbers;
    private ArrayList<Seller> sellers;

    public BuyLog(String ID, Date date, double paidAmount, double discountAmount, ArrayList<Product> products, ArrayList<Integer> numbers, ArrayList<Seller> sellers) {
        super(ID, date);
        this.paidAmount = paidAmount;
        this.discountAmount = discountAmount;
        this.products = products;
        this. numbers = numbers;
        this.sellers = sellers;
    }

//    public BuyLog() {
//        this("", null, 0, 0, null);
//    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public boolean isHasDelivered() {
        return hasDelivered;
    }

    public void setHasDelivered(boolean hasDelivered) {
        this.hasDelivered = hasDelivered;
    }





    @Override
    public String toString() {
        return "BuyLog{" +
                "paidAmount=" + paidAmount +
                ", discountAmount=" + discountAmount +
                //", seller=" + seller + // todo: add hashmap
                ", hasDelivered=" + hasDelivered +
                //", boughtProducts=" + boughtProducts +
                '}';
    }
}
