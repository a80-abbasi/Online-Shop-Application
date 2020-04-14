package View.AdminMenus;

import Controller.AdminProfileManager;
import Model.Account.Admin;
import View.Menu;
import View.ViewPersonalInfoMenu;

import java.util.ArrayList;

public class AdminProfileMenu extends Menu {
    private static ArrayList<AdminProfileMenu> allAdminProfiles;
    private Admin admin;
    private AdminProfileManager adminProfileManager;

    {
        allAdminProfiles = new ArrayList<>();
    }

    public AdminProfileMenu(Menu parentMenu, Admin admin) {
        super("Admin Profile Menu", parentMenu);
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(new ViewPersonalInfoMenu(admin, "admin",  this));
        submenus.add(new ManageUsersMenu(this, admin));
        submenus.add(new ManageAllProductsMenu(this));
        submenus.add(getCreateDiscountCodesMenu());
        submenus.add(new ViewDiscountCodesMenu(this));
        submenus.add(new ManageRequestsMenu(this));
        submenus.add(new ManageCategoriesMenu(this));


        this.admin = admin;
        this.adminProfileManager = new AdminProfileManager(admin);
        this.setSubmenus(submenus);
        allAdminProfiles.add(this);
    }

    public Menu getCreateDiscountCodesMenu() {
        return new Menu("Create Discount Codes", this) {
            @Override
            public void execute() {
                String input = scanner.nextLine();
                adminProfileManager.createDiscountCode();
            }
        };
    }
}
