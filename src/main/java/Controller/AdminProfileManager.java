package Controller;

import Model.Account.Account;
import Model.Account.Admin;
import Model.Account.Discount;
import Model.Product.Category;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class AdminProfileManager extends ProfileManager{
    private Admin admin;
    private Scanner scanner;

    public AdminProfileManager(Admin admin) {
        this.admin = admin;
    }

    public void viewPersonalInfo() {
        System.out.println("Username: " + admin.getUsername());
        System.out.println("Password: " + admin.getPassword());
        System.out.println("Name: " + admin.getName());
        System.out.println("Last Name: " + admin.getLastName());
        System.out.println("Phone Number: " + admin.getPhoneNumber());
        System.out.println("E-mail: " + admin.getEmail());
    }

    public void editFieldOfProfile(String field) {
        if (field.equalsIgnoreCase("username")) {
            throw new IllegalArgumentException();
        }
        else {
            String input = scanner.nextLine().trim();
            if (field.equalsIgnoreCase("password")) {
                admin.setPassword(input);
            }
            else if (field.equalsIgnoreCase("name")) {
                admin.setName(input);
            }
            else if (field.equalsIgnoreCase("last name")) {
                admin.setLastName(input);
            }
            else if (field.equalsIgnoreCase("phone number")) {
                admin.setPhoneNumber(input);
            }
            else if (field.equalsIgnoreCase("email")) {
                admin.setEmail(input);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    public void viewAccount(String username) {
        Account account = Account.getAccountByUsername(username);
        viewPersonalInfo();
    }

    public void changeTypeOfAccount(String username, String role) {

    }

    public void deleteUser(String username) {

    }

    public void createManagerProfile() {

    }

    public void manageAllProducts() {

    }

    public void createDiscountCode() {
        String discountCode = scanner.nextLine().trim();
        String startTime = scanner.nextLine().trim();
        String endTime = scanner.nextLine().trim();
        int discountPercent = scanner.nextInt();
        int maxPossibleDiscount = scanner.nextInt();
        int discountPerCustomer = scanner.nextInt();
        new Discount(discountCode, startTime, endTime, discountPercent, maxPossibleDiscount, discountPerCustomer);
    }

    public void viewDiscountCodes() {
        ArrayList<Discount> allDiscounts = Discount.getAllDiscounts();
        for (Discount discount : allDiscounts) {
            System.out.println(discount.getDiscountCode());
        }
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
    }

    public void manageRequests() {

    }

    public void getDetailsOfRequest(String requestId) {

    }

    public void acceptRequest(String requestId) {

    }

    public void declineRequest(String requestId) {

    }

    public void manageCategories() {

    }

    public void editCategory(Category category) {
        String editionField = scanner.nextLine().trim();
        if (editionField.equalsIgnoreCase("name")) {
            String editionName = scanner.nextLine().trim();
            category.setName(editionName);
        }

    }

    public void addCategory(Category category) {

    }

    public void removeCategory(Category category) {

    }
}
