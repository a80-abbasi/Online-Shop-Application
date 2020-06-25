package Controller;

import Model.Account.Account;
import Model.Account.Admin;
import Model.Account.Customer;
import Model.Account.Discount;
import Model.Request.Request;
import Model.Product.Category;
import Model.Product.Product;
import Model.Request.RequestType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.*;

public class AdminProfileManager extends ProfileManager {

    public AdminProfileManager(Admin admin) {
        super(admin);
    }

    public static boolean isThereAdmin() {
        if (Admin.getAllAdmins().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Account> getAllUsers() {
        return Account.getAllAccounts();
    }

    public TableView getAllUsersTable() {
        TableView allUsers = new TableView();

        TableColumn<String, Account> column1 = new TableColumn<>("Username");
        column1.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<String, Account> column2 = new TableColumn<>("First Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, Account> column3 = new TableColumn<>("Last Name");
        column3.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<String, Account> column4 = new TableColumn<>("Email");
        column4.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<String, Account> column5 = new TableColumn<>("Phone Number");
        column5.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        allUsers.getColumns().addAll(column1, column2, column3, column4, column5);
        for (Account account : Account.getAllAccounts()) {
            allUsers.getItems().add(account);
        }
        allUsers.setPlaceholder(new Label("No Data to display"));
        return allUsers;
    }

    public TableView getAllCustomersTable(TableView allCustomers) {
        TableColumn<String, Customer> column1 = new TableColumn<>("Username");
        column1.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<String, Customer> column2 = new TableColumn<>("First Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, Customer> column3 = new TableColumn<>("Last Name");
        column3.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<String, Customer> column4 = new TableColumn<>("Email");
        column4.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<String, Customer> column5 = new TableColumn<>("Phone Number");
        column5.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        allCustomers.getColumns().addAll(column1, column2, column3, column4, column5);
        for (Customer customer : Customer.getAllCustomers()) {
            allCustomers.getItems().add(customer);
        }
        allCustomers.setPlaceholder(new Label("No Data to display"));
        return allCustomers;
    }

    public String viewUser(String username) throws NullPointerException {
        Account account = Account.getAccountByUsername(username);
        if (account == null) {
            throw new NullPointerException();
        }
        return account.toString();
    }

    public void deleteUser(String username) throws NullPointerException, IllegalArgumentException {
        if (username.equals("")) {
            throw new IllegalArgumentException("You must enter username.");
        } else {
            Account account = Account.getAccountByUsername(username);
            if (account == null) {
                throw new NullPointerException("There is no account with this username.");
            }
            Account.deleteAccount(account);
        }
    }

    public static String generateRandomDiscountCode() {
        char[] code = new char[8];
        Random random = new Random();
        for (int i = 0; i < 8; ++i) {
            int a = random.nextInt(62);

            if (a < 10)
                code[i] = (char) (a + 48);

            else if (a < 36)
                code[i] = (char) (a + 55);
            else
                code[i] = (char) (a + 61);
        }
        return String.valueOf(code);
    }

    public void createManagerProfile(String username, String password, String name, String lastName, String email, String phoneNumber) {
        new Admin(username, password, name, lastName, email, phoneNumber);
    }

    //todo: checking this
    public void removeProduct(String productId) throws NullPointerException {
        Product product = Product.getProductByID(productId);
        if (product == null) {
            throw new NullPointerException();
        } else {
            Product.removeProduct(product);
        }
    }

    public void createDiscountCode(String discountCode, Date startTime, Date endTime, String discountPercent, String maxPossibleDiscount, String discountPerCustomer, ArrayList<String> includingCustomers) throws Exception{
        if (checkDiscountCodeValidity(discountCode) && checkDiscountPercentValidity(discountPercent) && checkMaxPossibleDiscountValidity(maxPossibleDiscount) && checkDiscountPerCustomerValidity(discountPerCustomer) && checkCustomersValidity(includingCustomers)) {
            new Discount(discountCode, startTime, endTime, Double.parseDouble(discountPercent), Double.parseDouble(maxPossibleDiscount), Integer.parseInt(discountPerCustomer), includingCustomers);
        }
    }

    public TableView getAllDiscountsTable(TableView allDiscountsTable) {
        TableColumn<String, Discount> column = new TableColumn<>("Discount Code");
        column.setCellValueFactory(new PropertyValueFactory<>("discountCode"));

        allDiscountsTable.getColumns().add(column);

        for (Discount discount : Discount.getAllDiscounts()) {
            allDiscountsTable.getItems().add(discount);
        }

        allDiscountsTable.setPlaceholder(new Label("No Data To Display"));
        return allDiscountsTable;
    }

    public ArrayList<Discount> getAllDiscountCodes() {
        return Discount.getAllDiscounts();
    }

    public String viewDiscount(String discountCode) throws NullPointerException {
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
        } else if (Discount.getDiscountByDiscountCode(discountCodeAfter) != null) {
            throw new IllegalArgumentException();
        } else {
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

    public void editDiscountPercent(String discountCode, String discountPercent) throws NullPointerException, Exception {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        } else if (checkDiscountPercentValidity(discountPercent)) {
            discount.setDiscountPercent(Integer.parseInt(discountPercent));
        }
    }

    public void editDiscountMaxPossibleDiscount(String discountCode, String maxPossibleDiscount) throws NullPointerException, Exception {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        } else if (checkMaxPossibleDiscountValidity(maxPossibleDiscount)) {
            discount.setMaxPossibleDiscount(Double.parseDouble(maxPossibleDiscount));
        }
    }

    public void editDiscountPerCustomer(String discountCode, String discountPerCustomer) throws NullPointerException, Exception {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        } else if (checkDiscountPercentValidity(discountPerCustomer)) {
            discount.setDiscountPerCustomer(Integer.parseInt(discountPerCustomer));
        }
    }

    public void editDiscountIncludingCustomers(String discountCode, ArrayList<String> customersUsername) throws Exception{
        if (checkCustomersValidity(customersUsername) && checkDiscountCodeValidity(discountCode)) {
            Discount discount = Discount.getDiscountByDiscountCode(discountCode);
            discount.setIncludingCustomers(customersUsername);
        }
    }

    private boolean checkDiscountCodeValidity(String discountCode) throws IllegalArgumentException {
        if (discountCode.trim().equals("")) {
            throw new IllegalArgumentException("Invalid Discount Code");
        } else {
            return true;
        }
    }

    private boolean checkDiscountPercentValidity(String discountPercent) throws Exception {
        try {
            Double.parseDouble(discountPercent);
            return true;
        } catch (Exception e) {
            throw new Exception("Invalid Discount Percent");
        }
    }

    private boolean checkMaxPossibleDiscountValidity(String maxPossibleDiscount) throws Exception {
        try {
            Double.parseDouble(maxPossibleDiscount);
            return true;
        } catch (Exception e) {
            throw new Exception("Invalid Maximum Possible Discount");
        }
    }

    private boolean checkDiscountPerCustomerValidity(String discountPerCustomer) throws IllegalArgumentException {
        if (discountPerCustomer.trim().equals("") || !(discountPerCustomer.matches("\\d+"))) {
            throw new IllegalArgumentException("Invalid number of discount user per customer.");
        } else {
            return true;
        }
    }

    private boolean checkCustomersValidity(ArrayList<String> customersUsername) throws IllegalArgumentException{
        for (String s : customersUsername) {
            if (Account.getAccountByUsername(s) == null || !(Account.getAccountByUsername(s) instanceof Customer)) {
                throw new IllegalArgumentException("Invalid Customer Username.");
            }
        }
        return true;
    }

    public void removeDiscount(String discountCode) throws NullPointerException {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null) {
            throw new NullPointerException();
        }
        Discount.removeDiscount(discount);
    }

    public TableView getAllRequestsTable(TableView allRequests) {
        TableColumn<String, Request> column1 = new TableColumn<>("Request ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("requestId"));

        TableColumn<RequestType, Request> column2 = new TableColumn<>("Request Type");
        column2.setCellValueFactory(new PropertyValueFactory<>("requestType"));

        allRequests.getColumns().addAll(column1, column2);
        for (Request request : Request.getAllRequests()) {
            allRequests.getItems().add(request);
        }
        allRequests.setPlaceholder(new Label("No Data to display"));
        return allRequests;
    }

    public ArrayList<String> getAllRequests() {
        ArrayList<Request> allRequests = Request.getAllRequests();
        ArrayList<String> allRequestIds = new ArrayList<>();
        for (Request request : allRequests) {
            allRequestIds.add(request.getRequestId());
        }
        return allRequestIds;
    }

    public String getDetailsOfRequest(String requestId) throws NullPointerException {
        Request request = Request.getRequestById(requestId);
        if (request == null) {
            throw new NullPointerException();
        }
        return request.toString();
    }

    public void acceptRequest(String requestId) throws NullPointerException, IllegalArgumentException {
        Request request = Request.getRequestById(requestId);
        if (request == null) {
            throw new NullPointerException();
        } else {
            request.acceptRequest();
            Request.removeRequest(request);
        }
    }

    public void declineRequest(String requestId) throws NullPointerException {
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
        } else {
            throw new NullPointerException();
        }
    }

    public void addSpecialFeature(Category category, String specialFeature) throws IllegalArgumentException {
        if (category.getSpecialFeatures().contains(specialFeature)) {
            throw new IllegalArgumentException();
        } else {
            category.addSpecialFeature(specialFeature);
        }
    }

    public void removeCategory(String categoryName) throws NullPointerException {
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
