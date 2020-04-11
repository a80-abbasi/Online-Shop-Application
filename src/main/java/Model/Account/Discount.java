package Model.Account;

import java.util.ArrayList;

public class Discount {
    private String discountCode;
    private String startTime;
    private String endTime;
    private int discountPercent;
    private int maxPossibleDiscount;
    private int discountPerCustomer;
    private ArrayList<Customer> includingCustomers;

    Discount (String discountCode, String startTime, String endTime, int discountPercent, int maxPossibleDiscount, int discountPerCustomer) {
        this.discountCode = discountCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discountPercent = discountPercent;
        this.maxPossibleDiscount = maxPossibleDiscount;
        this.discountPerCustomer = discountPerCustomer;
    }
}
