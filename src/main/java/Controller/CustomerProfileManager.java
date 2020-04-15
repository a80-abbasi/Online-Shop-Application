package Controller;

import Model.Account.Customer;

public class CustomerProfileManager extends ProfileManager {
    private Customer customer;

    public CustomerProfileManager(Customer customer) {
        super(customer);
        this.customer = customer;
    }

}
