package graphics.LoginAndRegister;

import Controller.LoginAndRegisterManager;
import graphics.AlertBox;
import graphics.App;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginMenu {
    private LoginAndRegisterManager loginAndRegisterManager;
    public Button loginButton;
    public Button customerRegister;
    public Button sellerRegister;
    public TextField usernameField;
    public TextField passwordField;

    public void initialize() {
        this.loginAndRegisterManager = new LoginAndRegisterManager();
    }

    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        try {
            loginAndRegisterManager.loginUser(username, password);
        } catch (IllegalAccessException e) {
            AlertBox.showMessage("Login Failed", e.getMessage());
        }
    }

    public void signUp() {
        try {
            App.setRoot("RegisterMenu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
