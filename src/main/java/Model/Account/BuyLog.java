package Model.Account;

import Model.Product.Product;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends Log {
    private int paidAmount;
    private int discountAmount;
    private Seller seller;
    private boolean hasDelivered;
    private ArrayList<Product> boughtProducts;

    public BuyLog(String ID, Date date, int paidAmount, int discountAmount, Seller seller) {
        super(ID, date);
        this.paidAmount = paidAmount;
        this.discountAmount = discountAmount;
        this.seller = seller;
        boughtProducts = new ArrayList<>();
    }

    public BuyLog() {
        this("", null, 0, 0, null);
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public boolean isHasDelivered() {
        return hasDelivered;
    }

    public void setHasDelivered(boolean hasDelivered) {
        this.hasDelivered = hasDelivered;
    }

    public void addProductToLog(Product product){
        boughtProducts.add(product);
    }

    public ArrayList<Product> getBoughtProducts() {
        return boughtProducts;
    }

    @Override
    public String toString() {
        return "BuyLog{" +
                "paidAmount=" + paidAmount +
                ", discountAmount=" + discountAmount +
                ", seller=" + seller +
                ", hasDelivered=" + hasDelivered +
                ", boughtProducts=" + boughtProducts +
                '}';
    }
}
