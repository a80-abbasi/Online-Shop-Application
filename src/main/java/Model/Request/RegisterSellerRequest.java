package Model.Request;

import java.util.ArrayList;

public class RegisterSellerRequest extends Request {
    private static ArrayList<RegisterSellerRequest> allRegisterSellerRequests = new ArrayList<>();
    public RegisterSellerRequest() {
        super("register_customer_" + allRequests.size(), RequestType.Register_Seller_Request);
        allRegisterSellerRequests.add(this);
    }

    public static ArrayList<RegisterSellerRequest> getAllRegisterSellerRequests() {
        return allRegisterSellerRequests;
    }

    public static void setAllRegisterSellerRequests(ArrayList<RegisterSellerRequest> allRegisterSellerRequests) {
        RegisterSellerRequest.allRegisterSellerRequests = allRegisterSellerRequests;
    }

    @Override
    public void acceptRequest() {

    }

    @Override
    public String toString() {
        return null;
        //todo: completing
    }

}
