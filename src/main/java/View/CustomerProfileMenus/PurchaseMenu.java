package View.CustomerProfileMenus;

import Controller.CustomerProfileManager;
import Controller.SellerProfileManager;
import View.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class PurchaseMenu extends Menu {
    public PurchaseMenu(Menu parentMenu) {
        super("Purchase Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(new ReceiveInformationMenu(this));
        setSubMenus(subMenus);
    }

        @Override
        public void execute() {

        }

}

