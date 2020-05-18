package Model.Account;

import Model.Product.Product;

public class SellLog extends Log {
    private int received;
    private int offAmount;
    private Product product;
    private String buyerName;
    private boolean hasSent;

    public SellLog(String ID, String date, int received, int offAmount, Product product, String buyerName) {
        super(ID, date);
        this.received = received;
        this.offAmount = offAmount;
        this.product = product;
        this.buyerName = buyerName;
    }

    public SellLog() {
        super();
        this.received = 0;
        this.offAmount = 0;
        this.product = null;
        this.buyerName = "";
    }

    public int getReceived() {
        return received;
    }

    public void setReceived(int received) {
        this.received = received;
    }

    public int getOffAmount() {
        return offAmount;
    }

    public void setOffAmount(int offAmount) {
        this.offAmount = offAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public boolean isHasSent() {
        return hasSent;
    }

    public void setHasSent(boolean hasSent) {
        this.hasSent = hasSent;
    }

    @Override
    public String toString() {
        return "SellLog{" +
                "received=" + received +
                ", offAmount=" + offAmount +
                ", product=" + product +
                ", buyerName='" + buyerName + '\'' +
                ", hasSent=" + hasSent +
                '}';
    }
}
