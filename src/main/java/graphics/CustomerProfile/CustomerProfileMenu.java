package graphics.CustomerProfile;

import Controller.CustomerProfileManager;
import Controller.SellerProfileManager;
import Model.Account.Account;
import Model.Account.BuyLog;
import Model.Account.Customer;
import Model.Account.Seller;
import Model.Product.Comment;
import Model.Product.Product;
import graphics.AlertBox;
import graphics.App;
import graphics.products.ProductPageController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomerProfileMenu {
    public Button confirmButton;
    public TextField phoneNumberField;
    public TextField emailField;
    public TextField lastNameField;
    public TextField firstNameField;
    public TextField passwordField;
    public TextField usernameField;
    public Label balanceLabel;
    public AnchorPane showDiscountCodesScroll;
    public ImageView backImage;
    public ImageView mainMenuImage;
    public ImageView cartImage;
    private TableView table = new TableView();

    private ArrayList<Pane> showingBuyLogs = new ArrayList<>();
    public Label numberOfBuyLogsLabel;

    private static String parentMenu;
    public TextField orderIDForShowOrder;
    public AnchorPane showOrderScroll;

    private CustomerProfileManager customerProfileManager;

    public void initialize() {
        this.customerProfileManager = new CustomerProfileManager((Customer) Account.getLoggedInAccount());
        usernameField.setText(customerProfileManager.getUsername());
        passwordField.setText(customerProfileManager.getPassword());
        firstNameField.setText(customerProfileManager.getFirstName());
        lastNameField.setText(customerProfileManager.getLastName());
        emailField.setText(customerProfileManager.getEmail());
        phoneNumberField.setText(customerProfileManager.getPhoneNumber());
        balanceLabel.setText(customerProfileManager.viewBalance() + "$");
        App.setBackButton(backImage, "MainMenu");
        ProductPageController.setMainMenuButton(mainMenuImage);
        ProductPageController.setCartButton(cartImage);
    }

    public void confirm(ActionEvent event) {
        changePassword();
        changeFirstName();
        changeLastName();
        changeEmail();
        changePhoneNumber();
    }

    private void changePassword() {
        String password = passwordField.getText();
        try {
            customerProfileManager.editPassword(password);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit password", e.getMessage());
        }
    }

    private void changeFirstName() {
        String firstName = firstNameField.getText();
        try {
            customerProfileManager.editFirstName(firstName);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit first name.", e.getMessage());
        }
    }

    private void changeLastName() {
        String lastName = lastNameField.getText();
        try {
            customerProfileManager.editLastName(lastName);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit last name", e.getMessage());
        }
    }

    private void changeEmail() {
        String email = emailField.getText();
        try {
            customerProfileManager.editEmail(email);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit email", e.getMessage());
        }
    }

    private void changePhoneNumber() {
        String phoneNumber = phoneNumberField.getText();
        try {
            customerProfileManager.editPhoneNumber(phoneNumber);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit phone number", e.getMessage());
        }
    }

    public static void setParentMenu(String parentMenu) {
        CustomerProfileMenu.parentMenu = parentMenu;
    }


    public void showOrderByID(MouseEvent mouseEvent) throws Exception{
        String orderID =  orderIDForShowOrder.getText();
        //BuyLog buyLog = customerProfileManager.customer.getBuyLogByID(orderID);
        TableView table = new TableView<>();
        TableColumn<String, Data> productNameCol = new TableColumn("Product Name");
        productNameCol.setMinWidth(200);
        productNameCol.setCellValueFactory(
                new PropertyValueFactory<>("productName"));

        TableColumn<String, Data> productNumberCol = new TableColumn("Product Number");
        productNumberCol.setMinWidth(100);
        productNumberCol.setCellValueFactory(
                new PropertyValueFactory<>("productNumber"));

        TableColumn<String, Data> productSellerCol = new TableColumn("Product Seller");
        productSellerCol.setMinWidth(200);
        productSellerCol.setCellValueFactory(
                new PropertyValueFactory<>("productSeller"));

        table.getColumns().addAll(productNameCol, productNumberCol, productSellerCol);
        table.getItems().add(new Data("a", "a", "a"));

        //table.setEditable(true);

        //        for (int i = 0; i < buyLog.getBoughtProducts().keySet().size(); i++) {
    //            ArrayList<Product> products = new ArrayList<>(buyLog.getBoughtProducts().keySet());
    //            FXCollections.observableArrayList().add(new Data(products.get(i).getProductName(), buyLog.getBoughtProducts().get(products.get(i)).toString(), products.get(i).getProductSeller().getName()));
    //        }

        Stage stage = new Stage();
        //public void start(Stage stage) {
        stage.setTitle("Order");
        stage.setWidth(550);
        stage.setHeight(500);

        final Label label = new Label("Date" + "1.2.3" + "Price:" + "10$");
        label.setFont(new Font("Arial", 20));

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        Scene scene1 = new Scene(vbox);

        stage.setScene(scene1);
        stage.show();
    }
}

