package graphics.SellerProfile;

import graphics.App;
import graphics.products.ProductPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

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

    public static ImageView productImage;
    public static Stage selectImagePopUp;

    public void initialize(){
        productImage = new ImageView(new Image("file:src\\main\\resources\\Images\\products\\unKnown.jpg"));
        App.setBackButton(backImage, "SellerProfileMenu");
        ProductPageController.setMainMenuButton(mainMenuImage);
    }

    public void confirm(MouseEvent mouseEvent) {

    }

    public void addImageButtonPressed(ActionEvent event) {
        if (selectImagePopUp == null) {
            selectImagePopUp = new Stage();
            Scene scene;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("selectProductImage.fxml"));
                scene = new Scene(fxmlLoader.load());
                ProductPageController newPage = ((ProductPageController) fxmlLoader.getController());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            selectImagePopUp.setScene(scene);
            selectImagePopUp.setTitle("select product image");
            selectImagePopUp.setResizable(false);
            selectImagePopUp.showAndWait();
        }
    }
}
