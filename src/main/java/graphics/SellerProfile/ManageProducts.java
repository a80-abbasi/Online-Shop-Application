package graphics.SellerProfile;

import Controller.SellerProfileManager;
import Model.Account.Account;
import Model.Account.Seller;
import Model.Product.Category;
import Model.Product.Product;
import graphics.AlertBox;
import graphics.App;
import graphics.products.ProductPageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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

    private SellerProfileManager sellerProfileManager;

    public void initialize() {
        this.sellerProfileManager = new SellerProfileManager((Seller) Account.getLoggedInAccount());
        productsTable = sellerProfileManager.getSellerProductsTable(productsTable);

        ProductPageController.setMainMenuButton(mainMenuImage);
        App.setBackButton(backImage, "SellerProfileMenu");

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
        productSpecialFeaturesTable.getItems().addAll(category.getSpecialFeatures());
    }

    public void confirm(MouseEvent mouseEvent) {
        String productID = productIDField.getText();
        String productName = productNameField.getText();
        String productCompanyName = productCompanyNameField.getText();
        String productPrice = productPriceField.getText();
        String productExistingNumber = productExistingNumberField.getText();
        String productExplanations = productExplanationsField.getText();
        ArrayList<String> values = new ArrayList<>();
        int i = 0;
        while (valueColumn.getCellData(i) != null) {
            values.add((String) valueColumn.getCellData(i));
            i++;
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
        Product product = (Product) selectedProduct;
        productIDField.setText(product.getProductId());
        productNameField.setText(product.getProductName());
        productCompanyNameField.setText(product.getCompanyName());
        productPriceField.setText(String.valueOf(product.getPrice()));
        productExistingNumberField.setText(String.valueOf(product.getExistingNumber()));
        productExplanationsField.setText(product.getExplanations());

        category = product.getProductCategory();
        productSpecialFeaturesTable.getItems().setAll(category.getSpecialFeatures());
        categoriesMenuButton.setText(product.getProductCategory().getName());
    }
}
