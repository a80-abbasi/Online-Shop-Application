package Model.Account;

import java.util.ArrayList;

public class Discount {
    private static ArrayList<Discount> allDiscounts;
    private String discountCode;
    private String startTime;
    private String endTime;
    private int discountPercent;
    private double maxPossibleDiscount;
    private int discountPerCustomer; // todo:
    private ArrayList<Customer> includingCustomers; //todo:

    {
        allDiscounts = new ArrayList<>();
        includingCustomers = new ArrayList<>();
    }

    public Discount (String discountCode, String startTime, String endTime, int discountPercent, int maxPossibleDiscount,
                     int discountPerCustomer) {
        this.discountCode = discountCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discountPercent = discountPercent;
        this.maxPossibleDiscount = maxPossibleDiscount;
        this.discountPerCustomer = discountPerCustomer;
    }

    public static void setAllDiscounts(ArrayList<Discount> allDiscounts) {
        Discount.allDiscounts = allDiscounts;
    }

    public double calculateTotalDiscount(double money){
        double discountAmount = money * discountPercent;
        return Math.min(discountAmount, maxPossibleDiscount);
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getMaxPossibleDiscount() {
        return maxPossibleDiscount;
    }

    public ArrayList<Customer> getIncludingCustomers() {
        return includingCustomers;
    }

    public void setIncludingCustomers(ArrayList<Customer> includingCustomers) {
        this.includingCustomers = includingCustomers;
        allDiscounts.add(this);
    }

    public void setDiscountPerCustomer(int discountPerCustomer) {
        this.discountPerCustomer = discountPerCustomer;
    }

    public void setMaxPossibleDiscount(double maxPossibleDiscount) {
        this.maxPossibleDiscount = maxPossibleDiscount;
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

    public static void removeDiscount(Discount discount) {
        allDiscounts.remove(discount);
    }
}
