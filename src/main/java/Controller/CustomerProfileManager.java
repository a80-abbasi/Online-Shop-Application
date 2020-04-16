package Controller;

import Model.Account.Account;
import Model.Account.Customer;
import Model.Account.Discount;
import Model.Product.Product;

import java.util.ArrayList;

public class CustomerProfileManager extends ProfileManager{
    private Customer customer;

    public CustomerProfileManager(Customer customer) {
        super(customer);
        this.customer = customer;
    }

    public ArrayList<Product> showProducts(Account account) {
        return null;
    }
    public ArrayList<String> viewProduct(String id) {
        return null;
    }
    public boolean increaseProductInCart(String id) {
        return true;
    }
    public boolean decreaseProductInCart(String id) {
        return true;
    }
    public int showTotalPrice(Account account)  {
        return 0;
    }
    public boolean purchase(ArrayList<String> properties, String discountCode) {
        return true;
    }
    public ArrayList<String> showOrders(String id) {
        return null;
    }
    public boolean rateProduct(String id, int score) {
        return true;
    }
    public ArrayList<Discount> viewDiscountCodes(Account account) {
        return null;
    }
    public int viewBalance(Account account) {
        return 0;
    }
    public boolean isDiscountCodeAvailableForCustomer(Account account, Discount discount) {
        return true;
    }

}
