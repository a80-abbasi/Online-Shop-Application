package View.AdminMenus;

import Model.Account.Admin;
import View.Menu;

import java.util.ArrayList;

public class ManageUsersMenu extends Menu {
    private Admin admin;

    public ManageUsersMenu(Menu parentMenu, Admin admin) {
        super("Manage Users Menu", parentMenu);
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getViewUserMenu());
        submenus.add(getChangeTypeOfUserMenu());
        submenus.add(getDeleteUserMenu());
        submenus.add(getCreateManagerProfileMenu());
        this.admin = admin;
    }

    public Menu getViewUserMenu() {
        return new Menu("View User", this) {
            @Override
            public void execute() {

            }
        };
    }

    public Menu getChangeTypeOfUserMenu() {
        return new Menu("Change Type of User", this) {
            @Override
            public void execute() {

            }
        };
    }

    public Menu getDeleteUserMenu() {
        return new Menu("Delete User", this) {
            @Override
            public void execute() {

            }
        };
    }

    public Menu getCreateManagerProfileMenu() {
        return new Menu("Create Manager Profile", this) {
            @Override
            public void execute() {

            }
        };
    }

}
