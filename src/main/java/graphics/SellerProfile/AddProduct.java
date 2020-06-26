package graphics.SellerProfile;

import graphics.App;
import graphics.products.ProductPageController;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AddProduct {
    public TextField productIDField;
    public TextField productNameField;
    public TextField productCompanyNameField;
    public TextField productPriceField;
    public TextField productExistingNumberField;
    public TextField productCategoryField;
    public TextField productSpecialFeaturesField;

    public TableView productsTable;
    public ImageView backImage;
    public ImageView mainMenuImage;

    public void initialize(){
        App.setBackButton(backImage, "SellerProfileMenu");
        ProductPageController.setMainMenuButton(mainMenuImage);
    }

    public void confirm(MouseEvent mouseEvent) {

    }
}
