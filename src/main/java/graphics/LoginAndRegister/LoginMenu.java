package graphics.LoginAndRegister;

import Controller.LoginAndRegisterManager;
import graphics.AlertBox;
import graphics.App;
import graphics.products.ProductPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
            ((Stage) usernameField.getScene().getWindow()).close();
            ProductPageController.loginPopUp = null;
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Login Failed", e.getMessage());
        }
    }

    public void signUp() {
        try {
            Stage registerPopUp = (Stage) usernameField.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("RegisterMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            registerPopUp.setScene(scene);
            registerPopUp.setTitle("register");
            registerPopUp.setResizable(false);
            /*loginPopUp.initStyle(StageStyle.UNDECORATED);*/
            RegisterMenu.setParentMenu(parentMenu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setParentMenu(String parentMenu) {
        LoginMenu.parentMenu = parentMenu;
    }
}
