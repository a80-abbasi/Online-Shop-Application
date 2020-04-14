package View.AdminMenus;

import View.Menu;

import java.util.ArrayList;

public class ManageCategoriesMenu extends Menu {

    public ManageCategoriesMenu(Menu parentMenu) {
        super("Manage Categories Menu", parentMenu);
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getEditCategoryMenu());
        submenus.add(getAddCategoryMenu());
        submenus.add(getRemoveCategoryMenu());
    }

    private Menu getEditCategoryMenu() {
        return new Menu("Edit Category", this) {
            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }

    private Menu getAddCategoryMenu() {
        return new Menu("Add Category", this) {
            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }

    private Menu getRemoveCategoryMenu() {
        return new Menu("Remove Category", this) {
            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }
}
