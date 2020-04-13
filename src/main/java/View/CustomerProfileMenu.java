package View;

import Model.Account.Customer;

public class CustomerProfileMenu extends Menu{
    private Customer customer;

    public CustomerProfileMenu(Customer customer) {
        super("Customer Profile Menu", null);
        this.customer = customer;
    }
}
