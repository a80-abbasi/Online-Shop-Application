package Controller;

import Model.Account.Account;
import Model.Account.Admin;
import Model.Account.Customer;
import Model.Account.Seller;
import View.AdminProfileMenu;
import View.CustomerProfileMenu;
import View.SellerProfileMenu;

import java.util.Scanner;

public class LoginAndRegisterManager {
    private Scanner scanner;

    public void registerAccount(String type, String username) {

    }

    public void loginAccount(String username) throws IllegalArgumentException{
        Account account = Account.getAccountByUsername(username);
        if (account == null) {
            throw new IllegalArgumentException();
        }
        else {
            String password = scanner.nextLine().trim();
            if (account.getPassword().equals(password)) {
                if (account instanceof Admin) {
                    new AdminProfileMenu((Admin) account);
                }
                else if (account instanceof Seller) {
                    new SellerProfileMenu((Seller) account);
                }
                else if (account instanceof Customer) {
                    new CustomerProfileMenu((Customer) account);
                }
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }
}
