package Model.Account;

import Model.Product.Product;

import java.util.ArrayList;

public class Seller extends Account {
    private String nameOfCompany;
    private ArrayList<SellLog> sellLogs;
    private ArrayList<Product> products;
    private ArrayList<Off> offs;

    public Seller(String username, String password, String name, String lastName, String email, String phoneNumber, String nameOfCompany) {
        super(username, password, name, lastName, email, phoneNumber);
        this.nameOfCompany = nameOfCompany;
        sellLogs = new ArrayList<>();
        products = new ArrayList<>();
        offs = new ArrayList<>();
    }

    public Seller() {
        super();
        this.nameOfCompany = "";
    }
}
