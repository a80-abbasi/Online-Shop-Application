package Model.Account.Request;

public class RegisterCustomerRequest extends Request {

    public RegisterCustomerRequest() {
        super("register_customer_" + allRequests.size());
    }

}
