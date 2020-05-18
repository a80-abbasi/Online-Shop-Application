package Model.Account;

import java.util.ArrayList;
import java.util.Date;

public class Discount {
    private static ArrayList<Discount> allDiscounts;
    private String discountCode;
    private Date startTime;
    private Date endTime;
    private int discountPercent;
    private double maxPossibleDiscount;
    private int discountPerCustomer; // todo:
    private ArrayList<Customer> includingCustomers; //todo:

    {
        allDiscounts = new ArrayList<>();
        includingCustomers = new ArrayList<>();
    }

    public Discount (String discountCode, Date startTime, Date endTime, int discountPercent, int maxPossibleDiscount,
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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

    public int getDiscountPerCustomer() {
        return discountPerCustomer;
    }

    public boolean isAvailable(){
        Date currentDate = new Date();
        return currentDate.compareTo(startTime) >= 0 && currentDate.compareTo(endTime) <= 0;
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

    @Override
    public String toString() {
        return "Discount{" +
                "discountCode='" + discountCode + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", discountPercent=" + discountPercent +
                ", maxPossibleDiscount=" + maxPossibleDiscount +
                ", discountPerCustomer=" + discountPerCustomer +
                ", includingCustomers=" + includingCustomers +
                '}';
    }
}
