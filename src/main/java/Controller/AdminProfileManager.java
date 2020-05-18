package Controller;

import Model.Account.Account;
import Model.Account.Admin;
import Model.Account.Discount;
import Model.Request.Request;
import Model.Product.Category;
import Model.Product.Product;

import java.util.*;

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

    //todo: checking this
    public void deleteUser(String username) throws NullPointerException{
        Account account = Account.getAccountByUsername(username);
        if (account == null) {
            throw new NullPointerException();
        }
        Account.deleteAccount(account);
    }

    public static String generateRandomDiscountCode(){
        char[] code = new char[8];
        Random random = new Random();
        for(int i = 0; i < 8; ++i)
        {
            int a = random.nextInt(62);

            if (a < 10)
                code[i] = (char)(a + 48);

            else if (a < 36)
                code[i] = (char)(a + 55);
            else
                code[i] = (char)(a + 61);
        }
        return String.valueOf(code);
    }

    //todo: adding Exceptions
    public void createManagerProfile(String username, String password, String name, String lastName, String email, String phoneNumber) {
        new Admin(username, password, name, lastName, email, phoneNumber);
    }

    //todo: checking this
    public void removeProduct(String productId) throws NullPointerException{
        Product product = Product.getProductByID(productId);
        if (product == null) {
            throw new NullPointerException();
        }
        else {
            Product.removeProduct(product);
        }
    }

    //todo: completing this and adding exceptions
    public static void createDiscountCode(String discountCode, Date startTime, Date endTime, int discountPercent, int maxPossibleDiscount, int discountPerCustomer) {
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

    public void editDiscountStartTime(String discountCode, Date startTime) throws NullPointerException {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        }
        discount.setStartTime(startTime);
    }

    public void editDiscountEndTime(String discountCode, Date endTime) throws NullPointerException {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        }
        discount.setEndTime(endTime);
    }

    //todo: checking this
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

    //todo: checking this
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

    //todo: checking this
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

    //todo: checking this
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

    public void addCategory(String categoryName, ArrayList<String> specialFeatures) {
        Category category = new Category(categoryName);
        category.setSpecialFeatures(specialFeatures);
    }

    //todo: completing this
    public void addSubCategory(String subCategoryName, String parentCategory) {
        Category category = Category.getCategoryByName(parentCategory);
        category.addSubCategoryWithName(subCategoryName);
    }

    public void editCategoryName(Category category, String newCategoryName) throws IllegalArgumentException {
        if (Category.getCategoryByName(newCategoryName) != null) {
            throw new IllegalArgumentException();
        }
        category.setName(newCategoryName);
    }

    public void removeCategorySpecialFeature(Category category, String specialFeature) throws NullPointerException {
        if (category.getSpecialFeatures().contains(specialFeature)) {
            category.removeSpecialFeature(specialFeature);
        }
        else {
            throw new NullPointerException();
        }
    }

    public void addSpecialFeature(Category category, String specialFeature) throws IllegalArgumentException {
        if (category.getSpecialFeatures().contains(specialFeature)) {
            throw new IllegalArgumentException();
        }
        else {
            category.addSpecialFeature(specialFeature);
        }
    }

    public void removeCategory(String categoryName) throws NullPointerException{
        Category category = Category.getCategoryByName(categoryName);
        if (category == null) {
            throw new NullPointerException();
        }
        Category.removeCategory(category);
    }

    public static boolean isProductWithThisID(String productID) {
        for (Product product : Product.getAllProducts()) {
            if (product.getProductId().equals(productID)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCategoryWithThisName(String categoryName) {
        for (Category category : Category.getAllCategories()) {
            if (category.getName().equals(categoryName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDiscountWithThisID(String ID) {
        for (Discount discount : Discount.getAllDiscounts()) {
            if (discount.getDiscountCode().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInputValidForDiscountCode(String input) {
        if (!input.trim().matches("\\s")) {
            return true;
        }
        return false;
    }

    public static boolean isInputValidForDiscountPercent(String input) {
        if (input.matches("\\A\\d\\d\\z")) {
            return true;
        }
        return false;
    }

}
