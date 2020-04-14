package View.AdminMenus;

import View.Menu;

import java.util.ArrayList;

public class ManageRequestsMenu extends Menu {

    public ManageRequestsMenu(Menu parentMenu) {
        super("Manage Requests Menu", parentMenu);
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getDetailsOfRequestMenu());
        submenus.add(getAcceptRequestMenu());
        submenus.add(getDeclineRequestMenu());
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
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }

    private Menu getDeclineRequestMenu() {
        return new Menu("Decline Request", this) {
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

}
