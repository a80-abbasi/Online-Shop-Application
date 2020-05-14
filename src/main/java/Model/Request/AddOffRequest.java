package Model.Request;

import Model.Account.Off;

import java.util.ArrayList;

public class AddOffRequest extends Request {
    private static ArrayList<AddOffRequest> allAddOffRequest = new ArrayList<>();
    private ArrayList<String> propertiesInOrder;

    public AddOffRequest(ArrayList<String> propertiesInOrder) {
        super("add_product_" + allRequests.size(), RequestType.Adding_Off_Request);
        allAddOffRequest.add(this);
        this.propertiesInOrder = propertiesInOrder;
    }

    public static ArrayList<AddOffRequest> getAllAddOffRequest() {
        return allAddOffRequest;
    }

    public static void setAllAddOffRequest(ArrayList<AddOffRequest> allAddOffRequest) {
        AddOffRequest.allAddOffRequest = allAddOffRequest;
    }

    @Override
    public void acceptRequest() {
        new Off(propertiesInOrder.get(0), propertiesInOrder.get(1), propertiesInOrder.get(2), Integer.parseInt(propertiesInOrder.get(3)));
    }

    @Override
    public String toString() {
        return "Off{" +
                "offID = '" + propertiesInOrder.get(0) + '\'' +
                ", startTime = '" + propertiesInOrder.get(1) + '\'' +
                ", endTime = '" + propertiesInOrder.get(2) + '\'' +
                ", offAmount = " + propertiesInOrder.get(3) +
                ", offStatus = " + propertiesInOrder.get(4) +
                ", products = " + propertiesInOrder.get(5) +
                '}';
    }

}
