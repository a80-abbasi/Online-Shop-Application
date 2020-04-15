package View.SellerProfileMenus;

import Controller.SellerProfileManager;
import Model.Account.Admin;
import Model.Account.Seller;
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
        subMenus.add(getEditMenu());
        this.setSubmenus(subMenus);
    }

    @Override
    public void show() {
        ArrayList<String> allProductsIds = sellerProfileManager.getSellerProducts();
        for (String productId : allProductsIds) {
            System.out.println(productId);
        }
        super.show();
    }

    public Menu getViewMenu() {
        return new Menu("View Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter productId to view or (back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String productId = input;
                    //todo: go to product Menu
                }
            }
        };
    }

    public Menu getViewBuyersMenu() {
        return new Menu("View Buyers Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter productId to view buyers or (back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String productId = input;
                    ArrayList<String> productBuyers = sellerProfileManager.getProductBuyers(productId);
                    for (String productBuyer : productBuyers) {
                        System.out.println(productBuyer);
                    }
                    this.show();
                    this.execute();
                }
            }
        };
    }

    public Menu getEditMenu() {
        return new Menu("Edit Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter productId to edit or (back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String productId = input;
                    HashMap<String, String> fieldChanges = new HashMap<>();
                    System.out.println("Enter the field you want to change or (end) to finish");
                    String field = scanner.nextLine();
                    while (!(field.equalsIgnoreCase("end"))) {
                        System.out.println("Enter new value for the field:");
                        String value = scanner.nextLine();
                        fieldChanges.put(field, value);
                        System.out.println("Enter the field you want to change or (end) to finish");
                        field = scanner.nextLine();
                    }
                    sellerProfileManager.editProductByID(productId, fieldChanges);
                    this.show();
                    this.execute();
                }
            }
        };
    }
}
