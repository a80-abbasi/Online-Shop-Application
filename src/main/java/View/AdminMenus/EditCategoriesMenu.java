package View.AdminMenus;

import Controller.AdminProfileManager;
import Model.Product.Category;
import View.Menu;

import java.util.ArrayList;

public class EditCategoriesMenu extends Menu {
    private AdminProfileManager adminProfileManager;
    private Category category;

    //todo: Completing this class
    public EditCategoriesMenu(Menu parentMenu, AdminProfileManager adminProfileManager) {
        super("Edit Category Menu", parentMenu);
        this.adminProfileManager = adminProfileManager;
        ArrayList<Menu> submenus = new ArrayList<>();
    }

    @Override
    public void show() {
        System.out.println("Enter the name of the category you want to edit, (back) to return or (logout) to log out:");
        String categoryName = scanner.nextLine();
        if (AdminProfileManager.isCategoryWithThisName(categoryName)) {
            this.category = Category.getCategoryByName(categoryName);
            super.show();
        }
        else if (categoryName.equalsIgnoreCase("back")) {
            parentMenu.execute();
        }
        else if (categoryName.equalsIgnoreCase("logout")) {
            //todo: add logout to this
        }
        else {
            System.out.println("There is no Category with this name.");
            this.show();
        }
    }

    private Menu getEditCategoryNameMenu() {
        return new Menu("Edit Category Name Menu", this) {
            @Override
            public void execute() {
                System.out.println("Enter new Name:");
                String newCategoryName = scanner.nextLine();
                try {
                    adminProfileManager.editCategoryName(category, newCategoryName);
                    System.out.println("Category name successfully changed to " + newCategoryName);
                }
                catch (IllegalArgumentException e) {
                    System.out.println("There is another category with this name.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getEditCategoryProductsMenu() {
        return new Menu("Edit Category Products Menu", this) {
            @Override
            public void execute() {
                System.out.println("Enter");
                this.parentMenu.execute();
            }
        };
    }

    private Menu getEditCategorySpecialFeaturesMenu() {
        return new Menu("Edit Category Special Features Menu", this) {
            @Override
            public void execute() {
                this.parentMenu.execute();
            }
        };
    }
}
