package graphics.LoginAndRegister;

import Controller.LoginAndRegisterManager;
import Model.Account.AccountType;
import graphics.AlertBox;
import graphics.App;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;

public class RegisterMenu {
    public AnchorPane registerPane;

    public Button confirmButton;
    public TextField phoneNumberField;
    public PasswordField passwordField;
    public TextField emailField;
    public TextField lastNameField;
    public TextField firstNameField;
    public TextField usernameField;
    public TextField companyField;
    public Label companyLabel;

    private LoginAndRegisterManager loginAndRegisterManager;
    private AccountType accountType;

    private static String parentMenu;

    public void initialize() {
        this.loginAndRegisterManager = new LoginAndRegisterManager();
        this.accountType = AccountType.CUSTOMER;
    }

    public void confirm(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        boolean confirmButtonInability = username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty();
        if (!(confirmButtonInability)) {
            if (accountType == AccountType.CUSTOMER) {
                try {
                    loginAndRegisterManager.registerCustomer(username.trim(), password.trim(), firstName.trim(), lastName, email, phoneNumber);
                    AlertBox.showMessage("Register Customer", "Customer Registered Successfully");
                } catch (IllegalArgumentException e) {
                    AlertBox.showMessage("Failed to Register", e.getMessage());
                }
            } else if (accountType == AccountType.SELLER) {
                String companyName = companyField.getText();
                try {
                    loginAndRegisterManager.registerSeller(username, password, firstName, lastName, email, phoneNumber, companyName);
                    AlertBox.showMessage("Register Seller", "Your Request Was Sent to Admin Successfully");
                } catch (IllegalArgumentException e) {
                    AlertBox.showMessage("Failed to Register", e.getMessage());
                }
            }
            try {
                App.setRoot(parentMenu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAccountTypeCustomer(ActionEvent event) {
        this.accountType = AccountType.CUSTOMER;
        registerPane.getChildren().removeAll(companyLabel, companyField);
        companyLabel = null;
        companyField = null;

        this.confirmButton.setLayoutY(300);
    }

    public void setAccountTypeSeller(ActionEvent event) {
        this.accountType = AccountType.SELLER;

        this.companyLabel = new Label("Company");
        companyLabel.setLayoutX(69.0);
        companyLabel.setLayoutY(305);
        companyLabel.setFont(Font.font("Times New Roman", 14));

        companyField = new TextField();
        companyField.setLayoutX(160);
        companyField.setLayoutY(300);
        companyField.setFont(Font.font("Times New Roman", 14));

        this.confirmButton.setLayoutY(340);

        this.registerPane.getChildren().addAll(companyField, companyLabel);
    }

    public static void setParentMenu(String parentMenu) {
        RegisterMenu.setParentMenu(parentMenu);
    }
}
