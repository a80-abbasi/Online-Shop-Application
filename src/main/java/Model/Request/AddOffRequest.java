package Model.Request;

import Model.Account.Off;
import Model.Account.OffStatus;

import java.util.ArrayList;

public class AddOffRequest extends EditAddOffRequest {
    private static ArrayList<AddOffRequest> allAddOffRequest = new ArrayList<>();

    //todo: making StartTime and EndTime Date instead of String

    public AddOffRequest() {
        super("add_product_" + allRequests.size(), RequestType.Adding_Off_Request);
        allAddOffRequest.add(this);
        this.setOffStatus(OffStatus.PENDING_FOR_CREATION);
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
            new Off(offID, startTime, endTime, offAmount);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "AddOffRequest{" +
                "offID='" + offID + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", offAmount=" + offAmount +
                ", offStatus=" + offStatus +
                ", requestId='" + requestId + '\'' +
                ", requestType=" + requestType +
                '}';
    }

}
