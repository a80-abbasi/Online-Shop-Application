package View.SellerProfileMenus;

import Controller.SellerProfileManager;
import Model.Account.Off;
import View.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewOffsMenu extends Menu {
    private SellerProfileManager sellerProfileManager;

    public ViewOffsMenu(Menu parentMenu, SellerProfileManager sellerProfileManager) {
        super("View Offs Menu", parentMenu);
        this.sellerProfileManager = sellerProfileManager;
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getViewMenu());
        subMenus.add(getEditMenu());
        subMenus.add(getAddOffMenu());
        this.setSubmenus(subMenus);
    }

    @Override
    public void show() {
        ArrayList<String> allOffIds = sellerProfileManager.viewOffs();
        for (String offId : allOffIds) {
            System.out.println(offId);
        }
        super.show();
    }

    public Menu getViewMenu() {
        return new Menu("View Off Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter offId to view or (back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String offId = input;
                    sellerProfileManager.getOffStatus(offId);
                    this.show();
                    this.execute();
                }
            }
        };
    }

    public Menu getEditMenu() {
        return new Menu("Edit Off Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter OffId to edit or (back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String offId = input;
                    HashMap<String, String> fieldChanges = new HashMap<>();
                    System.out.println("Enter the field you want to change or (end) to finish");
                    String field = scanner.nextLine();
                    while (!(field.equalsIgnoreCase("end"))) {
                        System.out.println("Enter new value for the field:");
                        String value = scanner.nextLine();
                        fieldChanges.put(field, value);
                        System.out.println("Enter the field you want to change or (end) to finish");
                        field = scanner.nextLine();
                    }
                    sellerProfileManager.editOff(offId, fieldChanges);
                }
            }
        };
    }

    public Menu getAddOffMenu() {
        return new Menu("Add Off Information", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter (add) to add a new off or (back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else if (input.equalsIgnoreCase("add")) {
                    ArrayList<String> propertiesInOrder = new ArrayList<>();
                    //todo: completing this
                }
            }
        };
    }

}
