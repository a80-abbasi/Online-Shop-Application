package Model.Account;

import Model.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends Account {
    private int balance;
    private ArrayList<Product> cart;
    private ArrayList<BuyLog> buyLogs;
    private HashMap<Discount, Integer> usedDiscounts;

    public Customer(String username, String password, String name, String lastName, String email, String phoneNumber,
                    int balance) {
        super(username, password, name, lastName, email, phoneNumber);
        this.balance = balance;
        cart = new ArrayList<>();
        buyLogs = new ArrayList<>();
        usedDiscounts = new HashMap<>();
    }

    public Customer() {
        super();
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
