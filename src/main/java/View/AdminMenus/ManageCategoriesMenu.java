package View.AdminMenus;

import Controller.AdminProfileManager;
import Model.Product.Category;
import Model.Product.Product;
import View.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class ManageCategoriesMenu extends Menu {
    private AdminProfileManager adminProfileManager;

    public ManageCategoriesMenu(Menu parentMenu, AdminProfileManager adminProfileManager) {
        super("Manage Categories Menu", parentMenu);
        this.adminProfileManager = adminProfileManager;
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getAddCategoryMenu());
        submenus.add(getEditCategoryMenu());
        submenus.add(getRemoveCategoryMenu());
        this.setSubMenus(submenus);
    }

    @Override
    public void show() {
        ArrayList<Category> allCategories = adminProfileManager.getAllCategories();
        for (Category category : allCategories) {
            System.out.println(category.getName());
        }
        super.show();
    }

    //todo: completing add category
    private Menu getAddCategoryMenu() {
        return new Menu("Add Category", this) {
            @Override
            public void execute() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter category name to add or (Back) to return:");
                String categoryName = scanner.nextLine();
                if (AdminProfileManager.isCategoryWithThisName(categoryName)) {
                    System.out.println("There is category with this name, please choose another name.");
                }
                else if (categoryName.equalsIgnoreCase("back")) {
                    this.parentMenu.execute();
                }
                else {
                    ArrayList<String> specialFeatures = new ArrayList<>();
                    while (true) {
                        System.out.println("Enter special feature for this category or (back) to return:");
                        String specialFeature = scanner.nextLine();
                        if (specialFeature.equalsIgnoreCase("back")) {
                            break;
                        }
                        specialFeatures.add(specialFeature);
                    }
                    adminProfileManager.addCategory(categoryName, specialFeatures);
                    while (true) {
                        System.out.println("Enter the ID of Product you want to add to this category or (back) to return:");
                        String productID = scanner.nextLine();
                        if (productID.equalsIgnoreCase("back")) {
                            break;
                        }
                        else if (AdminProfileManager.isProductWithThisID(productID)) {
                            HashMap<String, Integer> productSpecialFeatures = new HashMap<>();
                            for (String specialFeature : specialFeatures) {
                                System.out.println("Enter value of feature " + specialFeature + " for product " + productID);
                                String value = scanner.nextLine();
                                if (value.matches("\\d+")) {
                                    productSpecialFeatures.put(specialFeature, Integer.parseInt(value));
                                }
                                else {
                                    System.out.println("You must enter an integer number.");
                                }
                            }
                            adminProfileManager.addProductToCategory(categoryName, productID, productSpecialFeatures);
                        }
                        else {
                            System.out.println("There is no product with this ID.");
                        }
                    }
                    System.out.println("Category " + categoryName + " successfully created.");
                }
                this.execute();
            }
        };
    }

    //todo: completing category edition
    private Menu getEditCategoryMenu() {
        return new Menu("Edit Category", this) {
            @Override
            public void execute() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter category name to edit or (Back) to return:");
                String categoryName = scanner.nextLine();
                if (AdminProfileManager.isCategoryWithThisName(categoryName)) {
                    System.out.println();
                }
                else if (categoryName.equalsIgnoreCase("back")) {
                    this.parentMenu.execute();
                }
                else {
                    System.out.println("There is no category with this name.");
                    System.out.println("Enter");
                    String changeField = scanner.nextLine();
                    try {
                        //adminProfileManager.editCategory(categoryName);
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("This category doesn't have this field.");
                    }
                    this.execute();
                }
            }
        };
    }

    private Menu getRemoveCategoryMenu() {
        return new Menu("Remove Category", this) {
            @Override
            public void execute() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter category name to remove or (Back) to return:");
                String categoryName = scanner.nextLine();
                if (categoryName.equalsIgnoreCase("back")) {
                    this.parentMenu.execute();
                }
                else {
                    try {
                        adminProfileManager.removeCategory(categoryName);
                        System.out.println(categoryName + " category removed successfully.");
                    }
                    catch (NullPointerException e) {
                        System.out.println("There is no category with this name.");
                    }
                    this.execute();
                }
            }
        };
    }
}
