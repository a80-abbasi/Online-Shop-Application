package Model.Account;

import java.util.ArrayList;

public class Discount {
    private static ArrayList<Discount> allDiscounts;
    private String discountCode;
    private String startTime;
    private String endTime;
    private int discountPercent;
    private int maxPossibleDiscount;
    private int discountPerCustomer;
    private ArrayList<Customer> includingCustomers;

    {
        allDiscounts = new ArrayList<>();
        includingCustomers = new ArrayList<>();
    }

    public Discount (String discountCode, String startTime, String endTime, int discountPercent, int maxPossibleDiscount, int discountPerCustomer) {
        this.discountCode = discountCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discountPercent = discountPercent;
        this.maxPossibleDiscount = maxPossibleDiscount;
        this.discountPerCustomer = discountPerCustomer;
        allDiscounts.add(this);
    }

    public String getDiscountCode() {
        return discountCode;
    }

    @Override
    public String toString() {
        return null;
    }

    public static ArrayList<Discount> getAllDiscounts() {
        return allDiscounts;
    }

    public static Discount getDiscountByDiscountCode(String discountCode) {
        for (Discount discount : allDiscounts) {
            if (discount.getDiscountCode().equals(discountCode)) {
                return discount;
            }
        }
        return null;
    }
}
