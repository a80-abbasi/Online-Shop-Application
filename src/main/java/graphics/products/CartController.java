package graphics.products;

import Model.Account.Account;
import Model.Account.Customer;
import Model.Product.Product;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class CartController {

    public Pane mainPane;
    public Label XLabel;

    private HashMap<Product, Integer> cart;

    public void initialize(){
        Account account = Account.getLoggedInAccount();
        if (account == null){
            cart = Customer.getTmpCart();
        }
        else if (account instanceof Customer){
            Customer customer = (Customer) account;
            cart = customer.getCart();
        }
        if (cart == null || cart.isEmpty()){
            setEmptyLabel();
        }
        else {
            fillCart();
        }
        ProductPageController.shadowOnMouseHover(XLabel);
        XLabel.setOnMouseClicked(e -> {
            ProductPageController.getCartPopUp().close();
            ProductPageController.setCartPopUp(null);
        });
    }

    private void setEmptyLabel(){
        Label label = new Label("Your Cart Is Empty!");
        label.setWrapText(true);
        label.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 30");
        mainPane.getChildren().add(label);
        label.setLayoutX(mainPane.getPrefWidth() / 3);
        label.setLayoutY(50);
    }

    private void fillCart(){

    }
}
