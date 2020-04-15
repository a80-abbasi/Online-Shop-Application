package Controller;

import Model.Account.Account;
import Model.Account.Admin;
import Model.Account.Discount;
import Model.Product.Category;
import Model.Product.Product;

import java.util.ArrayList;

public class AdminProfileManager extends ProfileManager{
    private Admin admin;

    public AdminProfileManager(Admin admin) {
        super(admin);
        this.admin = admin;
    }

    public ArrayList<Account> getAllUsers() {
        ArrayList<Account> allAccounts = Account.getAllAccounts();
        return allAccounts;
    }

    public String viewUser(String username) {
        Account account = Account.getAccountByUsername(username);
        return account.toString();
    }

    public void changeTypeOfAccount(String username, String role) {
        Account account = Account.getAccountByUsername(username);
    }

    public void deleteUser(String username) {
        Account account = Account.getAccountByUsername(username);
        Account.deleteAccount(account);
    }

    public void createManagerProfile(String username, String password, String name, String lastName, String email, String phoneNumber) {
        new Admin(username, password, name, lastName, email, phoneNumber);
    }

    public void removeProduct(String productId) {
        Product product = Product.getProductByID(productId);
        Product.removeProduct(product);
    }

    public void createDiscountCode(String discountCode, String startTime, String endTime, int discountPercent, int maxPossibleDiscount, int discountPerCustomer) {
        new Discount(discountCode, startTime, endTime, discountPercent, maxPossibleDiscount, discountPerCustomer);
    }

    public ArrayList<Discount> getAllDiscountCodes() {
        ArrayList<Discount> allDiscounts = Discount.getAllDiscounts();
        return allDiscounts;
    }

    public void viewDiscount(String discountCode) {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        discount.toString();
    }

    public void editDiscount(String discountCode) {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
    }

    public void removeDiscount(String discountCode) {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        Discount.removeDiscount(discount);
    }

    public String showAllRequests() {
        //todo: make a class for requests
        return null;
    }


    public void getDetailsOfRequest(String requestId) {

    }

    public void acceptRequest(String requestId) {

    }

    public void declineRequest(String requestId) {

    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> allCategories = Category.getAllCategories();
        return allCategories;
    }

    public void editCategory(String categoryName, String changeField) {
        Category category = Category.getCategoryByName(categoryName);

    }

    public void addCategory(String categoryName, String specialProperties) {
        new Category(categoryName, specialProperties);
    }

    public void removeCategory(String categoryName) {
        Category category = Category.getCategoryByName(categoryName);
        Category.removeCategory(category);
    }
}
