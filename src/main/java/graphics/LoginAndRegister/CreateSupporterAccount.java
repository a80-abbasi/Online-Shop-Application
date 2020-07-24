package graphics.LoginAndRegister;

import Controller.LoginAndRegisterManager;
import graphics.AlertBox;
import graphics.App;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateSupporterAccount {
    public Button confirmButton;
    public TextField usernameField;
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField emailField;
    public TextField phoneNumberField;
    public PasswordField passwordField;

    private static String parentMenu = "MainMenu";

    private LoginAndRegisterManager loginAndRegisterManager;

    public void initialize() {
        this.loginAndRegisterManager = new LoginAndRegisterManager();
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
            try {
                loginAndRegisterManager.registerSupporter(username, password, firstName, lastName, email, phoneNumber);
                AlertBox.showMessage("Supporter Register", "Supporter Successfully registered");
                try {
                    App.setRoot(parentMenu);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IllegalArgumentException e) {
                AlertBox.showMessage("Failed to Register", e.getMessage());
            }
        }
    }

    public static void setParentMenu(String parentMenu) {
        CreateSupporterAccount.parentMenu = parentMenu;
    }
}