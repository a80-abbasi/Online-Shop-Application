package graphics.AdminProfile.Categories;

import Controller.AdminProfileManager;
import Model.Account.Account;
import Model.Account.Admin;
import Model.Product.Category;
import graphics.AlertBox;
import graphics.App;
import graphics.products.ProductPageController;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class AddCategory {
    public TextField categoryNameField;
    public TextField specialFeaturesField;
    public ListView<String> specialFeaturesList;
    public Button confirmButton;

    private static Category category;
    public ImageView backImage;
    public ImageView mainMenuImage;

    private ArrayList<String> specialFeatures;

    private static String parentMenu = "AdminProfileMenu";

    private AdminProfileManager adminProfileManager;

    public void initialize() {
        this.adminProfileManager = new AdminProfileManager((Admin) Account.getLoggedInAccount());
        specialFeatures = new ArrayList<>();
        if (category == null) {
            specialFeaturesList.getItems().clear();
            categoryNameField.setText("");
        }

        App.setBackButton(backImage, parentMenu);
        ProductPageController.setMainMenuButton(mainMenuImage);
    }

    public void addFeature(MouseEvent mouseEvent) {
        String specialFeature = specialFeaturesField.getText();
        if (specialFeature.isEmpty() || specialFeature.trim().isEmpty()) {
            return;
        }
        specialFeatures.add(specialFeature);
        specialFeaturesField.setText("");
        specialFeaturesList.getItems().add(specialFeature);
    }

    public void confirm(MouseEvent mouseEvent) {
        if (category == null) {
            String categoryName = categoryNameField.getText();
            try {
                category = adminProfileManager.addAndGetCategory(categoryName, specialFeatures);
                AlertBox.showMessage("Add Category", categoryName + " Category Added Successfully");
                category = null;
                try {
                    App.setRoot(parentMenu);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                AlertBox.showMessage("Failed To Add Category", e.getMessage());
            }
        }
    }

    public static void setCategory(Category category) {
        AddCategory.category = category;
    }
}
