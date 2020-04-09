package Model;

import java.util.ArrayList;

public class Customer extends Account {
    private int balance;
    private ArrayList<Product> cart;
    private ArrayList<BuyLog> buyLogs;

    public Customer(String username, String password, String name, String lastName, String email, String phoneNumber,
                    int balance) {
        super(username, password, name, lastName, email, phoneNumber);
        this.balance = balance;
        cart = new ArrayList<>();
        buyLogs = new ArrayList<>();
    }

    public Customer() {
        super();
        this.balance = 0;
    }
}
