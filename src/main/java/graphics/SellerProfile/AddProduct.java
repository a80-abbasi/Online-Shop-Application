package graphics.SellerProfile;

import Controller.SellerProfileManager;
import Model.Account.Account;
import Model.Account.Seller;
import Model.Product.Category;
import graphics.AlertBox;
import graphics.App;
import graphics.products.ProductPageController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddProduct {
    public TextField productIDField;
    public TextField productNameField;
    public TextField productCompanyNameField;
    public TextField productPriceField;
    public TextField productExistingNumberField;

    public ImageView backImage;
    public ImageView mainMenuImage;

    public static String productImageAddress;
    public static Stage selectImagePopUp;

    public TextArea productExplanationsField;
    public MenuButton selectCategoryMenuButton;
    public Button confirmButton;

    public TableView specialFeaturesTable;
    public TableColumn specialFeatureColumn;
    public TableColumn valueColumn;

    private Category category;

    private ArrayList<FeatureData> allFeatureData;

    private SellerProfileManager sellerProfileManager;

    public void initialize(){
        productImageAddress = "file:src\\main\\resources\\Images\\products\\unKnown.jpg";
        App.setBackButton(backImage, "SellerProfileMenu");
        ProductPageController.setMainMenuButton(mainMenuImage);

        allFeatureData = new ArrayList<>();

        this.sellerProfileManager = new SellerProfileManager((Seller) Account.getLoggedInAccount());

        specialFeatureColumn.setCellValueFactory(new PropertyValueFactory<String, FeatureData>("specialFeature"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<String, FeatureData>("value"));

        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        for (String categoryName : sellerProfileManager.getAllCategories()) {
            MenuItem menuItem = new MenuItem(categoryName);
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    setCategory(categoryName);
                }
            });
            selectCategoryMenuButton.getItems().add(menuItem);
        }
    }

    private void setCategory(String categoryName) {
        this.category = Category.getCategoryByName(categoryName);
        selectCategoryMenuButton.setText(categoryName);
        if (category.getSpecialFeatures().isEmpty()) {
            return;
        }
        specialFeaturesTable.getItems().clear();
        for (String specialFeature : category.getSpecialFeatures()) {
            FeatureData featureData = new FeatureData(specialFeature, "");
            specialFeaturesTable.getItems().add(featureData);
            allFeatureData.add(featureData);
        }
    }

    public void confirm(MouseEvent mouseEvent) {
        String productID = productIDField.getText();
        String productName = productNameField.getText();
        String productCompanyName = productCompanyNameField.getText();
        String productPrice = productPriceField.getText();
        String productExistingNumber = productExistingNumberField.getText();
        String productExplanations = productExplanationsField.getText();
        ArrayList<String> values = new ArrayList<>();
        for (FeatureData featureData : allFeatureData) {
            values.add(featureData.getValue());
        }
        boolean informationIncomplete = productID.isEmpty() || productName.isEmpty() || productCompanyName.isEmpty() || productPrice.isEmpty() || productExistingNumber.isEmpty() ||
                productExplanations.isEmpty() || values.size() != specialFeaturesTable.getItems().size();
        if (informationIncomplete) {
            return;
        }
        try {
            sellerProfileManager.makeNewAddProductRequest(productID, productName, productCompanyName, productPrice,
                    productExistingNumber, productExplanations, category, values, productImageAddress);
            AlertBox.showMessage("Add Product", "Your Request Was Successfully Sent To Admins");
        } catch (Exception e) {
            AlertBox.showMessage("Failed to Add Product", e.getMessage());
        }
    }

    public void addImageButtonPressed(ActionEvent event) {
        if (selectImagePopUp == null) {
            selectImagePopUp = new Stage();
            Scene scene;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("selectProductImage.fxml"));
                scene = new Scene(fxmlLoader.load());
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

    public void editValueForSpecialFeature(TableColumn.CellEditEvent cellEditEvent) {
        FeatureData featureData = (FeatureData) specialFeaturesTable.getSelectionModel().getSelectedItem();
        featureData.setValue((String) cellEditEvent.getNewValue());
    }
}
