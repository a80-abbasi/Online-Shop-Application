package graphics;

import Model.Account.Account;
import Model.Account.Admin;
import Model.Account.Customer;
import Model.Account.Seller;
import graphics.AdminProfile.AdminProfileMenu;
import graphics.CustomerProfile.CustomerProfileMenu;
import graphics.LoginAndRegister.LoginMenu;
import graphics.SellerProfile.SellerProfileMenu;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenu {
    public Button productsMenuButton;
    public Button profileButton;

    public void goToProductsMenu(ActionEvent event) {
        try {
            App.setRoot("ProductsMenu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToProfileMenu(ActionEvent event) {
        Account account = Account.getLoggedInAccount();
        if (account == null) {
            AlertBox.showMessage("Login Error", "You must login first!");
            try {
                App.setRoot("LoginMenu");
                LoginMenu.setParentMenu("MainMenu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (account instanceof Admin) {
            try {
                App.setRoot("AdminProfileMenu");
                AdminProfileMenu.setParentMenu("MainMenu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (account instanceof Customer) {
            try {
                App.setRoot("CustomerProfileMenu");
                CustomerProfileMenu.setParentMenu("MainMenu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (account instanceof Seller) {
            try {
                App.setRoot("SellerProfileMenu");
                SellerProfileMenu.setParentMenu("MainMenu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }
}
