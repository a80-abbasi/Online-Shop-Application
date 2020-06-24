package graphics.CustomerProfile;

import Controller.CustomerProfileManager;
import Controller.SellerProfileManager;
import Model.Account.Account;
import Model.Account.Customer;
import Model.Account.Seller;
import graphics.AlertBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CustomerProfileMenu {
    public Button confirmButton;
    public TextField phoneNumberField;
    public TextField emailField;
    public TextField lastNameField;
    public TextField firstNameField;
    public TextField passwordField;
    public TextField usernameField;

    private static String parentMenu;

    private CustomerProfileManager customerProfileManager;

    public void initialize() {
        this.customerProfileManager = new CustomerProfileManager((Customer) Account.getLoggedInAccount());
        usernameField.setText(customerProfileManager.getUsername());
        passwordField.setText(customerProfileManager.getPassword());
        firstNameField.setText(customerProfileManager.getFirstName());
        lastNameField.setText(customerProfileManager.getLastName());
        emailField.setText(customerProfileManager.getEmail());
        phoneNumberField.setText(customerProfileManager.getPhoneNumber());
    }

    public void confirm(ActionEvent event) {
        changePassword();
        changeFirstName();
        changeLastName();
        changeEmail();
        changePhoneNumber();
    }

    private void changePassword() {
        String password = passwordField.getText();
        try {
            customerProfileManager.editPassword(password);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit password", e.getMessage());
        }
    }

    private void changeFirstName() {
        String firstName = firstNameField.getText();
        try {
            customerProfileManager.editFirstName(firstName);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit first name.", e.getMessage());
        }
    }

    private void changeLastName() {
        String lastName = lastNameField.getText();
        try {
            customerProfileManager.editLastName(lastName);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit last name", e.getMessage());
        }
    }

    private void changeEmail() {
        String email = emailField.getText();
        try {
            customerProfileManager.editEmail(email);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit email", e.getMessage());
        }
    }

    private void changePhoneNumber() {
        String phoneNumber = phoneNumberField.getText();
        try {
            customerProfileManager.editPhoneNumber(phoneNumber);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit phone number", e.getMessage());
        }
    }

    public static void setParentMenu(String parentMenu) {
        CustomerProfileMenu.parentMenu = parentMenu;
    }
}
