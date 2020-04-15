package View.AdminMenus;

import Controller.AdminProfileManager;
import View.Menu;

import java.util.ArrayList;

public class ManageRequestsMenu extends Menu {
    private AdminProfileManager adminProfileManager;

    public ManageRequestsMenu(Menu parentMenu, AdminProfileManager adminProfileManager) {
        super("Manage Requests Menu", parentMenu);
        this.adminProfileManager = adminProfileManager;
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getDetailsOfRequestMenu());
        submenus.add(getAcceptRequestMenu());
        submenus.add(getDeclineRequestMenu());
    }

    @Override
    public void show() {
        String allRequests = adminProfileManager.showAllRequests();
        System.out.println(allRequests);
        super.show();
    }

    private Menu getDetailsOfRequestMenu() {
        return new Menu("Details of request", this) {
            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }

    private Menu getAcceptRequestMenu() {
        return new Menu("Accept Request", this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }

    private Menu getDeclineRequestMenu() {
        return new Menu("Decline Request", this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }

}
