package View.AdminMenus;

import Controller.AdminProfileManager;
import View.Menu;

import java.util.ArrayList;

public class ManageRequestsMenu extends Menu {
    private AdminProfileManager adminProfileManager;

    public ManageRequestsMenu(Menu parentMenu, AdminProfileManager adminProfileManager) {
        super("Manage Requests Menu", parentMenu);
        this.adminProfileManager = adminProfileManager;
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getDetailsOfRequestMenu());
        subMenus.add(getAcceptRequestMenu());
        subMenus.add(getDeclineRequestMenu());
        this.setSubMenus(subMenus);
    }

    @Override
    public void show() {
        ArrayList<String> allRequests = adminProfileManager.getAllRequests();
        for (String request : allRequests) {
            System.out.println(request);
        }
        super.show();
    }

    private Menu getDetailsOfRequestMenu() {
        return new Menu("Details of request", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter requestId to show details or (back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String requestId = input;
                    String requestDetails = adminProfileManager.getDetailsOfRequest(requestId);
                    System.out.println(requestDetails);
                    this.show();
                    this.execute();
                }
            }
        };
    }

    private Menu getAcceptRequestMenu() {
        return new Menu("Accept Request", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter requestId to accept or (back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String requestId = input;
                    adminProfileManager.acceptRequest(requestId);
                    this.show();
                    this.execute();
                }
            }
        };
    }

    private Menu getDeclineRequestMenu() {
        return new Menu("Decline Request", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter requestId to decline or (back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String requestId = input;
                    adminProfileManager.declineRequest(requestId);
                    this.show();
                    this.execute();
                }
            }
        };
    }

}
