package View.AdminMenus;

import Controller.AdminProfileManager;
import Model.Account.Admin;
import View.Menu;
import View.ViewPersonalInfo;

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
        submenus.add(new ViewPersonalInfo(admin, this));
        submenus.add(new ManageUsersMenu(this, admin));
        submenus.add(new ManageAllProductsMenu(this));
        submenus.add(new ViewDiscountCodesMenu(this));
        submenus.add(new ManageRequestsMenu(this));
        submenus.add(new ManageCategoriesMenu(this));


        this.admin = admin;
        this.adminProfileManager = new AdminProfileManager(admin);
        allAdminProfiles.add(this);
    }
}
