package View.CustomerProfileMenus;

import Controller.CustomerProfileManager;
import Controller.ProductsManager;
import Model.Account.Customer;
import View.Menu;

import java.util.ArrayList;

public class ViewOrdersMenu extends Menu {
    private CustomerProfileManager customerProfileManager;
    public ViewOrdersMenu(Customer customer, Menu parentMenu) {
        super("View Orders Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getShowOrderMenu());
        subMenus.add(getRateMenu());
        this.setSubMenus(subMenus);
    }

    @Override
    public void show() {
        System.out.println(customerProfileManager.showOrdersSellerNameAndDate()); // todo: wrong input if (null)
        super.show();
    }

    private Menu getShowOrderMenu() {
        return new Menu("Show Order Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter orderID to view order:");
            }

            @Override
            public void execute() {
                show();
                while (true) {
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("Back")) {
                        this.parentMenu.execute();
                    } else if (input.equalsIgnoreCase("Logout")) {
                        loginAndRegisterManager.logoutUser();
                    } else if (input.matches("\\A\\d+\\z")) {
                        if (customerProfileManager.isInputValidForBuyLogID(input)) { //todo: wrong input if (null)
                            System.out.println(customerProfileManager.showOrder(input));
                            parentMenu.execute();
                        } else {
                            System.out.println("There is no order with this ID");
                        }
                    } else {
                        System.out.println("Please enter valid number:");
                    }
                }
            }
        };
    }

    private Menu getRateMenu() {
        return new Menu("Get Rate Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter productID:");
            }

            @Override
            public void execute() {
                show();
                while (true) {
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("Back")) {
                        this.parentMenu.execute();
                    } else if (input.equals("Logout")) {
                        loginAndRegisterManager.logoutUser();
                    } else if (input.matches("\\A\\d+\\z")) {
                        if (ProductsManager.isValidInputForProductID(input)) {
                            System.out.println("Enter rate in range (1-5):");
                            String productID = input;
                            while(true) {
                                input = scanner.nextLine();
                                if (input.equalsIgnoreCase("Back")) {
                                    this.show();
                                    break;
                                } else if (input.equalsIgnoreCase("Logout")) {
                                    loginAndRegisterManager.logoutUser();
                                } else if (input.matches("\\A\\d+\\z")) {
                                    if (Integer.parseInt(input) <= 5 && Integer.parseInt(input) >= 1) {
                                        customerProfileManager.rateProduct(productID, Integer.parseInt(input));
                                        System.out.println("Your rate submitted");
                                        parentMenu.execute();
                                    } else {
                                        System.out.println("please enter a number in range(1-5):");
                                    }
                                } else {
                                    System.out.println("please enter valid number:");
                                }
                            }
                        } else {
                            System.out.println("There is no product with this ID");
                        }
                    } else {
                        System.out.println("Please enter valid number:");
                    }
                }
            }
        };
    }
}
