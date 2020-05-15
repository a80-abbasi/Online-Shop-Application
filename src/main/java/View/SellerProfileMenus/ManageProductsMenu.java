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
        subMenus.add(getEditMenu());
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

    public Menu getEditMenu() {
        return new Menu("Edit Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter productID to edit or (Back) to return or (Logout) to leave your account:");
            }

            @Override
            public void execute() {
                show();
                HashMap<String, String> fieldChanges = new HashMap<>();
                String input = scanner.nextLine();
                String productID = input;
                System.out.println("Enter the field you want to edit or (End) to finish");
                String field = scanner.nextLine();
                while (!(field.equalsIgnoreCase("End"))) {
                    if (input.equalsIgnoreCase("Back")) {
                        this.parentMenu.execute();
                    } else if (input.equalsIgnoreCase("Logout")) {
                        loginAndRegisterManager.logoutUser();
                    } else if (SellerProfileManager.isInputInProductFields(input)) {
                        System.out.println("Enter new value for the field:");
                        String value = scanner.nextLine();
                        if (SellerProfileManager.isInputValidProductValue(value)) {
                            fieldChanges.put(field, value);
                        } else {
                            System.out.println("This value is invalid");
                            System.out.println("Enter the field you want to edit or (end) to finish");
                            field = scanner.nextLine();
                        }
                    } else {
                        System.out.println("This field doesn't exist");
                        System.out.println("Enter the field you want to edit or (end) to finish");
                        field = scanner.nextLine();
                    }
                }
                sellerProfileManager.editProductByID(productID, fieldChanges);
                System.out.println("Your request for edit this fields sent to admin please w8 for answer");
                parentMenu.execute();
            }
        };
    }
}
