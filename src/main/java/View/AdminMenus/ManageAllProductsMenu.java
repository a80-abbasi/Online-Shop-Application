package View.AdminMenus;

import View.Menu;

import java.util.ArrayList;

public class ManageAllProductsMenu extends Menu {

    public ManageAllProductsMenu(Menu parentMenu) {
        super("Manage All Products Menu", parentMenu);
        ArrayList<Menu> submenus = new ArrayList<>();

    }

    private Menu getRemoveProductMenu() {
        return new Menu("Remove Product", this) {
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
