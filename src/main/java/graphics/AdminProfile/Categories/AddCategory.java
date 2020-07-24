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
    public TextField subCategoryNameField;
    public TextField subCategoryFeaturesField;
    public ListView<String> subCategoryFeaturesList;
    public Button confirmButton;
    public TextField subCategoriesField;

    private static Category category;
    public ImageView backImage;
    public ImageView mainMenuImage;

    private ArrayList<String> specialFeatures;
    private ArrayList<Category> subCategories;
    private ArrayList<String> subCategorySpecialFeatures;

    private static String parentMenu = "AdminProfileMenu";

    private AdminProfileManager adminProfileManager;

    public void initialize() {
        this.adminProfileManager = new AdminProfileManager((Admin) Account.getLoggedInAccount());
        specialFeatures = new ArrayList<>();
        subCategories = new ArrayList<>();
        subCategorySpecialFeatures = new ArrayList<>();
        if (category == null) {
            specialFeaturesList.getItems().clear();
            subCategoryFeaturesList.getItems().clear();
            categoryNameField.setText("");
            subCategoriesField.setText("");
            subCategoriesField.setText("");
        } /*else {
            turnToAddSubCategoryMode();
        }*/

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

    /*public void addSubCategoryFeature(MouseEvent mouseEvent) {
        String subCategorySpecialFeature = subCategoryFeaturesField.getText();
        if (subCategorySpecialFeature.isEmpty() || subCategorySpecialFeature.trim().isEmpty()) {
            return;
        }
        subCategorySpecialFeatures.add(subCategorySpecialFeature);
        subCategoryFeaturesField.setText("");
        subCategoryFeaturesList.getItems().add(subCategorySpecialFeature);
    }*/

    public void confirm(MouseEvent mouseEvent) {
        if (category == null) {
            String categoryName = categoryNameField.getText();
            try {
                category = adminProfileManager.addAndGetCategory(categoryName, specialFeatures);
                AlertBox.showMessage("Add Category", categoryName + " Category Added Successfully" + "\n" + "You can now add SubCategories");
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
        /*else {
            AlertBox.showMessage("Add Categories", "Category and its SubCategories Added Successfully");
            category = null;
            try {
                App.setRoot(parentMenu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    /*private void turnToAddSubCategoryMode() {
        subCategoryNameField.setEditable(true);
        subCategoryFeaturesField.setEditable(true);
        categoryNameField.setEditable(false);
        specialFeaturesField.setEditable(false);
        categoryNameField.setText(category.getName());
        for (String specialFeature : category.getSpecialFeatures()) {
            if (!(specialFeaturesList.getItems().contains(specialFeature))) {
                specialFeaturesList.getItems().add(specialFeature);
            }
        }
    }*/

    /*public void addSubCategory(MouseEvent mouseEvent) {
        String subCategoryName = subCategoryNameField.getText();
        try {
            Category subCategory = adminProfileManager.addAndGetSubCategory(subCategoryName, category, subCategorySpecialFeatures);
            AlertBox.showMessage("Add SubCategory", subCategoryName + " SubCategory Successfully Added");
            subCategoriesField.setText(subCategoriesField.getText() + subCategoryName + ", ");
            subCategories.add(subCategory);

            subCategorySpecialFeatures.clear();
            subCategoryNameField.setText("");
            subCategoryFeaturesList.getItems().clear();
        } catch (Exception e) {
            AlertBox.showMessage("Failed To Add SubCategory", e.getMessage());
        }
    }*/

    public static void setCategory(Category category) {
        AddCategory.category = category;
    }
}
