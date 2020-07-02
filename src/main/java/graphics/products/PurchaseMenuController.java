package graphics.products;

import Controller.CustomerProfileManager;
import Model.Account.*;
import Model.Product.Product;
import graphics.AlertBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class PurchaseMenuController {

    public TextField nameTextField;
    public TextField phoneNumberTextField;
    public TextArea addressTextArea;
    public TextArea explanationTextArea;
    public Label alertLabel;
    public TextField discountTextField;
    public Label discountMessageLabel;
    public Pane buyLogPane;
    public Button buyItemsButton;
    public Button validateButton;
    public TabPane tabPane;
    public Label totalLabel;
    public Label totalAmountLabel;

    private Discount discount;
    private Customer customer;
    private double totalAmount;
    private HashMap<Product, Integer> cart;
    double finalPrice;

    public void initialize(){
        customer = (Customer) Account.getLoggedInAccount();
    }

    private void setValidateButton(){
        validateButton.setOnAction(e -> {
            boolean flag = false;
            if (discountTextField.getText().isBlank()){
                discountMessageLabel.setText("You must enter a discount code for validation");
                discountTextField.requestFocus();
                flag = true;
            }
            else {
                String discountCode = discountTextField.getText();
                Discount discount = Discount.getDiscountByDiscountCode(discountCode);
                if (discount == null){
                    discountMessageLabel.setText("There is no discount with this code");
                    flag = true;
                }
                else if (!discount.isAvailable()){
                    discountMessageLabel.setText("There is no discount with this code");
                    flag = true;
                }
                else if (customer.getUsedDiscounts().get(discount) >= discount.getDiscountPerCustomer()){
                    discountMessageLabel.setText("You have used this discount maximum times!");
                    flag = true;
                }
                else {
                    discountTextField.setDisable(true);
                    discountMessageLabel.setText(String.format("you can use your %.1f%% discount up to %.1f$",
                            discount.getDiscountPercent(), discount.getMaxPossibleDiscount()));
                    discountMessageLabel.setOpacity(1);
                    this.discount = discount;
                    HashMap<Discount, Integer> usedDiscounts = customer.getUsedDiscounts();
                    usedDiscounts.put(discount, usedDiscounts.getOrDefault(discount, 0) + 1);
                }
            }
            if (flag){
                discountMessageLabel.setOpacity(1);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> discountMessageLabel.setOpacity(0)));
                timeline.setCycleCount(1);
                timeline.play();
            }
        });
    }

    private void setBuyItemsButton(){
        totalAmount = cart.entrySet().stream().mapToDouble(entry -> entry.getKey().getPriceWithOff() * entry.getValue()).sum();
        AtomicBoolean flag = new AtomicBoolean(true);
        if (discount != null) {
            finalPrice = totalAmount - discount.calculateTotalDiscount(totalAmount);
        }
        else {
            finalPrice = totalAmount;
        }
        buyItemsButton.setOnAction(e -> {
            Stream.of(addressTextArea, phoneNumberTextField, nameTextField).forEach(field -> {
                if (field.getText().isBlank()) {
                    tabPane.getSelectionModel().select(0);
                    field.requestFocus();
                    alertLabel.setOpacity(1);
                    alertLabel.setText("required fields can't be blank!");
                    flag.set(false);
                }
            });
            if (flag.get()){
                if (!nameTextField.getText().matches("[a-zA-Z]+")){
                    alertLabel.setText("receiver name is invalid");
                    nameTextField.requestFocus();
                    nameTextField.selectAll();
                    flag.set(false);
                }
                else if (!phoneNumberTextField.getText().matches("\\d+")){
                    alertLabel.setText("phone number is invalid");
                    phoneNumberTextField.requestFocus();
                    phoneNumberTextField.selectAll();
                    flag.set(false);
                }
                else if (customer.getBalance() < finalPrice){
                    alertLabel.setText("You don't have enough money :(");
                    flag.set(false);
                }
                else {
                    showBoughtProducts();
                    finishBuying(finalPrice);
                    buyItemsButton.setDisable(true);
                    buyItemsButton.setOpacity(0.5);
                }
            }
            if (!flag.get()){
                tabPane.getSelectionModel().select(0);
                alertLabel.setOpacity(1);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> alertLabel.setOpacity(0)));
                timeline.setCycleCount(1);
                timeline.play();
            }
        });
    }

    private void showBoughtProducts(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label label1, label2, label3, label4, label5;
        gridPane.add((label1 = new Label("serial")), 0, 0);
        gridPane.add(label2 = new Label("name"), 1, 0);
        gridPane.add(label3 = new Label("Price"),2, 0);
        gridPane.add(label4 = new Label("quantity"), 3, 0);
        gridPane.add(label5 = new Label("amount"),4, 0);
        Stream.of(label1, label2, label3, label4, label5).forEach(label ->
                label.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 25; -fx-font-weight: bold"));
        AtomicInteger row = new AtomicInteger(1);
        cart.forEach(((product, number) -> setProductRow(product, number, gridPane, row.getAndIncrement())));
        buyLogPane.getChildren().add(gridPane);
        gridPane.setAlignment(Pos.CENTER);
        totalLabel.setOpacity(1);
        totalAmountLabel.setOpacity(1);
        totalAmountLabel.setText(finalPrice + "$");
    }

    private void setProductRow(Product product, int number, GridPane gridPane, int row) {
        Label serial, name, price, quantity, amount;
        serial = new Label(String.valueOf(row));
        name = new Label(product.getProductName());
        price = new Label(String.valueOf(product.getPriceWithOff()));
        quantity = new Label(String.valueOf(number));
        amount = new Label(String.valueOf(product.getPriceWithOff() * number));
        Stream.of(serial, name, price, quantity, amount).forEach(label -> {
            label.setAlignment(Pos.CENTER);
            label.setStyle("-fx-font-size: 20");
        });
        gridPane.add(serial, 0, row);
        gridPane.add(name, 1, row);
        gridPane.add(price, 2, row);
        gridPane.add(quantity, 3, row);
        gridPane.add(amount, 4, row);
    }

    private void finishBuying(double finalAmount){
        customer.setBalance(customer.getBalance() - finalAmount); //decrease customer money
        cart.forEach((product, number) -> {//increase sellers money & creating sell log
            Seller seller = product.getSeller();
            seller.setBalance(product.getSeller().getBalance() + product.getPriceWithOff() * number);
            SellLog sellLog = new SellLog("SellLog" + (SellLog.getAllSellLogs().size() + 1), new Date(), product.getPriceWithOff(),
                    product.getPrice() - product.getPriceWithOff(), product, number, customer.getName());
            seller.getSellLogs().add(sellLog);
            product.setExistingNumber(product.getExistingNumber() - number); //decrease product existing number
            if (!product.getProductBuyers().contains(customer.getUsername())){ //set product buyers
                product.getProductBuyers().add(customer.getUsername());
            }
        });
        BuyLog buyLog = new BuyLog("BuyLog" + (BuyLog.getAllBuyLogs().size() + 1), new Date(), finalAmount, totalAmount - finalAmount, cart);
        customer.getBuyLogs().add(buyLog); //creating buy log
        cart.clear(); //empty cart
        String discountGift = new CustomerProfileManager(customer).checkForDiscountGift();
        if (!discountGift.isEmpty()){
            AlertBox.showMessage("Discount Gift", discountGift);
        }
    }

    public void setCart(HashMap<Product, Integer> cart) {
        this.cart = cart;
        setValidateButton();
        setBuyItemsButton();
    }
}
