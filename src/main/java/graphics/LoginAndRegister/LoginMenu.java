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
    public Button signUpButton;
    public TextField usernameField;
    public TextField passwordField;

    private static String parentMenu;

    public void initialize() {
        this.loginAndRegisterManager = new LoginAndRegisterManager();
    }

    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        try {
            loginAndRegisterManager.loginUser(username, password);
            try {
                App.setRoot(parentMenu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            AlertBox.showMessage("Login Failed", e.getMessage());
        }
    }

    public void signUp() {
        try {
            App.setRoot("RegisterMenu");
            RegisterMenu.setParentMenu("MainMenu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setParentMenu(String parentMenu) {
        LoginMenu.parentMenu = parentMenu;
    }
}
