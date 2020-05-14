package Model.Request;

public class RegisterSellerRequest extends Request {

    public RegisterSellerRequest() {
        super("register_customer_" + allRequests.size(), RequestType.Register_Seller_Request);
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
