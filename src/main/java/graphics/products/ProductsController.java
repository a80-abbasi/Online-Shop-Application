package graphics.products;

import Controller.ProductsManager;
import Controller.SortType;
import Model.Product.Product;
import graphics.ToggleSwitch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.OptionalDouble;

public class ProductsController {
    private final static String selectedColor = " #7ec7f6";
    public Label latestLabel;
    public Label visitNumberLabel;
    public Label lowestPriceLabel;
    public Label scoreLabel;
    public Label highestPriceLabel;
    public ImageView magnifier;
    public TextField searchField;
    public Pagination pagination;
    public Pane existingFilterPane;
    public Slider minPriceSlider;
    public Slider maxPriceSlider;
    public Label minPriceLabel;
    public Label maxPriceLabel;
    public Button setPriceRangeButton;

    private ArrayList<Product> showingProducts;
    private ProductsManager productsManager;

    public void initialize() {
        productsManager = new ProductsManager();
        addToggleButtonForExistingFilter();



        showProducts();
    }

    private void addToggleButtonForExistingFilter(){
        latestLabelClicked(null);
        ToggleSwitch toggleSwitch = new ToggleSwitch(50, new EventHandler<Event>() {
            boolean isOn = false;
            @Override
            public void handle(Event event) {
                isOn = !isOn;
                if (isOn){
                    productsManager.addExistenceFilter(true);
                }
                else {
                    productsManager.addExistenceFilter(false);
                }
            }
        });
        toggleSwitch.setLayoutX(200);
        toggleSwitch.setLayoutY(20);
        existingFilterPane.getChildren().add(toggleSwitch);
    }

    private void showProducts() {
        showingProducts = productsManager.showProducts();
        setSliders();
        //todo: add products?
    }

    private void setSliders(){
        int max = 0, min = 0;
        OptionalDouble minPrice = showingProducts.stream().mapToDouble(Product::getPrice).min();
        OptionalDouble maxPrice = showingProducts.stream().mapToDouble(Product::getPrice).max();
        /*if (maxPrice.isPresent()){
            max = maxPrice.getAsDouble();
        }
        if (minPrice.isPresent()){
            min = minPrice.getAsDouble();
        }*/
        min = 0;
        max = 100;
        minPriceLabel.setText(String.valueOf(min));
        maxPriceLabel.setText(String.valueOf(max));
        minPriceSlider.setMin(min);
        minPriceSlider.setMax(max);
        minPriceSlider.setValue(min);
        maxPriceSlider.setMin(min);
        maxPriceSlider.setMax(max);
        maxPriceSlider.setValue(max);
        maxPriceSlider.valueProperty().addListener((observableValue, oldValue, newValue) ->{
            maxPriceLabel.setText(String.format("%.1f", (double)newValue));
            if ((Double)newValue < minPriceSlider.getValue()){
                minPriceSlider.setValue((Double) newValue);
            }
        });
        minPriceSlider.valueProperty().addListener((observableValue, oldValue, newValue) ->{
            minPriceLabel.setText(String.format("%.1f", (double)newValue));
            if ((Double)newValue > maxPriceSlider.getValue()){
                maxPriceSlider.setValue((Double) newValue);
            }
        });
        setPriceRangeButton.setOnAction(e -> {
            productsManager.addMinimumPriceFilter((int) minPriceSlider.getValue());
            productsManager.addMaximumPriceFilter((int) maxPriceSlider.getValue() + 1);
        });
    }

    private void mouseEntered(Label label){
        label.setOpacity(0.5);
    }

    private void mouseExited(Label label){
        label.setOpacity(1);
    }

    public void latestLabelClicked(MouseEvent mouseEvent) {
        if (!productsManager.getCurrentSortType().equals(SortType.SORT_BY_TIME)) {
            productsManager.useSortByTime();
            clearBackgroundColorOfSortLabels();
            latestLabel.setStyle("-fx-background-color : #7ec7f6");
            showProducts();
        }
    }

    public void latestLabelMouseEntered(MouseEvent mouseEvent) {
        mouseEntered(latestLabel);
    }

    public void latestLabelMouseExited(MouseEvent mouseEvent) {
        mouseExited(latestLabel);
    }

    public void visitNumberLabelClicked(MouseEvent mouseEvent) {
        if (!productsManager.getCurrentSortType().equals(SortType.SORT_BY_VISIT)) {
            productsManager.useSortByVisit();
            clearBackgroundColorOfSortLabels();
            visitNumberLabel.setStyle("-fx-background-color : #7ec7f6");
            showProducts();
        }
    }

    public void visitNumberLabelEntered(MouseEvent mouseEvent) {
        mouseEntered(visitNumberLabel);
    }

    public void visitNumberLabelExited(MouseEvent mouseEvent) {
        mouseExited(visitNumberLabel);
    }

    public void lowestPriceLabelClicked(MouseEvent mouseEvent) {
        if (!productsManager.getCurrentSortType().equals(SortType.SORT_BY_LOWEST_PRICE)) {
            productsManager.useSortByLowestPrice();
            clearBackgroundColorOfSortLabels();
            lowestPriceLabel.setStyle("-fx-background-color : #7ec7f6");
            showProducts();
        }
    }

    public void lowestPriceLabelMouseEntered(MouseEvent mouseEvent) {
        mouseEntered(lowestPriceLabel);
    }

    public void lowestPriceLabelMouseExited(MouseEvent mouseEvent) {
        mouseExited(lowestPriceLabel);
    }

    public void scoreLabelClicked(MouseEvent mouseEvent) {
        if (!productsManager.getCurrentSortType().equals(SortType.SORT_BY_SCORE)) {
            productsManager.useSortByScore();
            clearBackgroundColorOfSortLabels();
            scoreLabel.setStyle("-fx-background-color : #7ec7f6");
            showProducts();
        }
    }

    public void scoreLabelMouseEntered(MouseEvent mouseEvent) {
        mouseEntered(scoreLabel);
    }

    public void scoreLabelMouseExited(MouseEvent mouseEvent) {
        mouseExited(scoreLabel);
    }

    public void highestPriceLabelClicked(MouseEvent mouseEvent) {
        if (!productsManager.getCurrentSortType().equals(SortType.SORT_BY_HIGHEST_PRICE)) {
            productsManager.useSortByHighestPrice();
            clearBackgroundColorOfSortLabels();
            highestPriceLabel.setStyle("-fx-background-color : #7ec7f6");
            showProducts();
        }
    }

    public void highestPriceLabelMouseEntered(MouseEvent mouseEvent) {
        mouseEntered(highestPriceLabel);
    }

    public void highestPriceLabelMouseExited(MouseEvent mouseEvent) {
        mouseExited(highestPriceLabel);
    }

    private void clearBackgroundColorOfSortLabels(){
        latestLabel.setStyle("-fx-border-color :  #c5c5c5; -fx-border-radius :  5");
        scoreLabel.setStyle("-fx-border-color :  #c5c5c5; -fx-border-radius :  5");
        lowestPriceLabel.setStyle("-fx-border-color :  #c5c5c5; -fx-border-radius :  5");
        visitNumberLabel.setStyle("-fx-border-color :  #c5c5c5; -fx-border-radius :  5");
        highestPriceLabel.setStyle("-fx-border-color :  #c5c5c5; -fx-border-radius :  5");
    }

    public void magnifierClicked(MouseEvent mouseEvent) {
        if (searchField.getText().isBlank()) {
            productsManager.addNameFiltering(searchField.getText());
        }
    }

    public void magnifierMouseEntered(MouseEvent mouseEvent) {
        magnifier.setOpacity(0.25);
    }

    public void magnifierMouseExited(MouseEvent mouseEvent) {
        magnifier.setOpacity(1);
    }
}
