package View.CustomerProfileMenus;

import View.Menu;


import java.util.ArrayList;

public class ViewCartMenu extends Menu {
    public ViewCartMenu(Menu parentMenu) {
        super("View Cart Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getShowProductsMenu());
        subMenus.add(viewMenu());
        subMenus.add(getIncreaseMenu());
        subMenus.add(getDecreaseMenu());
        subMenus.add(getShowTotalPriceMenu());
        subMenus.add(getPurchaseMenu());
        this.submenus = subMenus;
    }
    private Menu viewMenu() {
        return new Menu("View Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    private Menu getShowProductsMenu() {
        return new Menu("Show Products Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    private Menu getIncreaseMenu() {
        return new Menu("Increase Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    private Menu getDecreaseMenu() {
        return new Menu("Decrease Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    private Menu getShowTotalPriceMenu() {
        return new Menu("Show TotalPrice Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    private Menu getPurchaseMenu() {
        return new Menu("View Company Information", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }




}
