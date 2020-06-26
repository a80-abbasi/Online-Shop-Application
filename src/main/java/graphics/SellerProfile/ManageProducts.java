package graphics.SellerProfile;

import Controller.SellerProfileManager;
import Model.Account.Account;
import Model.Account.Seller;
import Model.Product.Category;
import Model.Product.Product;
import graphics.AlertBox;
import graphics.App;
import graphics.products.ProductPageController;
import graphics.products.SelectProductImage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ManageProducts {
    public TextField productIDField;
    public TextField productNameField;
    public TextField productCompanyNameField;
    public TextField productPriceField;
    public TextField productExistingNumberField;
    public TextArea productExplanationsField;

    public TableView productsTable;
    public ImageView backImage;
    public ImageView mainMenuImage;
    public MenuButton categoriesMenuButton;
    public TableColumn specialFeaturesColumn;
    public TableColumn valueColumn;
    public Button changeImageButton;
    public TableView productSpecialFeaturesTable;

    public static String productImageAddress;

    private Category category;
    private Product product;

    private ArrayList<FeatureData> allFeatureData;

    private SellerProfileManager sellerProfileManager;

    public void initialize() {
        this.sellerProfileManager = new SellerProfileManager((Seller) Account.getLoggedInAccount());
        productsTable = sellerProfileManager.getSellerProductsTable(productsTable);

        allFeatureData = new ArrayList<>();

        specialFeaturesColumn.setCellValueFactory(new PropertyValueFactory<String, FeatureData>("specialFeature"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<String, FeatureData>("value"));

        ProductPageController.setMainMenuButton(mainMenuImage);
        App.setBackButton(backImage, "SellerProfileMenu");

        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        for (String categoryName : sellerProfileManager.getAllCategories()) {
            MenuItem menuItem = new MenuItem(categoryName);
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    setCategory(categoryName);
                }
            });
            categoriesMenuButton.getItems().add(menuItem);
        }
    }

    private void setCategory(String categoryName) {
        this.category = Category.getCategoryByName(categoryName);
        categoriesMenuButton.setText(categoryName);
        if (category.getSpecialFeatures().isEmpty()) {
            return;
        }
        productSpecialFeaturesTable.getItems().clear();
        for (String specialFeature : category.getSpecialFeatures()) {
            FeatureData featureData = new FeatureData(specialFeature, "");
            productSpecialFeaturesTable.getItems().add(featureData);
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
                productExplanations.isEmpty() || values.size() != productSpecialFeaturesTable.getItems().size();
        if (informationIncomplete) {
            return;
        }
        try {
            sellerProfileManager.makeNewEditProductRequest(productID, productName, productCompanyName, productPrice,
                    productExistingNumber, productExplanations, category, values, productImageAddress);
            AlertBox.showMessage("Edit Product", "Your Request Was Successfully Sent To Admins");
        } catch (Exception e) {
            AlertBox.showMessage("Failed to Edit Product", e.getMessage());
        }
    }

    public void showProductBuyers(MouseEvent mouseEvent) {
        Object selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            return;
        }
        Product product = (Product) selectedProduct;
        TableView productBuyersTable = sellerProfileManager.getProductBuyersTable(product.getProductId());

        Stage window = new Stage();
        window.setTitle("Product Buyers");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        Button OKButton = new Button("OK");
        OKButton.setFont(Font.font("Times New Roman", 16));
        OKButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                window.close();
            }
        });

        vBox.getChildren().addAll(productBuyersTable, OKButton);
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    public void removeProduct(MouseEvent mouseEvent) {
        Object selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            return;
        }
        Product product = (Product) selectedProduct;
        sellerProfileManager.removeProduct(product.getProductId());
        AlertBox.showMessage("Remove Product", "Product With ID <" + product.getProductId() + "> Removed Successfully");
        productsTable.getItems().remove(product);
    }

    public void showProductDetails(MouseEvent mouseEvent) {
        Object selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            return;
        }
        product = (Product) selectedProduct;
        productIDField.setText(product.getProductId());
        productNameField.setText(product.getProductName());
        productCompanyNameField.setText(product.getCompanyName());
        productPriceField.setText(String.valueOf(product.getPrice()));
        productExistingNumberField.setText(String.valueOf(product.getExistingNumber()));
        productExplanationsField.setText(product.getExplanations());
        ManageProducts.productImageAddress = product.getImageAddress();

        category = product.getProductCategory();
        productSpecialFeaturesTable.getItems().setAll(category.getSpecialFeatures());
        categoriesMenuButton.setText(product.getProductCategory().getName());
    }

    public void changeImagePressed(ActionEvent event) {
        Stage selectImagePopUp = new Stage();
        Scene scene;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("selectProductImage.fxml"));
            scene = new Scene(fxmlLoader.load());
            ((SelectProductImage)fxmlLoader.getController()).prepareForChangingPhoto();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        selectImagePopUp.setScene(scene);
        selectImagePopUp.setTitle("select product image");
        selectImagePopUp.setResizable(false);
        selectImagePopUp.showAndWait();
    }

    public void editValueForSpecialFeature(TableColumn.CellEditEvent cellEditEvent) {
        FeatureData featureData = (FeatureData) productSpecialFeaturesTable.getSelectionModel().getSelectedItem();
        featureData.setValue((String) cellEditEvent.getNewValue());
    }
}
