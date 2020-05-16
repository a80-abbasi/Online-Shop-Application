package View.CustomerProfileMenus;

import Controller.CustomerProfileManager;
import Model.Account.Customer;
import Model.Account.Discount;
import View.Menu;
import View.ViewPersonalInfoMenu;

import java.util.ArrayList;

public class CustomerProfileMenu extends Menu {
    private Customer customer;
    private CustomerProfileManager customerProfileManager;
    private ArrayList<Discount> allDiscountCodes = new ArrayList<>();
    public CustomerProfileMenu(Customer customer, Menu parentMenu) {
        super("CustomerProfile Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        this.customer = customer;
        this.customerProfileManager = new CustomerProfileManager(customer);
        subMenus.add(new ViewPersonalInfoMenu(parentMenu, customerProfileManager));
        subMenus.add(new ViewCartMenu(customer,this));
        subMenus.add(new ViewOrdersMenu(customer,this));
        subMenus.add(getViewBalanceMenu());
        subMenus.add(getViewDiscountCodesMenu());
        this.setSubMenus(subMenus);
    }

    private Menu getViewBalanceMenu() {
        return new Menu("View Balance", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println(customerProfileManager.viewBalance(customer));
                System.out.println("1. Logout");
                System.out.println("2. Back");
            }
        };
    }

    private Menu getViewDiscountCodesMenu() {
        return new Menu("Discount Codes" , this) {
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println(customerProfileManager.getDiscountCodes());
                System.out.println("1. Logout");
                System.out.println("2. Back");
            }
        };
    }
}
