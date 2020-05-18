package Controller;


import Model.Account.*;
import Model.Request.RegisterSellerRequest;
import View.MainMenu;

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
                               String phoneNumber, String companyName) {
        new RegisterSellerRequest(username, password, name, lastName, email, phoneNumber, companyName);
    }

    public void registerAdmin(String username, String password, String name, String lastName, String email,
                              String phoneNumber){
        new Admin(username, password, name, lastName, email, phoneNumber);
    }

    public boolean canCreateAdminManually(){
        return Admin.getAllAdmins().size() == 0;
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
        new MainMenu().execute();
    }

    public boolean isLogin(){
        return Account.getLoggedInAccount() != null;
    }

}
