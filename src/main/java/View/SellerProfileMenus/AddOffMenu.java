package View.SellerProfileMenus;

import Controller.SellerProfileManager;
import Model.Request.AddOffRequest;
import View.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
                Date offStartTime = getDate();
                if (offStartTime == null){
                    execute();
                }
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
                Date offEndTime = getDate();
                if (offEndTime == null){
                    execute();
                }
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

    public static Date getDate(){
        int year, month, day;
        try {
            System.out.println("Enter year");
            year = scanner.nextInt();
            System.out.println("Enter month");
            month = scanner.nextInt();
            if (month > 12 || month < 1){
                System.out.println("Please enter a number between 1-12 for month");
                return null;
            }
            System.out.println("Enter day");
            day = scanner.nextInt();
            if (day > 31 || day < 1){
                System.out.println("Please enter a number between 1-31 for day");
                return null;
            }
            return java.sql.Date.valueOf(LocalDate.of(year, month, day));
        } catch (Exception e) {
            System.out.println("Wrong format of Date. Please try again later");
            return null;
        }
    }
}
