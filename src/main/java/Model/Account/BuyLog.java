package Model.Account;

import Model.Product.Product;

import java.util.ArrayList;

public class BuyLog extends Log {
    private int paidAmount;
    private int discountAmount;
    private Seller seller;
    private boolean hasDelivered;
    private ArrayList<Product> boughtProducts;

    public BuyLog(String ID, String date, int paidAmount, int discountAmount, Seller sellerName) {
        super(ID, date);
        this.paidAmount = paidAmount;
        this.discountAmount = discountAmount;
        this.seller = sellerName;
        boughtProducts = new ArrayList<>();
    }

    public BuyLog() {
        this("", "", 0, 0, null);
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
