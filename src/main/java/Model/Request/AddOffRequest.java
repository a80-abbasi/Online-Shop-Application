package Model.Request;

import Model.Account.Off;
import Model.Account.OffStatus;
import Model.Product.Product;

import java.util.ArrayList;

public class AddOffRequest extends EditAddOffRequest {
    private static ArrayList<AddOffRequest> allAddOffRequest = new ArrayList<>();

    public AddOffRequest() {
        super("add_product_" + allRequests.size(), RequestType.Adding_Off_Request);
        allAddOffRequest.add(this);
        this.setOffStatus(OffStatus.PENDING_FOR_CREATION);
        this.offProducts = new ArrayList<>();
    }

    public static ArrayList<AddOffRequest> getAllAddOffRequest() {
        return allAddOffRequest;
    }

    public static void setAllAddOffRequest(ArrayList<AddOffRequest> allAddOffRequest) {
        AddOffRequest.allAddOffRequest = allAddOffRequest;
    }

    @Override
    public void acceptRequest() throws IllegalArgumentException{
        if (Off.getOffById(offID) == null) {
            Off off = new Off(offID, startTime, endTime, offAmount, offProducts);
            for (Product product : offProducts) {
                product.setOff(off);
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "AddOffRequest{" +
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
