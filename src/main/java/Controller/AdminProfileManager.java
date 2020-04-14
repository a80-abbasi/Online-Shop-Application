package Controller;

import Model.Account.Account;
import Model.Account.Admin;
import Model.Account.Discount;
import Model.Product.Category;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminProfileManager extends ProfileManager{
    private Admin admin;
    private Scanner scanner;

    public AdminProfileManager(Admin admin) {
        this.admin = admin;
    }

    public void viewPersonalInfo() {

    }

    public void editFieldOfProfile(String field) {

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


    public void getDetailsOfRequest(String requestId) {

    }

    public void acceptRequest(String requestId) {

    }

    public void declineRequest(String requestId) {

    }


    public void editCategory(Category category) {

    }

    public void addCategory(Category category) {

    }

    public void removeCategory(Category category) {

    }
}
