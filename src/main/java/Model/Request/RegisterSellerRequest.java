package Model.Request;

import Model.Account.Account;
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
        super("register_seller_" + allRequests.size(), RequestType.Register_Seller_Request);
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        allRegisterSellerRequests.add(this);
    }

    public RegisterSellerRequest(){
        this("", "", "", "", "", "", "");
    }

    public static ArrayList<RegisterSellerRequest> getAllRegisterSellerRequests() {
        return allRegisterSellerRequests;
    }

    public static void setAllRegisterSellerRequests(ArrayList<RegisterSellerRequest> allRegisterSellerRequests) {
        RegisterSellerRequest.allRegisterSellerRequests = allRegisterSellerRequests;
    }

    @Override
    public void acceptRequest() throws IllegalArgumentException{
        if (Account.getAccountByUsername(username) == null) {
            new Seller(username, password, name, lastName, email, phoneNumber, companyName, 0);
        }
        else {
            throw new IllegalArgumentException();
        }
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
