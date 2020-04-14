package Model.Account;

import Model.Product.Category;

import java.util.ArrayList;

public class Admin extends Account{
    private static ArrayList<Off> allOffs = Off.getAllOffs();
    private static ArrayList<Account> allAccounts = Account.getAllAccounts();
    private static ArrayList<Category> allCategories = Category.getAllCategories();

    public Admin(String username, String password, String name, String lastName, String email, String phoneNumber) {
        super(username, password, name, lastName, email, phoneNumber);

    }
}
