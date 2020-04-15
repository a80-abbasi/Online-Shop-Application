package Controller;

import Model.Account.*;
import Model.Product.Category;
import Model.Product.Product;
import Model.Product.ProductStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerProfileManager extends ProfileManager {
    private Seller seller;

    public SellerProfileManager(Seller seller) {
        super(seller);
        this.seller = seller;
    }

    public String getCompanyInformation() {
        return seller.getNameOfCompany();
    }

    public ArrayList<String> getSalesHistory() {
        ArrayList<String> salesHistory = new ArrayList<>();
        for (SellLog sellLog : seller.getSellLogs()) {
            salesHistory.add(sellLog.toString());
        }
        return salesHistory;
    }

    public ArrayList<Product> viewProductByID(String id) {
        return null;
    }

    public ArrayList<Account> viewBuyersByID(String id) {
        return null;
    }

    public void editByID(String id, HashMap< String , String > newFields)  {
    }

    public void addProduct(String productId, ProductStatus productStatus, String productName, String companyName, double price, int existingNumber, Seller seller) {
        new AddProductRequest(productId, productStatus, productName, companyName, price, existingNumber, seller);
    }

    public void removeProduct(String productId) {
        Product product = Product.getProductByID(productId);
        Product.removeProduct(product);
    }

    public ArrayList<String> getAllCategories() {
        ArrayList<Category> allCategories = Category.getAllCategories();
        ArrayList<String> allCategoriesNames = new ArrayList<>();
        for (Category category : allCategories) {
            allCategoriesNames.add(category.toString());
        }
        return allCategoriesNames;
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
