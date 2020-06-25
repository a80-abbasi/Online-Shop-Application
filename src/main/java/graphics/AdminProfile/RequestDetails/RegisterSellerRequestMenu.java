package graphics.AdminProfile.RequestDetails;

import Controller.AdminProfileManager;
import Model.Account.Account;
import Model.Account.Admin;
import Model.Request.RegisterSellerRequest;
import graphics.AlertBox;
import graphics.App;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class RegisterSellerRequestMenu {
    public TextField requestIDField;
    public TextField requestTypeField;
    public TextField usernameField;
    public PasswordField passwordField;
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField emailField;
    public TextField phoneNumberField;
    public TextField companyField;

    private static String parentMenu = "ManageRequests";

    private AdminProfileManager adminProfileManager;

    private static RegisterSellerRequest registerSellerRequest;

    public void initialize() {
        this.adminProfileManager = new AdminProfileManager((Admin) Account.getLoggedInAccount());
        requestIDField.setText(registerSellerRequest.getRequestId());
        requestTypeField.setText(registerSellerRequest.getRequestType());
        usernameField.setText(registerSellerRequest.getUsername());
        passwordField.setText(registerSellerRequest.getPassword());
        firstNameField.setText(registerSellerRequest.getName());
        lastNameField.setText(registerSellerRequest.getLastName());
        emailField.setText(registerSellerRequest.getEmail());
        phoneNumberField.setText(registerSellerRequest.getPhoneNumber());
        companyField.setText(registerSellerRequest.getCompanyName());
    }

    public void declineRequest(MouseEvent mouseEvent) {
        adminProfileManager.declineRequest(registerSellerRequest.getRequestId());
        AlertBox.showMessage("Decline Request", "Request with ID : <" + registerSellerRequest.getRequestId() + "> declined.");
        try {
            App.setRoot(parentMenu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptRequest(MouseEvent mouseEvent) {
        adminProfileManager.acceptRequest(registerSellerRequest.getRequestId());
        AlertBox.showMessage("Accept Request", "Request with ID : <" + registerSellerRequest.getRequestId() + "> accepted.");
        try {
            App.setRoot(parentMenu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setRegisterSellerRequest(RegisterSellerRequest registerSellerRequest) {
        RegisterSellerRequestMenu.registerSellerRequest = registerSellerRequest;
    }
}
