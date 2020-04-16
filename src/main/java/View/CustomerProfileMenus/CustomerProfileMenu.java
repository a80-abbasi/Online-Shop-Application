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
        subMenus.add(new ViewPersonalInfoMenu(parentMenu, customerProfileManager)); //todo: w8 answer in group to change it to father function or use it in every menu;
        subMenus.add(new ViewCartMenu(this));
        subMenus.add(new ViewOrdersMenu(customer,this));
        subMenus.add(getViewBalanceMenu());
        subMenus.add(getViewDiscountCodesMenu());
        this.setSubMenus(subMenus);
    }


    private Menu getViewBalanceMenu() {
        return new Menu("View Balance", this) {
            @Override
            public void execute() {
                System.out.println(customer.getBalance());
                parentMenu.execute();
            }
        };
    }

    private Menu getViewDiscountCodesMenu() {
        return new Menu("Discount Codes" , this) {
            @Override
            public void execute() {
                //TODO:change this part when u got answer in group
                for (Discount allDiscount : Discount.getAllDiscounts()) {
                    System.out.println(allDiscount.getDiscountCode());
                }
                parentMenu.execute();
            }
        };
    }

    //TODO: logout function or back function;
}
