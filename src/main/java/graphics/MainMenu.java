package graphics;

import Model.Account.Account;
import Model.Account.Admin;
import Model.Account.Customer;
import Model.Account.Seller;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenu {
    public Button productsMenuButton;
    public Button offMenuButton;
    public Button profileButton;
    public Button backButton;

    public void goToProductsMenu(ActionEvent event) {
        try {
            App.setRoot("ProductsMenu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToOffMenu(ActionEvent event) {
        try {
            App.setRoot("OffMenu");
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (account instanceof Admin) {
            try {
                App.setRoot("AdminProfileMenu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (account instanceof Customer) {
            try {
                App.setRoot("CustomerProfileMenu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (account instanceof Seller) {
            try {
                App.setRoot("SellerProfileMenu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void goBack(ActionEvent event) {
        //todo: parentMenu and these stuff
    }
}
