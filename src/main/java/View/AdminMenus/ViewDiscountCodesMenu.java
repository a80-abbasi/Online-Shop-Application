package View.AdminMenus;

import Controller.AdminProfileManager;
import Model.Account.Discount;
import View.Menu;

import java.util.ArrayList;

public class ViewDiscountCodesMenu extends Menu {
    private AdminProfileManager adminProfileManager;

    public ViewDiscountCodesMenu(Menu parentMenu, AdminProfileManager adminProfileManager) {
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
                    adminProfileManager.viewDiscount(input);
                    this.show();
                    this.execute();
                }
            }
        };
    }

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
                    adminProfileManager.editDiscount(input);
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
                    adminProfileManager.removeDiscount(input);
                    this.show();
                    this.execute();
                }
            }
        };
    }
}
