package Model.Account;

import Model.Product.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BuyLog extends Log {
    private static ArrayList<BuyLog> allBuyLogs = new ArrayList<>();
    private double paidAmount;
    private double discountAmount;
    private boolean hasDelivered;
    private HashMap<Product, Integer> boughtProducts;

    public BuyLog(String ID, Date date, double paidAmount, double discountAmount, HashMap<Product, Integer> cart) {
        super(ID, date);
        this.paidAmount = paidAmount;
        allBuyLogs.add(this);
        this.discountAmount = discountAmount;
        this.boughtProducts = new HashMap<>(cart);
    }

    public BuyLog() {
        allBuyLogs.add(this);
        this.boughtProducts = new HashMap<>();
    }

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

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public HashMap<Product, Integer> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(HashMap<Product, Integer> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public static ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }

    @Override
    public String toString() {
        return "BuyLog{" +
                "paidAmount=" + paidAmount +
                ", discountAmount=" + discountAmount +
                ", hasDelivered=" + hasDelivered +
                ", boughtProducts=" + boughtProducts +
                '}';
    }
}
