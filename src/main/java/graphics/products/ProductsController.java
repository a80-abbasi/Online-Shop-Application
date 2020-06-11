package graphics.products;

import Controller.ProductsManager;
import Model.Product.Product;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ProductsController {
    private final static String selectedColor = " #7ec7f6";
    public Label latestLabel;
    public Label visitNumberLabel;
    public Label lowestPriceLabel;
    public Label scoreLabel;
    public Label highestPriceLabel;

    private ArrayList<Product> products;
    private ProductsManager productsManager;

    public void initialize() {
        productsManager = new ProductsManager();
        latestLabelClicked(null);
        showProducts();
    }

    private void showProducts() {

    }

    private void mouseEntered(Label label){
        label.setOpacity(0.5);
    }

    private void mouseExited(Label label){
        label.setOpacity(1);
    }

    public void latestLabelClicked(MouseEvent mouseEvent) {
        productsManager.useSortByTime();
        showProducts();
    }

    public void latestLabelMouseEntered(MouseEvent mouseEvent) {
        mouseEntered(latestLabel);
    }

    public void latestLabelMouseExited(MouseEvent mouseEvent) {
        mouseExited(latestLabel);
    }

    public void visitNumberLabelClicked(MouseEvent mouseEvent) {
        productsManager.useSortByVisit();
        showProducts();
    }

    public void visitNumberLabelEntered(MouseEvent mouseEvent) {
        mouseEntered(visitNumberLabel);
    }

    public void visitNumberLabelExited(MouseEvent mouseEvent) {
        mouseExited(visitNumberLabel);
    }

    public void lowestPriceLabelClicked(MouseEvent mouseEvent) {
        productsManager.useSortByLowestPrice();
        showProducts();
    }

    public void lowestPriceLabelMouseEntered(MouseEvent mouseEvent) {
        mouseEntered(lowestPriceLabel);
    }

    public void lowestPriceLabelMouseExited(MouseEvent mouseEvent) {
        mouseExited(lowestPriceLabel);
    }

    public void scoreLabelClicked(MouseEvent mouseEvent) {
        productsManager.useSortByScore();
        showProducts();
    }

    public void scoreLabelMouseEntered(MouseEvent mouseEvent) {
        mouseEntered(scoreLabel);
    }

    public void scoreLabelMouseExited(MouseEvent mouseEvent) {
        mouseExited(scoreLabel);
    }

    public void HighestPriceLabelClicked(MouseEvent mouseEvent) {
        productsManager.useSortByHighestPrice();
        showProducts();
    }

    public void HighestPriceLabelMouseEntered(MouseEvent mouseEvent) {
        mouseEntered(highestPriceLabel);
    }

    public void HighestPriceLabelMouseExited(MouseEvent mouseEvent) {
        mouseExited(highestPriceLabel);
    }
}
