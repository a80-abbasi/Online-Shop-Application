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

    public String viewAllUsers() {
        ArrayList<Account> allAccounts = Account.getAllAccounts();
        String allUserNames = "";
        for (Account account : allAccounts) {
            allUserNames.concat(account.getUsername() + "\n");
        }
        return allUserNames;
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

    public String viewAllDiscountCodes() {
        ArrayList<Discount> allDiscounts = Discount.getAllDiscounts();
        String allDiscountsInfo = "";
        for (Discount discount : allDiscounts) {
            allDiscountsInfo.concat(discount.toString() + "\n");
        }
        return allDiscountsInfo;
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

    public String showAllCategories() {
        ArrayList<Category> allCategories = Category.getAllCategories();
        String allCategoriesNames = "";
        for (Category category : allCategories) {
            allCategoriesNames.concat(category.getName() + "\n");
        }
        return allCategoriesNames;
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
