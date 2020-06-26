package graphics.SellerProfile.Company;

import Controller.CustomerProfileManager;
import Controller.SellerProfileManager;
import Model.Account.Account;
import Model.Account.Customer;
import Model.Account.Seller;
import graphics.App;
import graphics.products.ProductPageController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class AddCompanyInformation {
    public TextField phoneNumber;
    public TextField address;
    public TextField openYear;


    public ImageView backImage;
    public ImageView mainMenuImage;

    private SellerProfileManager sellerProfileManager;

    public void initialize() {
        this.sellerProfileManager = new SellerProfileManager((Seller) Account.getLoggedInAccount());
        String parentMenu = "SellerProfileMenu";
        App.setBackButton(backImage, parentMenu);
        ProductPageController.setMainMenuButton(mainMenuImage);
    }

    public void submitInformationButton(MouseEvent mouseEvent) {
        sellerProfileManager.seller.setPhoneNumberOfCompany(phoneNumber.getText());
        sellerProfileManager.seller.setCompanyAddress(address.getText());
        sellerProfileManager.seller.setCompanyOpenYear(openYear.getText());
    }
}
