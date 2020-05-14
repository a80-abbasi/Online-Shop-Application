package Controller;


import Model.Account.*;

public class LoginAndRegisterManager {

    public boolean isThereAccountWithUserName(String username){
        Account account = Account.getAccountByUsername(username);
        return account != null;
    }

    public void registerCustomer(String username, String password, String name, String lastName, String email,
                                 String phoneNumber){
        new Customer(username, password, name, lastName, email, phoneNumber, 0);
    }

    public void registerSeller(String username, String password, String name, String lastName, String email,
                               String phoneNumber, String companyName){
        new Seller(username, password, name, lastName, email, phoneNumber, companyName, 0);
    }

    public void registerAdmin(String username, String password, String name, String lastName, String email,
                              String phoneNumber){
        new Admin(username, password, name, lastName, email, phoneNumber);
    }

    public boolean canCreateAdminManually(){
        return Account.getAllAccounts().size() == 0;
    }

    public void loginUser(String username, String password) throws IllegalAccessException {
        Account account = Account.getAccountByUsername(username);
        if (account == null) {
            throw new IllegalAccessException("There is no Account with this Username");
        }
        if (!account.getPassword().equals(password)){
            throw new IllegalArgumentException("Your Password is wrong");
        }
        Account.setLoggedInAccount(account);
    }

    public void logoutUser(){
        Account.setLoggedInAccount(null);
    }

    public boolean isLogin(){
        return Account.getLoggedInAccount() != null;
    }

}
