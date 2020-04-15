package View.AdminMenus;

import Controller.AdminProfileManager;
import Model.Product.Category;
import View.Menu;

import java.util.ArrayList;

public class ManageCategoriesMenu extends Menu {
    private AdminProfileManager adminProfileManager;

    public ManageCategoriesMenu(Menu parentMenu, AdminProfileManager adminProfileManager) {
        super("Manage Categories Menu", parentMenu);
        this.adminProfileManager = adminProfileManager;
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getEditCategoryMenu());
        submenus.add(getAddCategoryMenu());
        submenus.add(getRemoveCategoryMenu());
    }

    @Override
    public void show() {
        ArrayList<Category> allCategories = adminProfileManager.getAllCategories();
        for (Category category : allCategories) {
            System.out.println(category.getName());
        }
        super.show();
    }

    private Menu getEditCategoryMenu() {
        return new Menu("Edit Category", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter category name to edit or (Back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String categoryName = input;
                    String changeField = scanner.nextLine();
                    adminProfileManager.editCategory(categoryName, changeField);
                    this.show();
                    this.execute();
                }
            }
        };
    }

    private Menu getAddCategoryMenu() {
        return new Menu("Add Category", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter category name to add or (Back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String categoryName = input;
                    System.out.println("Enter special properties:");
                    String specialProperties = scanner.nextLine();
                    adminProfileManager.addCategory(categoryName, specialProperties);
                    this.show();
                    this.execute();
                }
            }
        };
    }

    private Menu getRemoveCategoryMenu() {
        return new Menu("Remove Category", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter category name to remove or (Back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String categoryName = input;
                    adminProfileManager.removeCategory(categoryName);
                    this.show();
                    this.execute();
                }
            }
        };
    }
}
