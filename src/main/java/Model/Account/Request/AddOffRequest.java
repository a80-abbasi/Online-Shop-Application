package Model.Account.Request;

import java.util.ArrayList;

public class AddOffRequest extends Request {
    private ArrayList<String> propertiesInOrder;

    public AddOffRequest(ArrayList<String> propertiesInOrder) {
        super("add_product_" + allRequests.size());
        this.propertiesInOrder = propertiesInOrder;
    }

    @Override
    public void acceptRequest() {

    }

}
