package Controller;

import Model.Account.*;
import Model.Request.*;
import Model.Product.Category;
import Model.Product.Product;
import Model.Product.ProductStatus;
import View.SellerProfileMenus.SellerProfileMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class SellerProfileManager extends ProfileManager {
    private Seller seller;

    public SellerProfileManager(Seller seller) {
        super(seller);
        this.seller = seller;
    }

    public HashMap<String, String> getCompanyInformation() {
        HashMap <String, String> companyInformation = new HashMap<String, String>();
        companyInformation.put("Name", seller.getNameOfCompany());
        return companyInformation;
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

    public Product getProductByID(String id) {
        return Product.getProductByID(id);
    }

    public HashMap<String, String> getProductBuyers(String productId) {
        HashMap<String, String> allBuyersUsernameAndPhoneNumber = new HashMap<>();
        for (Customer customer : Product.getProductByID(productId).getProductBuyers()) {
            allBuyersUsernameAndPhoneNumber.put(customer.getUsername(),customer.getPhoneNumber());
        }
        return allBuyersUsernameAndPhoneNumber;
    }

    public void editProductByID(String productId, HashMap<String, String> fieldChanges) {
        new EditProductRequest(Product.getProductByID(productId), fieldChanges);
    }

    public static ArrayList<String> getAllProductFields() {
        return Product.getProductFields();
    }

    public AddProductRequest addProductRequest() {
       return new AddProductRequest();
    }

    public void addProductId(AddProductRequest addProductRequest, String productId) throws IllegalArgumentException {
        if (Product.getProductByID(productId) != null) {
            throw new IllegalArgumentException();
        }
        addProductRequest.setProductId(productId);
    }

    public void addProductName(AddProductRequest addProductRequest, String productName) {
        addProductRequest.setProductName(productName);
    }

    public void addProductCompanyName(AddProductRequest addProductRequest, String companyName) {
        addProductRequest.setCompanyName(companyName);
    }

    public void addProductPrice(AddProductRequest addProductRequest, String producePrice) throws InputMismatchException {
        if (producePrice.matches("\\d+\\.?\\d.")) {
            addProductRequest.setPrice(Double.parseDouble(producePrice));
        }
        else {
            throw new InputMismatchException();
        }
    }

    public void addExistingNumberOfProduct(AddProductRequest addProductRequest, String existingNumber) throws InputMismatchException {
        if (existingNumber.matches("\\d+")) {
            addProductRequest.setExistingNumber(Integer.parseInt(existingNumber));
        }
        else {
            throw new InputMismatchException();
        }
    }

    public void addProductSeller(AddProductRequest addProductRequest, String sellerUsername) throws NullPointerException, InputMismatchException {
        Account account = Account.getAccountByUsername(sellerUsername);
        if (account == null) {
            throw new NullPointerException();
        }
        else if (account instanceof Seller) {
            addProductRequest.setProductSeller((Seller) account);
        }
        else {
            throw new InputMismatchException();
        }
    }

    public static boolean isProductIdFormatValid(String productId) {
        if (Product.getProductByID(productId) == null) {
            return false;
        }
        else {
            return true;
        }
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

    public static boolean isInputInOffFields(String input) {
        for (int i = 0; i < Off.getOffFields().size(); i++) {
            if (Off.getOffFields().get(i).equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInputInProductFields(String input) {
        for (int i = 0; i < Product.getProductFields().size(); i++) {
            if (Product.getProductFields().get(i).equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInputValidOffValue(String input) {
        //todo:check is new value valid or not;
        return true;
    }

    public static boolean isInputValidProductValue(String input) {
        //todo:check is new value valid or not;
        return true;
    }

    public HashMap<String, String> getOffsAmountAndID() {
        HashMap<String, String> offsAmountAndID = new HashMap<>();
        for (int i = 0; i < seller.getOffs().size(); i ++) {
            offsAmountAndID.put(String.valueOf(seller.getOffs().get(i).getOffAmount()), seller.getOffs().get(i).getOffID());
        }
        return offsAmountAndID;
    }

    public HashMap<String, String> getSellerProductsNameAndID() {
        HashMap<String, String> SellerProductsNameAndID = new HashMap<>();
        for (int i = 0; i < seller.getProducts().size(); i ++) {
            SellerProductsNameAndID.put(String.valueOf(seller.getProducts().get(i).getProductName()), seller.getProducts().get(i).getProductId());
        }
        return SellerProductsNameAndID;
    }

    public static Off getOffByID(String offID) {
        return Off.getOffById(offID);
    }

    public String getOffStatus(String offId) {
        //todo:
        return "CHANGE THIS MESSAGE";
    }

    public static ArrayList<String> getOffFields() {
        return Off.getOffFields();
    }

    public void editOffByID(String offId, HashMap<String, String> fieldChanges) {
        Off off = Off.getOffById(offId);
        new EditOffRequest(off, fieldChanges);
    }

    public void addOff (HashMap<String, String> properties) {
        //new AddOffRequest(properties);
    }

    public int viewBalance(Account account) {
        return 0;
    }



}
