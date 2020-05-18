package Model.Request;

import Model.Account.OffStatus;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.Date;

public abstract class EditAddOffRequest extends Request {
    protected String offID;
    protected Date startTime;
    protected Date endTime;
    protected int offAmount;
    protected OffStatus offStatus;
    protected ArrayList<Product> offProducts;

    public EditAddOffRequest(String requestID, RequestType requestType) {
        super(requestID, requestType);
    }

    public ArrayList<Product> getOffProducts() {
        return offProducts;
    }

    public void setOffID(String offID) {
        this.offID = offID;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setOffAmount(int offAmount) {
        this.offAmount = offAmount;
    }

    public void setOffStatus(OffStatus offStatus) {
        this.offStatus = offStatus;
    }

    public void setOffProducts(ArrayList<Product> offProducts) {
        this.offProducts = offProducts;
    }

    public void addOffProduct(Product product) {
        offProducts.add(product);
    }

    @Override
    public abstract void acceptRequest();

    @Override
    public abstract String toString();
}
