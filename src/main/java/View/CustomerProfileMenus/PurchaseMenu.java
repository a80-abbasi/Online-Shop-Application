package View.CustomerProfileMenus;

import View.Menu;

import java.util.ArrayList;

public class PurchaseMenu extends Menu {
    public PurchaseMenu(Menu parentMenu) {
        super("Purchase Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(new ReceiveInformationMenu(this));
        setSubMenus(subMenus);
    }
}
