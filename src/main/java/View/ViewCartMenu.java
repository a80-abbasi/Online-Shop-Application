package View;

import com.sun.java.swing.action.ViewMenu;

import java.util.ArrayList;

public class ViewCartMenu extends Menu{
    public ViewCartMenu(Menu parentMenu) {
        super("View Cart Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getShowProductsMenu());
        subMenus.add(viewMenu());
        subMenus.add(getIncreaseMenu());
        subMenus.add(getDecreaseMenu());
        subMenus.add(getShowTotalPriceMenu());
        subMenus.add(new PurchaseMenu(this));
        this.submenus = subMenus;
    }
    private Menu viewMenu() {
        return null;
    }
    private Menu getShowProductsMenu() {
        return null;
    }
    private Menu getIncreaseMenu() {
        return null;
    }
    private Menu getDecreaseMenu() {
        return null;
    }
    private Menu getShowTotalPriceMenu() {
        return null;
    }




}
