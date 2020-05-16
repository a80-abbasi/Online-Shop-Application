package Model.Request;

import Model.Account.Seller;

import java.util.ArrayList;

public class RegisterSellerRequest extends Request {
    private static ArrayList<RegisterSellerRequest> allRegisterSellerRequests = new ArrayList<>();
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String companyName;

    public RegisterSellerRequest(String username, String password, String name, String lastName, String email,
                                 String phoneNumber, String companyName) {
        super("register_customer_" + allRequests.size(), RequestType.Register_Seller_Request);
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
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
        new Seller(username, password, name, lastName, email, phoneNumber, companyName, 0);
    }

    @Override
    public String toString() {
        return "RegisterSellerRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", requestId='" + requestId + '\'' +
                ", requestType=" + requestType +
                '}';
    }
}
