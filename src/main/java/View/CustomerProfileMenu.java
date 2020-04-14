package View;

import Model.Account.Customer;
import Model.Account.Discount;

import java.util.ArrayList;

public class CustomerProfileMenu extends Menu{
    Customer customer;
    public CustomerProfileMenu(Customer customer, Menu parentMenu) {
        super("CustomerProfile Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        this.customer = customer;
        subMenus.add(new ViewPersonalInfoMenu(customer, "customer", parentMenu)); //todo: w8 answer in group to change it to father function or use it in every menu;
        subMenus.add(new ViewCartMenu(this));
        subMenus.add(new ViewOrdersMenu(customer,this));
        subMenus.add(getViewBalanceMenu());
        subMenus.add(getViewDiscountCodesMenu());
        this.setSubmenus(subMenus);
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
