package graphics.CustomerProfile;

import Controller.CustomerProfileManager;
import Model.Account.Account;
import Model.Account.Customer;
import graphics.AlertBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CustomerWalletMenu {
    public TextField walletBalanceField;
    public TextField chargeWalletAmount;

    private CustomerProfileManager customerProfileManager;

    public void initialize() {
        this.customerProfileManager = new CustomerProfileManager((Customer) Account.getLoggedInAccount());
        walletBalanceField.setText(String.valueOf(customerProfileManager.getBalance()));
    }

    public void chargeWallet(MouseEvent mouseEvent) {
        String chargeAmount = chargeWalletAmount.getText();
        try {
            customerProfileManager.chargeWallet(Integer.parseInt(chargeAmount));
            walletBalanceField.setText(String.valueOf(customerProfileManager.getBalance()));
        } catch (Exception e) {
            AlertBox.showMessage("Failed to charge", e.getMessage());
        }
    }
}
