package Controller;

import Model.Account.Account;
import Model.Account.Off;
import Model.Account.Seller;
import Model.Product.Category;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerProfileManager extends ProfileManager {
    private Seller seller;

    public SellerProfileManager(Seller seller) {
        super(seller);
        this.seller = seller;
    }

    public ArrayList<String> viewCompanyInformation(Account account) {
        return null;
    }
    public ArrayList<String> viewSalesHistory(Account account) {
        return null;
    }
    public ArrayList<Product> viewProductByID(String id) {
        return null;
    }
    public ArrayList<Account> viewBuyersByID(String id) {
        return null;
    }
    public void editByID(String id, HashMap< String , String > newFields)  {

    }
    public boolean addProduct(ArrayList<String> properties) {
        return true;
    }
    public void removeProduct(String id) {

    }
    public ArrayList<Category> showAllCategories() {
        return null;
    }
    public ArrayList<Off> viewOffs(Account Seller) {
        return null;
    }
    public Off viewOff(String id) {
        return null;
    }
    public void editOff(String id) {

    }
    public void addOff (ArrayList<String> properties) {

    }
    public int viewBalance(Account account) {
        return 0;
    }

}
