package View.CustomerProfileMenus;

import Model.Account.Customer;
import View.Menu;

import java.util.ArrayList;

public class ViewOrdersMenu extends Menu {
    public ViewOrdersMenu(Customer customer, Menu parentMenu) {
        super("View Orders Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        submenus.add(getShowOrderMenu());
        submenus.add(getRateMenu());
        this.setSubmenus(subMenus);
    }

    private Menu getShowOrderMenu() {
        return null;
        //todo
        return null;
    }

    private Menu getRateMenu() {
        return null;
        //todo
        return null;
    }

    //todo: w8 for answer in group for adding override for execute to give inputs for rate
}
