package Controller;

import Model.Account.Account;
import Model.Account.Admin;
import Model.Account.Discount;
import Model.Request.Request;
import Model.Product.Category;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.InputMismatchException;

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

    public void editDiscountCode(String discountCodeBefore, String discountCodeAfter) throws NullPointerException, IllegalArgumentException {
        Discount discount = Discount.getDiscountByDiscountCode(discountCodeBefore);
        if (discount == null) {
            throw new NullPointerException();
        }
        else if (Discount.getDiscountByDiscountCode(discountCodeAfter) != null) {
            throw new IllegalArgumentException();
        }
        else {
            discount.setDiscountCode(discountCodeAfter);
        }
    }

    public void editDiscountStartTime(String discountCode, String startTime) throws NullPointerException {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        }
        discount.setStartTime(startTime);
    }

    public void editDiscountEndTime(String discountCode, String endTime) throws NullPointerException {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        }
        discount.setEndTime(endTime);
    }

    public void editDiscountPercent(String discountCode, String discountPercent) throws NullPointerException, IllegalArgumentException {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        }
        else if (discountPercent.matches("\\d[1-2]")) {
            discount.setDiscountPercent(Integer.parseInt(discountPercent));
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void editDiscountMaxPossibleDiscount(String discountCode, String maxPossibleDiscount) throws NullPointerException, InputMismatchException{
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        }
        else if (maxPossibleDiscount.matches("\\d+\\.\\d+")) {
            discount.setMaxPossibleDiscount(Double.parseDouble(maxPossibleDiscount));
        }
        else {
            throw new InputMismatchException();
        }
    }

    public void editDiscountPerCustomer(String discountCode, String discountPerCustomer) throws NullPointerException, InputMismatchException{
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        }
        else if ((discountPerCustomer.matches("\\d+"))) {
            discount.setDiscountPerCustomer(Integer.parseInt(discountPerCustomer));
        }
        else {
            throw new InputMismatchException();
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

    public void acceptRequest(String requestId) throws NullPointerException, IllegalArgumentException{
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

    //todo: completing this
    public void editCategory(String categoryName, String changeField) throws NullPointerException{
        Category category = Category.getCategoryByName(categoryName);
        if (category == null) {
            throw new NullPointerException();
        }

    }

    //todo: completing this
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
