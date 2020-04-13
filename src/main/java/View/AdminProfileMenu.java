package View;

import Controller.AdminProfileManager;
import Model.Account.Admin;

import java.util.ArrayList;

public class AdminProfileMenu extends Menu{
    private static ArrayList<AdminProfileMenu> allAdminProfiles;
    private Admin admin;
    private AdminProfileManager adminProfileManager;

    {
        allAdminProfiles = new ArrayList<>();
    }

    public AdminProfileMenu(Admin admin) {
        super("Admin Profile Menu", null);
        this.admin = admin;
        this.adminProfileManager = new AdminProfileManager(admin);
        allAdminProfiles.add(this);
    }

}
