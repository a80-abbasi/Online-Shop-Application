package View.AdminMenus;

import Model.Account.Admin;
import View.Menu;

public class ManageUsersMenu extends Menu {
    private Admin admin;

    public ManageUsersMenu(Menu parentMenu, Admin admin) {
        super("Manage Users Menu", parentMenu);

        this.admin = admin;
    }

}
