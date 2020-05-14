package Controller;

import Model.Account.Account;
import Model.Account.AccountType;
import Model.Account.Admin;
import Model.Account.Discount;
import Model.Request.Request;
import Model.Product.Category;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminProfileManager extends ProfileManager{

    public AdminProfileManager(Admin admin) {
        super(admin);
    }

    public ArrayList<Account> getAllUsers() {
        return Account.getAllAccounts();
    }

    public String viewUser(String username) throws NullPointerException{
        Account account = Account.getAccountByUsername(username);
        if (account == null) {
            throw new NullPointerException();
        }
        return account.toString();
    }

    public void deleteUser(String username) throws NullPointerException{
        Account account = Account.getAccountByUsername(username);
        if (account == null) {
            throw new NullPointerException();
        }
        Account.deleteAccount(account);
    }

    public void createManagerProfile(String username, String password, String name, String lastName, String email, String phoneNumber) {
        new Admin(username, password, name, lastName, email, phoneNumber);
    }

    public void removeProduct(String productId) throws NullPointerException{
        Product product = Product.getProductByID(productId);
        if (product == null) {
            throw new NullPointerException();
        }
        else {
            Product.removeProduct(product);
        }
    }

    public void createDiscountCode(String discountCode, String startTime, String endTime, int discountPercent, int maxPossibleDiscount, int discountPerCustomer) {
        new Discount(discountCode, startTime, endTime, discountPercent, maxPossibleDiscount, discountPerCustomer);
    }

    public ArrayList<Discount> getAllDiscountCodes() {
        return Discount.getAllDiscounts();
    }

    public String viewDiscount(String discountCode) throws NullPointerException{
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        }
        return discount.toString();
    }

    public void editDiscount(String discountCode, HashMap<String, String> discountEdition) throws NullPointerException{
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        }

    }

    public void removeDiscount(String discountCode) throws NullPointerException{
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        }
        Discount.removeDiscount(discount);
    }

    public ArrayList<String> getAllRequests() {
        ArrayList<Request> allRequests = Request.getAllRequests();
        ArrayList<String> allRequestIds = new ArrayList<>();
        for (Request request : allRequests) {
            allRequestIds.add(request.getRequestId());
        }
        return allRequestIds;
    }


    public String getDetailsOfRequest(String requestId) throws NullPointerException{
        Request request = Request.getRequestById(requestId);
        if (request == null) {
            throw new NullPointerException();
        }
        return request.toString();
    }

    public void acceptRequest(String requestId) throws NullPointerException{
        Request request = Request.getRequestById(requestId);
        if (request == null) {
            throw new NullPointerException();
        }
        request.acceptRequest();
        Request.removeRequest(request);
    }

    public void declineRequest(String requestId) throws NullPointerException{
        Request request = Request.getRequestById(requestId);
        if (request == null) {
            throw new NullPointerException();
        }
        Request.removeRequest(request);
    }

    public ArrayList<Category> getAllCategories() {
        return Category.getAllCategories();
    }

    public void editCategory(String categoryName, String changeField) throws NullPointerException{
        Category category = Category.getCategoryByName(categoryName);
        if (category == null) {
            throw new NullPointerException();
        }

    }

    public void addCategory(String categoryName, String specialProperties) {
        new Category(categoryName, specialProperties);
    }

    public void removeCategory(String categoryName) throws NullPointerException{
        Category category = Category.getCategoryByName(categoryName);
        if (category == null) {
            throw new NullPointerException();
        }
        Category.removeCategory(category);
    }
}
