package View.SellerProfileMenus;

import Controller.SellerProfileManager;
import View.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class ManageProductsMenu extends Menu {
    private SellerProfileManager sellerProfileManager;

    public ManageProductsMenu(Menu parentMenu, SellerProfileManager sellerProfileManager) {
        super("Manage Products Menu", parentMenu);
        this.sellerProfileManager = sellerProfileManager;
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getViewMenu());
        subMenus.add(getViewBuyersMenu());
        subMenus.add(new EditProductMenu(this, sellerProfileManager));
        this.setSubMenus(subMenus);
    }

    @Override
    public void show() {
        System.out.println(sellerProfileManager.getSellerProductsNameAndID());
        super.show();
    }

    public Menu getViewMenu() {
        return new Menu("View Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter ProductId to view or (back) to return or (Logout) to leave your account:");
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
                    System.out.println(sellerProfileManager.getProductByID(input));
                    this.execute();
                }
            }
        };
    }

    public Menu getViewBuyersMenu() {
        return new Menu("View Buyers Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter ProductId to view buyers or (back) to return or (Logout) to leave your account:");
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
                    System.out.println(sellerProfileManager.getProductBuyers(input));
                    this.execute();
                }
            }
        };
    }
}
