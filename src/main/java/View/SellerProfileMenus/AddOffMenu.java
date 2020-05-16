package View.SellerProfileMenus;

import Controller.SellerProfileManager;
import Model.Request.AddOffRequest;
import View.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class AddOffMenu extends Menu {
    private SellerProfileManager sellerProfileManager;
    private AddOffRequest addOffRequest;

    public AddOffMenu(Menu parentMenu, SellerProfileManager sellerProfileManager) {
        super("Add Off Menu", parentMenu);
        this.sellerProfileManager = sellerProfileManager;
        this.addOffRequest = sellerProfileManager.addOffRequest();
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getAddOffID());
        submenus.add(getAddOffStartTime());
        submenus.add(getAddOffEndTime());
        submenus.add(getAddOffAmount());
        this.setSubMenus(submenus);
    }

    private Menu getAddOffID() {
        return new Menu("Add Off ID", this) {
            @Override
            public void execute() {
                System.out.println("Enter Off ID:");
                String offID = scanner.nextLine();
                try {
                    sellerProfileManager.addOffId(addOffRequest, offID);
                    System.out.println("Off ID " + offID + " successfully added to your request.");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("There is already an Off with this ID.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getAddOffStartTime() {
        return new Menu("Add Off Start Time", this) {
            @Override
            public void execute() {
                System.out.println("Enter Off Start Time:");
                String offStartTime = scanner.nextLine();
                sellerProfileManager.addOffStartTime(addOffRequest, offStartTime);
                System.out.println("Off Start Time " + offStartTime + " successfully added to your request.");
                this.parentMenu.execute();
            }
        };
    }

    private Menu getAddOffEndTime() {
        return new Menu("Add Off End Time", this) {
            @Override
            public void execute() {
                System.out.println("Enter Off End Time:");
                String offEndTime = scanner.nextLine();
                sellerProfileManager.addOffEndTime(addOffRequest, offEndTime);
                System.out.println("Off End Time " + offEndTime + " successfully added to your request.");
                this.parentMenu.execute();
            }
        };
    }

    private Menu getAddOffAmount() {
        return new Menu("Add Off Amount", this) {
            @Override
            public void execute() {
                System.out.println("Enter Off Amount:");
                String offAmount = scanner.nextLine();
                try {
                    sellerProfileManager.addOffAmount(addOffRequest, offAmount);
                    System.out.println("Off Amount " + offAmount + " successfully added to your request.");
                }
                catch (InputMismatchException e) {
                    System.out.println("You should enter an integer number.");
                }
                this.parentMenu.execute();
            }
        };
    }
}
