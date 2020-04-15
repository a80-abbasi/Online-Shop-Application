package Model.Account;

import java.util.ArrayList;

public abstract class Account {
    private static ArrayList<Account> allAccounts = new ArrayList<>();
    private static Account loggedInAccount;
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Account(String username, String password, String name, String lastName, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        allAccounts.add(this);
    }

    public Account() {
        this("", "", "", "", "", "");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static void setLoggedInAccount(Account loggedInAccount) {
        Account.loggedInAccount = loggedInAccount;
    }

    public static Account getAccountByUsername (String username) {
        for (Account account : allAccounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                return account;
            }
        }
        return null;
    }

    public static void deleteAccount(Account account) {
        allAccounts.remove(account);
    }
}
