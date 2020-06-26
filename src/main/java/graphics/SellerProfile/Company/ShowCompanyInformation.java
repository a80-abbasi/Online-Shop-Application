package graphics.SellerProfile.Company;

import Controller.SellerProfileManager;
import Model.Account.Account;
import Model.Account.Seller;
import graphics.App;
import graphics.products.ProductPageController;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ShowCompanyInformation {

    public Label CompanyName;
    public Label PhoneNumber;
    public Label OpenYear;
    public Label Address;

    public ImageView backImage;
    public ImageView mainMenuImage;

    public void initialize() {
        SellerProfileManager sellerProfileManager = new SellerProfileManager((Seller) Account.getLoggedInAccount());
        String parentMenu = "SellerProfileMenu";
        App.setBackButton(backImage, parentMenu);
        ProductPageController.setMainMenuButton(mainMenuImage);
        CompanyName.setText(sellerProfileManager.seller.getNameOfCompany());
        PhoneNumber.setText(sellerProfileManager.seller.getPhoneNumberOfCompany());
        OpenYear.setText(sellerProfileManager.seller.getCompanyOpenYear());
        Address.setText(sellerProfileManager.seller.getCompanyOpenYear());
    }
}
