package View.SellerProfileMenus;

import Controller.SellerProfileManager;
import View.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewOffsMenu extends Menu {
    private SellerProfileManager sellerProfileManager;

    public ViewOffsMenu(Menu parentMenu, SellerProfileManager sellerProfileManager) {
        super("View Offs Menu", parentMenu);
        this.sellerProfileManager = sellerProfileManager;
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getViewMenu());
        subMenus.add(new EditOffMenu(this, sellerProfileManager));
        subMenus.add(new AddOffMenu(this, sellerProfileManager));
        this.setSubMenus(subMenus);
    }

    @Override
    public void show() {
        System.out.println(sellerProfileManager.getOffsAmountAndID());
        super.show();
    }

    public Menu getViewMenu() {
        return new Menu("View Off Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter offId to view or (back) to return or (Logout) to leave your account:");
            }

            @Override
            public void execute() {
                show();
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Back")) {
                    this.parentMenu.execute();
                } else if (input.equals("Logout")) {
                    loginAndRegisterManager.logoutUser();
                } else {
                    //todo: check is input valid
                    System.out.println(SellerProfileManager.getOffByID(input));
                    this.execute();
                }
            }
        };
    }
}
