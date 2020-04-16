package View.CustomerProfileMenus;

import View.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class ReceiveInformationMenu extends Menu {

    public ReceiveInformationMenu(Menu parentMenu) {
        super("Receive Information Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(new DiscountCodeMenu(this));
        setSubMenus(subMenus);
    }


    @Override
    public void execute() {
        super.execute();
    }
}
