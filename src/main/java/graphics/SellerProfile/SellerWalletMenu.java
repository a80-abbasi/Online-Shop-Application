package graphics.SellerProfile;

import Controller.SellerProfileManager;
import Model.Account.Account;
import Model.Account.Seller;
import graphics.AlertBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SellerWalletMenu {
    public TextField balanceField;
    public TextField withdrawAmountField;
    public TextField chargeAmountField;

    private SellerProfileManager sellerProfileManager;

    public void initialize() {
        this.sellerProfileManager = new SellerProfileManager((Seller) Account.getLoggedInAccount());
        balanceField.setText(String.valueOf(sellerProfileManager.getBalance()));
    }

    public void withdrawFromWallet(MouseEvent mouseEvent) {
        String withdrawAmount = withdrawAmountField.getText();
        try {
            int withdrawAmountMoney = Integer.parseInt(withdrawAmount);
            sellerProfileManager.withdrawFromWallet(withdrawAmountMoney);
            balanceField.setText(String.valueOf(sellerProfileManager.getBalance()));
        } catch (Exception e) {
            AlertBox.showMessage("Failed To Withdraw", e.getMessage());
        }
    }

    public void chargeWallet(MouseEvent mouseEvent) {
        String chargeAmount = chargeAmountField.getText();
        try {
            int chargeAmountMoney = Integer.parseInt(chargeAmount);
            sellerProfileManager.chargeWallet(chargeAmountMoney);
            balanceField.setText(String.valueOf(sellerProfileManager.getBalance()));
        } catch (Exception e) {
            AlertBox.showMessage("Failed To Charge", e.getMessage());
        }
    }
}
