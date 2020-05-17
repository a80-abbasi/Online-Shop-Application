package View.AdminMenus;

import Controller.AdminProfileManager;
import Model.Account.Admin;
import View.Menu;
import View.ViewPersonalInfoMenu;

import java.util.ArrayList;
import java.util.Date;

import static View.SellerProfileMenus.AddOffMenu.getDate;

public class AdminProfileMenu extends Menu {
    protected Admin admin;
    private AdminProfileManager adminProfileManager;

    public AdminProfileMenu(Admin admin, Menu parentMenu) {
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
            public void execute() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter Following Data:");
                System.out.println("Enter the discount code:");
                String discountCode = scanner.nextLine();
                System.out.println("Enter start time:");
                Date startTime = getDate(parentMenu);
                if (startTime == null){
                    execute();
                }
                System.out.println("Enter end time:");
                Date endTime = getDate(parentMenu);
                if (endTime == null){
                    execute();
                }
                System.out.println("Enter discountPercent:");
                int discountPercent = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter maximum possible discount:");
                int maxPossibleDiscount = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter number of times each customer can use discount:");
                int discountPerCustomer = Integer.parseInt(scanner.nextLine());
                adminProfileManager.createDiscountCode(discountCode, startTime, endTime, discountPercent, maxPossibleDiscount, discountPerCustomer);
                System.out.println("Enter (Back) to return or (Create Discount Code) to create another discount code");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.execute();
                }
                else {
                    this.execute();
                }
            }
        };
    }
}
