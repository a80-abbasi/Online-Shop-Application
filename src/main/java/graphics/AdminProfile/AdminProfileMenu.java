package graphics.AdminProfile;

import Controller.AdminProfileManager;
import Model.Account.Account;
import Model.Account.Admin;
import graphics.AlertBox;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminProfileMenu {
    private AdminProfileManager adminProfileManager;
    public TextField usernameField;
    public PasswordField passwordField;
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField emailField;
    public TextField phoneNumberField;
    public Button confirmButton;

    public void initialize() {
        this.adminProfileManager = new AdminProfileManager((Admin) Account.getLoggedInAccount());
        usernameField.setText(adminProfileManager.getUsername());
        passwordField.setText(adminProfileManager.getPassword());
        firstNameField.setText(adminProfileManager.getFirstName());
        lastNameField.setText(adminProfileManager.getLastName());
        emailField.setText(adminProfileManager.getEmail());
        phoneNumberField.setText(adminProfileManager.getPhoneNumber());
    }

    public void confirm() {
        changeUsername();
        changePassword();
        changeFirstName();
        changeLastName();
        changeEmail();
        changePhoneNumber();
    }

    private void changeUsername() {
        String userName = usernameField.getText();
        try {
            adminProfileManager.editUsername(userName);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit username.", e.getMessage());
        }
    }

    private void changePassword() {
        String password = passwordField.getText();
        adminProfileManager.editPassword(password);
    }

    private void changeFirstName() {
        String firstName = firstNameField.getText();
        try {
            adminProfileManager.editFirstName(firstName);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit first name.", e.getMessage());
        }
    }

    private void changeLastName() {
        String lastName = lastNameField.getText();
        try {
            adminProfileManager.editLastName(lastName);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit last name", e.getMessage());
        }
    }

    private void changeEmail() {
        String email = emailField.getText();
        try {
            adminProfileManager.editEmail(email);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit email", e.getMessage());
        }
    }

    private void changePhoneNumber() {
        String phoneNumber = phoneNumberField.getText();
        try {
            adminProfileManager.editPhoneNumber(phoneNumber);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit phone number", e.getMessage());
        }
    }

    public void createManagerAccount(MouseEvent mouseEvent) {

    }

    public void showAllUsers(MouseEvent mouseEvent) {

    }

    public void deleteUser(MouseEvent mouseEvent) {

    }

    public void showAllRequests(MouseEvent mouseEvent) {

    }

    public void getRequestDetail(MouseEvent mouseEvent) {

    }

    public void acceptRequest(MouseEvent mouseEvent) {

    }

    public void declineRequest(MouseEvent mouseEvent) {

    }

    public void removeProduct(MouseEvent mouseEvent) {

    }

    public void showAllCategories(MouseEvent mouseEvent) {

    }

    public void addCategory(MouseEvent mouseEvent) {

    }

    public void editCategory(MouseEvent mouseEvent) {

    }

    public void removeCategory(MouseEvent mouseEvent) {

    }

    public void showAllDiscounts(MouseEvent mouseEvent) {

    }

    public void editDiscount(MouseEvent mouseEvent) {

    }

    public void removeDiscount(MouseEvent mouseEvent) {

    }

    public void createDiscount(MouseEvent mouseEvent) {

    }
}