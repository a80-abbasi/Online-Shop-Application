package View.AdminMenus;

import Controller.AdminProfileManager;
import View.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class EditDiscountCodesMenu extends Menu {
    private AdminProfileManager adminProfileManager;

    public EditDiscountCodesMenu(Menu parentMenu, AdminProfileManager adminProfileManager) {
        super("Edit Discount Code Menu", parentMenu);
        this.adminProfileManager = adminProfileManager;
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getEditDiscountCode());
        submenus.add(getEditStartTime());
        submenus.add(getEditEndTime());
        submenus.add(getEditDiscountPercent());
        submenus.add(getEditMaxPossibleDiscount());
        submenus.add(getEditDiscountPerCustomer());
        this.setSubMenus(submenus);
    }

    private Menu getEditDiscountCode() {
        return new Menu("Edit Discount Code", this) {
            @Override
            public void execute() {
                System.out.println("Enter the code of discount you want to change:");
                String discountCode = scanner.nextLine();
                System.out.println("Enter new Discount Code:");
                String newDiscountCode = scanner.nextLine();
                try {
                    adminProfileManager.editDiscountCode(discountCode, newDiscountCode);
                    System.out.println("Discount code " + discountCode + " successfully changed to " + newDiscountCode);
                }
                catch (NullPointerException n) {
                    System.out.println("There is no discount with previous code.");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("There is discount with this code.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getEditStartTime() {
        return new Menu("Edit Start Time", this) {
            @Override
            public void execute() {
                System.out.println("Enter the code of discount you want to change:");
                String discountCode = scanner.nextLine();
                System.out.println("Enter new Start Time");
                String startTime = scanner.nextLine();
                try {
                    adminProfileManager.editDiscountStartTime(discountCode, startTime);
                    System.out.println("Discount Start Time successfully changed to " + startTime);
                }
                catch (NullPointerException n) {
                    System.out.println("There is no discount with this code.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getEditEndTime() {
        return new Menu("Edit End Time", this) {
            @Override
            public void execute() {
                System.out.println("Enter the code of discount you want to change:");
                String discountCode = scanner.nextLine();
                System.out.println("Enter new End Time");
                String endTime = scanner.nextLine();
                try {
                    adminProfileManager.editDiscountEndTime(discountCode, endTime);
                    System.out.println("Discount end time successfully changed to " + endTime);
                }
                catch (NullPointerException n) {
                    System.out.println("There is no discount with this code.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getEditDiscountPercent() {
        return new Menu("Edit Discount Percent", this) {
            @Override
            public void execute() {
                System.out.println("Enter the code of discount you want to change:");
                String discountCode = scanner.nextLine();
                System.out.println("Enter new Discount Percent:");
                String discountPercent = scanner.nextLine();
                try {
                    adminProfileManager.editDiscountPercent(discountCode, discountPercent);
                    System.out.println("Discount percent successfully changed to " + discountPercent);
                }
                catch (NullPointerException n) {
                    System.out.println("There is no discount with this code.");
                }
                catch (IllegalArgumentException i) {
                    System.out.println("You must enter a 2 digit number.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getEditMaxPossibleDiscount() {
        return new Menu("Edit Maximum Possible Discount", this) {
            @Override
            public void execute() {
                System.out.println("Enter the code of discount you want to change:");
                String discountCode = scanner.nextLine();
                System.out.println("Enter new maximum possible discount");
                String maxPossibleDiscount = scanner.nextLine();
                try {
                    adminProfileManager.editDiscountMaxPossibleDiscount(discountCode, maxPossibleDiscount);
                    System.out.println("Discount maximum Possible discount successfully changed to " + maxPossibleDiscount);
                }
                catch (NullPointerException n) {
                    System.out.println("There is no discount with this code.");
                }
                catch (InputMismatchException i) {
                    System.out.println("You must enter a number.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getEditDiscountPerCustomer() {
        return new Menu("Edit Number Of Customers That Can Use This Discount", this) {
            @Override
            public void execute() {
                System.out.println("Enter the code of discount you want to change:");
                String discountCode = scanner.nextLine();
                System.out.println("Enter new number of customers that can use this discount:");
                String discountPerCustomer = scanner.nextLine();
                try {
                    adminProfileManager.editDiscountPerCustomer(discountCode, discountPerCustomer);
                }
                catch (NullPointerException n) {
                    System.out.println("There is no discount with this code.");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("You must enter an integer number.");
                }
                this.parentMenu.execute();
            }
        };
    }


}