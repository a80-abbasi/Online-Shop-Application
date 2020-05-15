package View;

import View.ProductsMenus.ProductsMenu;
import View.SellerProfileMenus.SellerProfileMenu;

import java.util.ArrayList;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Main Menu", null);
        ArrayList<Menu> submenus = new ArrayList<>();
        //todo: add profile menu
        submenus.add(new ProfileMenu(this));
        submenus.add(new ProductsMenu(this));
        submenus.add(new OffMenu(this));
        this.setSubMenus(submenus);
    }
}
