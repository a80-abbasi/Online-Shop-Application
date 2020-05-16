package View.AdminMenus;

import Controller.AdminProfileManager;
import Model.Account.Discount;
import View.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class ManageDiscountCodesMenu extends Menu {
    private AdminProfileManager adminProfileManager;

    public ManageDiscountCodesMenu(Menu parentMenu, AdminProfileManager adminProfileManager) {
        super("Manage Discount Codes Menu", parentMenu);
        this.adminProfileManager = adminProfileManager;
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getViewDiscountCodeMenu());
        submenus.add(new EditDiscountCodesMenu(this, adminProfileManager));
        submenus.add(getRemoveDiscountCode());
        this.setSubMenus(submenus);
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
            public void execute() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter discount code to view the discount or (Back) to return");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
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
                    this.execute();
                }
            }
        };
    }

    private Menu getRemoveDiscountCode() {
        return new Menu("Remove Discount Code", this) {
            @Override
            public void execute() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter discount code to remove or (Back) to return:");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
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
                    this.execute();
                }
            }
        };
    }
}
