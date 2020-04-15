package View;

import View.ProductMenus.ProductMenu;
import View.ProductsMenus.ProductsMenu;

import java.util.ArrayList;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Main Menu", null);
        ArrayList<Menu> submenus = new ArrayList<>();
        //todo: add profile menu
        submenus.add(new ProductsMenu(this));
        submenus.add(new OffMenu(this));
        this.setSubmenus(submenus);
    }
}
