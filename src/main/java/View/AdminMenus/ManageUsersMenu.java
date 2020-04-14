package View.AdminMenus;

import Controller.AdminProfileManager;
import Model.Account.Admin;
import View.Menu;

import java.util.ArrayList;

public class ManageUsersMenu extends Menu {
    private Admin admin;
    private AdminProfileManager adminProfileManager;

    public ManageUsersMenu(Menu parentMenu, Admin admin, AdminProfileManager adminProfileManager) {
        super("Manage Users Menu", parentMenu);
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getViewUserMenu());
        submenus.add(getChangeTypeOfUserMenu());
        submenus.add(getDeleteUserMenu());
        submenus.add(getCreateManagerProfileMenu());
        this.admin = admin;
    }

    @Override
    public void show() {
        String allUserNames = adminProfileManager.viewAllUsers();
        System.out.println(allUserNames);
    }

    public Menu getViewUserMenu() {
        return new Menu("View User", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter (Username) or (Back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String userInfo = adminProfileManager.viewUser(input);
                    this.show();
                    this.execute();
                }
            }
        };
    }

    public Menu getChangeTypeOfUserMenu() {
        return new Menu("Change Type of User", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter Username :");
            }

            @Override
            public void execute() {
                String username = scanner.nextLine();
                System.out.println("Enter the role you want this user to have:");
                String role = scanner.nextLine();
                adminProfileManager.changeTypeOfAccount(username, role);
                System.out.println("Enter (Back) to return or (Change Type) to change another user's type:");
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

    public Menu getDeleteUserMenu() {
        return new Menu("Delete User", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter Username or (Back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    adminProfileManager.deleteUser(input);
                    this.show();
                    this.execute();
                }
            }
        };
    }

    public Menu getCreateManagerProfileMenu() {
        return new Menu("Create Manager Profile", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter Following data to create manager profile:");
            }

            @Override
            public void execute() {
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();
                System.out.println("Enter name:");
                String name = scanner.nextLine();
                System.out.println("Enter lastName:");
                String lastName = scanner.nextLine();
                System.out.println("Enter email:");
                String email = scanner.nextLine();
                System.out.println("Enter phone number:");
                String phoneNumber = scanner.nextLine();
                adminProfileManager.createManagerProfile(username, password, name, lastName, email, phoneNumber);
                System.out.println("Enter (Back) to return or (Create Manager Profile) to create another manager profile:");
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
