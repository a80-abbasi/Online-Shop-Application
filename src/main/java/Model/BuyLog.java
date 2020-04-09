package Model;

import java.util.ArrayList;

public class BuyLog extends Log {
    private int paidAmount;
    private int discountAmount;
    private String sellerName; //could be replace by a Seller object?
    private boolean hasDelivered;
    private ArrayList<Product> boughtProducts;

    public BuyLog(String ID, String date, int paidAmount, int discountAmount, String sellerName) {
        super(ID, date);
        this.paidAmount = paidAmount;
        this.discountAmount = discountAmount;
        this.sellerName = sellerName;
        boughtProducts = new ArrayList<>();
    }

    public BuyLog() {
        super();
        this.paidAmount = 0;
        this.discountAmount = 0;
        this.sellerName = "";
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
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
}
