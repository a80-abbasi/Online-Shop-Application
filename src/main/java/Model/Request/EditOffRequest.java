package Model.Request;

import Model.Account.Off;
import Model.Account.OffStatus;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.Date;

public class EditOffRequest extends EditAddOffRequest {
    private static ArrayList<EditOffRequest> allEditOffRequests = new ArrayList<>();
    private Off off;

    public EditOffRequest(Off off) {
        super("edit_off_" + allRequests.size(), RequestType.Editing_Off_Request);
        allEditOffRequests.add(this);
        this.setOff(off);
        this.setOffID(off.getOffID());
        this.setStartTime(off.getStartTime());
        this.setEndTime(off.getEndTime());
        this.setOffAmount(off.getOffAmount());
        this.setOffStatus(OffStatus.PENDING_FOR_EDITION);
        this.setOffProducts(off.getProducts());
    }

    public EditOffRequest() {
        this(null);
    }

    public static ArrayList<EditOffRequest> getAllEditOffRequests() {
        return allEditOffRequests;
    }

    public static void setAllEditOffRequests(ArrayList<EditOffRequest> allEditOffRequests) {
        EditOffRequest.allEditOffRequests = allEditOffRequests;
    }

    public void setOff(Off off) {
        this.off = off;
    }

    public void removeProduct(Product product) {
        offProducts.remove(product);
    }

    @Override
    public void acceptRequest() throws IllegalArgumentException{
        if (Off.getOffById(offID) != null && (!(Off.getOffById(offID).equals(off)))) {
            throw new IllegalArgumentException();
        }
        else {
            for (Product product : off.getProducts()) {
                product.setOff(null);
            }
            off.setOffID(offID);
            off.setStartTime(startTime);
            off.setEndTime(endTime);
            off.setOffStatus(OffStatus.CONFIRMED);
            off.setOffAmount(offAmount);
            off.setProducts(offProducts);
            for (Product product : offProducts) {
                product.setOff(off);
            }
        }
    }

    @Override
    public String toString() {
        return "EditOffRequest{" +
                "offID='" + offID + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", offAmount=" + offAmount +
                ", offStatus=" + offStatus +
                ", offProducts=" + offProducts +
                ", requestId='" + requestId + '\'' +
                ", requestType=" + requestType +
                '}';
    }
}
