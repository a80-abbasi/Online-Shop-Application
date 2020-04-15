package Model.Account;

import Model.Product.Product;

import java.util.ArrayList;

public class Seller extends Account {
    private String nameOfCompany;
    private int balance;
    private ArrayList<SellLog> sellLogs;
    private ArrayList<Product> products;
    private ArrayList<Off> offs;

    public Seller(String username, String password, String name, String lastName, String email, String phoneNumber, String nameOfCompany,
                  int balance) {
        super(username, password, name, lastName, email, phoneNumber);
        this.nameOfCompany = nameOfCompany;
        this.balance = balance;
        sellLogs = new ArrayList<>();
        products = new ArrayList<>();
        offs = new ArrayList<>();
    }

    public Seller() {
        this("", "", "", "", "", "", "", 0);
        this.nameOfCompany = "";
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public int getBalance() {
            return balance;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setSellLogs(ArrayList<SellLog> sellLogs) {
        this.sellLogs = sellLogs;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setOffs(ArrayList<Off> offs) {
        this.offs = offs;
    }
}
