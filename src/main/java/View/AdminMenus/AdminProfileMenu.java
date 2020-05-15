package View.AdminMenus;

import Controller.AdminProfileManager;
import Model.Account.Admin;
import View.Menu;
import View.ViewPersonalInfoMenu;

import java.util.ArrayList;

public class AdminProfileMenu extends Menu {
    protected Admin admin;
    private AdminProfileManager adminProfileManager;

    public AdminProfileMenu(Menu parentMenu, Admin admin) {
        super("Admin Profile Menu", parentMenu);
        this.admin = admin;
        this.adminProfileManager = new AdminProfileManager(admin);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(new ViewPersonalInfoMenu(this, adminProfileManager));
        subMenus.add(new ManageUsersMenu(this, adminProfileManager));
        subMenus.add(new ManageAllProductsMenu(this, adminProfileManager));
        subMenus.add(getCreateDiscountCodesMenu());
        subMenus.add(new ManageDiscountCodesMenu(this, adminProfileManager));
        subMenus.add(new ManageRequestsMenu(this, adminProfileManager));
        subMenus.add(new ManageCategoriesMenu(this, adminProfileManager));
        this.setSubMenus(subMenus);
    }

    public Menu getCreateDiscountCodesMenu() {
        return new Menu("Create Discount Codes", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter Following Data:");
            }

            @Override
            public void execute() {
                System.out.println("Enter the discount code:");
                String discountCode = scanner.nextLine();
                System.out.println("Enter start time:");
                String startTime = scanner.nextLine();
                System.out.println("Enter end time:");
                String endTime = scanner.nextLine();
                System.out.println("Enter discountPercent:");
                int discountPercent = scanner.nextInt();
                System.out.println("Enter maximum possible discount:");
                int maxPossibleDiscount = scanner.nextInt();
                System.out.println("Enter number of times each customer can use discount:");
                int discountPerCustomer = scanner.nextInt();
                adminProfileManager.createDiscountCode(discountCode, startTime, endTime, discountPercent, maxPossibleDiscount, discountPerCustomer);
                System.out.println("Enter (Back) to return or (Create Discount Code) to create another discount code");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    this.show();
                    this.execute();
                }
            }
        };
    }
}
