package View.SellerProfileMenus;

import View.Menu;

import java.util.ArrayList;

public class ManageProductsMenu extends Menu {
    public ManageProductsMenu(Menu parentMenu) {
        super("Manage Products Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getViewMenu());
        subMenus.add(getViewBuyersMenu());
        subMenus.add(getEditMenu());
        this.setSubmenus(subMenus);
    }
    public Menu getViewMenu() {
        return new Menu("View Company Information", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    public Menu getViewBuyersMenu() {
        return new Menu("View Company Information", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    public Menu getEditMenu() {
        return new Menu("View Company Information", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
}
