package View.SellerProfileMenus;

import Controller.SellerProfileManager;
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
        this.setSubMenus(subMenus);
    }

    @Override
    public void show() {
        System.out.println(sellerProfileManager.getOffsAmountAndID());
        super.show();
    }

    public Menu getViewMenu() {
        return new Menu("View Off Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter offId to view or (back) to return or (Logout) to leave your account:");

            }

            @Override
            public void execute() {
                show();
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Back")) {
                    this.parentMenu.execute();
                } else if (input.equals("Logout")) {
                    loginAndRegisterManager.logoutUser();
                } else {
                    //todo: check is input valid
                    System.out.println(SellerProfileManager.getOffByID(input));
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
                System.out.println("Enter OffId to edit or (Back) to return or (Logout) to leave your account:");
            }

            @Override
            public void execute() {
                show();
                HashMap<String, String> fieldChanges = new HashMap<>();
                String input = scanner.nextLine();
                String offID = input;
                System.out.println("Enter the field you want to edit or (End) to finish");
                String field = scanner.nextLine();
                while (!(field.equalsIgnoreCase("End"))) {
                    if (input.equalsIgnoreCase("Back")) {
                        this.parentMenu.execute();
                    } else if (input.equalsIgnoreCase("Logout")) {
                        loginAndRegisterManager.logoutUser();
                    } else if (SellerProfileManager.isInputInOffFields(input)) {
                        System.out.println("Enter new value for the field:");
                        String value = scanner.nextLine();
                        if (SellerProfileManager.isInputValidOffValue(value)) {
                            fieldChanges.put(field, value);
                        } else {
                            System.out.println("This value is invalid");
                            System.out.println("Enter the field you want to edit or (end) to finish");
                            field = scanner.nextLine();
                        }
                    } else {
                        System.out.println("This field doesn't exist");
                        System.out.println("Enter the field you want to edit or (end) to finish");
                        field = scanner.nextLine();
                    }
                }
                sellerProfileManager.editOffByID(offID, fieldChanges);
                System.out.println("Your request for edit this fields sent to admin please w8 for answer");
                parentMenu.execute();
            }
        };
    }

    public Menu getAddOffMenu() {
        return new Menu("Add Off Information", this) {
            @Override
            public void execute() {
                ArrayList<String> offFieldsValue = new ArrayList<>(SellerProfileManager.getOffFields());
                int pageNumber = 1;
                while (true) {
                    if (pageNumber == 1) {
                        System.out.println(this.getName() + ":");
                        System.out.println("In each Menu you can use (Back) to return, (Next) to go next page or (Logout) to leave your account!");
                    }
                    if(pageNumber <= SellerProfileManager.getOffFields().size()) {
                        System.out.println("Enter " + SellerProfileManager.getOffFields().get(pageNumber - 1) + ":");
                        String input = scanner.nextLine();
                        if (input.equals("Back")) {
                            if(pageNumber == 1) {
                                this.parentMenu.execute();
                            }
                            else {
                                pageNumber -= 1;
                            }
                        } else if(input.equals("Next")) {
                            pageNumber += 1;
                        } else if (input.equals("Logout")) {
                            loginAndRegisterManager.logoutUser();
                        } else {
                            offFieldsValue.set(pageNumber - 1, input);
                            pageNumber += 1;
                        }
                    } else if (pageNumber == SellerProfileManager.getOffFields().size() + 1) {
                        System.out.println("are this fields true?(Write (Next) if they are true)");
                        HashMap<String, String> newOffFields = new HashMap<>();
                        for (int i = 0; i < SellerProfileManager.getOffFields().size(); i++) {
                            newOffFields.put(SellerProfileManager.getOffFields().get(i), offFieldsValue.get(i));
                        }
                        System.out.println(newOffFields);
                        String input = scanner.nextLine();
                        if (input.equals("Back")) {
                            pageNumber -= 1;
                        } else if(input.equals("Next")) {
                            if (SellerProfileManager.areNewOffFieldsValueValid(newOffFields)) {
                                sellerProfileManager.addOff(newOffFields);
                                System.out.println("Your Request Sent to Admin please w8 for answer");
                                parentMenu.execute();
                            } else {
                                System.out.println("formats are invalid"); //todo: write a better message;
                            }
                        } else if (input.equals("Logout")) {
                            loginAndRegisterManager.logoutUser();
                        } else {
                            System.out.println("invalid message");
                        }
                    }
                }
            }
        };
    }

}
