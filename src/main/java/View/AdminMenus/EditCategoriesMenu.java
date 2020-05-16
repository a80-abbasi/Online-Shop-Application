package View.AdminMenus;

import Controller.AdminProfileManager;
import View.Menu;

import java.util.ArrayList;

public class EditCategoriesMenu extends Menu {
    private AdminProfileManager adminProfileManager;

    public EditCategoriesMenu(Menu parentMenu, AdminProfileManager adminProfileManager) {
        super("Edit Category Menu", parentMenu);
        this.adminProfileManager = adminProfileManager;
        ArrayList<Menu> submenus = new ArrayList<>();

    }

}
