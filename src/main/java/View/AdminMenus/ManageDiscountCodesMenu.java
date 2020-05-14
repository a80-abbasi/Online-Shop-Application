package View.AdminMenus;

import Controller.AdminProfileManager;
import Model.Account.Discount;
import View.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class ManageDiscountCodesMenu extends Menu {
    private AdminProfileManager adminProfileManager;

    public ManageDiscountCodesMenu(Menu parentMenu, AdminProfileManager adminProfileManager) {
        super("View Discount Codes Menu", parentMenu);
        this.adminProfileManager = adminProfileManager;
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getViewDiscountCodeMenu());
        submenus.add(getEditDiscountCodeMenu());
        submenus.add(getRemoveDiscountCode());
    }

    @Override
    public void show() {
        ArrayList<Discount> allDiscounts = adminProfileManager.getAllDiscountCodes();
        for (Discount discount : allDiscounts) {
            System.out.println(discount.toString());
        }
        super.show();
    }

    private Menu getViewDiscountCodeMenu() {
        return new Menu("View Discount Code", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter discount code to view the discount or (Back) to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    try {
                        String discountProperties = adminProfileManager.viewDiscount(input);
                        System.out.println(discountProperties);
                    }
                    catch (NullPointerException e) {
                        System.out.println("There is no discount with this code.");
                    }
                    this.show();
                    this.execute();
                }
            }
        };
    }

    //todo: make a new Menu for editing discount codes
    private Menu getEditDiscountCodeMenu() {
        return new Menu("Edit Discount Code", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter discount code to edit the discount or (Back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String discountCode = input;
                    String fieldName, fieldChange;
                    HashMap<String, String> discountEdition = new HashMap<>();
                    System.out.println("Enter the field you want to change, or (back) to finish editing:");
                    fieldName = scanner.nextLine();
                    while (!(fieldName.equalsIgnoreCase("back"))){
                        System.out.println("Enter the change you want to make");
                        fieldChange = scanner.nextLine();
                        discountEdition.put(fieldName, fieldChange);
                        System.out.println("Enter the field you want to change, or (back) to finish editing:");
                        fieldName = scanner.nextLine();
                    }
                    try{
                        adminProfileManager.editDiscount(discountCode, discountEdition);
                        System.out.println("Discount with code " + discountCode + " edited successfully.");
                    }
                    catch (NullPointerException e) {
                        System.out.println("There is no discount with this code.");
                    }
                    this.show();
                    this.execute();
                }
            }
        };
    }

    private Menu getRemoveDiscountCode() {
        return new Menu("Remove Discount Code", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter discount code to remove or (Back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    try {
                        adminProfileManager.removeDiscount(input);
                        System.out.println("Discount with code " + input + " removed successfully.");
                    }
                    catch (NullPointerException e) {
                        System.out.println("There is no discount with this code.");
                    }
                    this.show();
                    this.execute();
                }
            }
        };
    }
}
