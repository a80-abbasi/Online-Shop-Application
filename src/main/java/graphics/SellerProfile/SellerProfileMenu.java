package graphics.SellerProfile;

import Controller.SellerProfileManager;
import Model.Account.Account;
import Model.Account.Seller;
import graphics.AlertBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SellerProfileMenu {
    public TextField usernameField;
    public TextField passwordField;
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField emailField;
    public TextField phoneNumberField;
    public TextField companyField;
    public Button confirmButton;

    private static String parentMenu;

    private SellerProfileManager sellerProfileManager;

    public void initialize() {
        this.sellerProfileManager = new SellerProfileManager((Seller) Account.getLoggedInAccount());
        usernameField.setText(sellerProfileManager.getUsername());
        passwordField.setText(sellerProfileManager.getPassword());
        firstNameField.setText(sellerProfileManager.getFirstName());
        lastNameField.setText(sellerProfileManager.getLastName());
        emailField.setText(sellerProfileManager.getEmail());
        phoneNumberField.setText(sellerProfileManager.getPhoneNumber());
    }

    public void confirm(ActionEvent event) {
        changePassword();
        changeFirstName();
        changeLastName();
        changeEmail();
        changePhoneNumber();
        changeCompanyName();
    }

    private void changePassword() {
        String password = passwordField.getText();
        try {
            sellerProfileManager.editPassword(password);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit password", e.getMessage());
        }
    }

    private void changeFirstName() {
        String firstName = firstNameField.getText();
        try {
            sellerProfileManager.editFirstName(firstName);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit first name.", e.getMessage());
        }
    }

    private void changeLastName() {
        String lastName = lastNameField.getText();
        try {
            sellerProfileManager.editLastName(lastName);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit last name", e.getMessage());
        }
    }

    private void changeEmail() {
        String email = emailField.getText();
        try {
            sellerProfileManager.editEmail(email);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit email", e.getMessage());
        }
    }

    private void changePhoneNumber() {
        String phoneNumber = phoneNumberField.getText();
        try {
            sellerProfileManager.editPhoneNumber(phoneNumber);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit phone number", e.getMessage());
        }
    }

    private void changeCompanyName() {
        String companyName = companyField.getText();
        sellerProfileManager.editCompanyName(companyName);
    }

    public static void setParentMenu(String parentMenu) {
        SellerProfileMenu.setParentMenu(parentMenu);
    }

    public void manageOffs(MouseEvent mouseEvent) {

    }

    public void addOff(MouseEvent mouseEvent) {

    }

    public void manageProducts(MouseEvent mouseEvent) {

    }

    public void addProduct(MouseEvent mouseEvent) {

    }
}
