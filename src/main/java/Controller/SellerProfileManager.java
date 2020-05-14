package Controller;

import Model.Account.*;
import Model.Request.*;
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

    public ArrayList<String> getSellerProducts() {
        ArrayList<Product> sellerProducts = seller.getProducts();
        ArrayList<String> sellerProductsIds = new ArrayList<>();
        for (Product product : sellerProducts) {
            sellerProductsIds.add(product.getProductId());
        }
        return sellerProductsIds;
    }

    public ArrayList<Product> viewProductByID(String id) {
        return null;
    }

    public ArrayList<String> getProductBuyers(String productId) {
        Product product = Product.getProductByID(productId);
        ArrayList<Customer> allBuyers = product.getProductBuyers();
        ArrayList<String> allBuyersNames = new ArrayList<>();
        for (Customer buyer : allBuyers) {
            allBuyersNames.add(buyer.getUsername());
        }
        return allBuyersNames;
    }

    public void editProductByID(String productId, HashMap<String, String> fieldChanges) {
        Product product = Product.getProductByID(productId);
        new EditProductRequest(product, fieldChanges);
    }

    public void addProduct(String productId, ProductStatus productStatus, String productName, String companyName, double price, int existingNumber, Seller seller) {
        new AddProductRequest(productId, productStatus, productName, companyName, price, existingNumber, seller);
    }

    public void removeProduct(String productId) {
        Product product = Product.getProductByID(productId);
        new RemoveProductRequest(product);
    }

    public ArrayList<String> getAllCategories() {
        ArrayList<Category> allCategories = Category.getAllCategories();
        ArrayList<String> allCategoriesNames = new ArrayList<>();
        for (Category category : allCategories) {
            allCategoriesNames.add(category.toString());
        }
        return allCategoriesNames;
    }

    public ArrayList<String> viewOffs() {
        ArrayList<String> allSellerOffIds = new ArrayList<>();
        ArrayList<Off> allSellerOffs = seller.getOffs();
        for (Off sellerOff : allSellerOffs) {
            allSellerOffIds.add(sellerOff.getOffID());
        }
        return allSellerOffIds;
    }

    public String getOffStatus(String offId) {
        Off off = Off.getOffById(offId);
        return off.toString();
    }

    public void editOff(String offId, HashMap<String, String> fieldChanges) {
        Off off = Off.getOffById(offId);
        new EditOffRequest(off, fieldChanges);
    }

    public void addOff (ArrayList<String> properties) {
        new AddOffRequest(properties);
    }
    public int viewBalance(Account account) {
        return 0;
    }

}
