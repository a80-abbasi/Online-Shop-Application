package Model;

import java.util.ArrayList;

public class Discount {
    private String discountCode;
    private String startTime;
    private String endTime;
    private DiscountAmount discountAmount;
    private int discountPerCustomer;
    private ArrayList<Customer> includingCustomers;

    Discount (String discountCode, String startTime, String endTime, DiscountAmount discountAmount, int discountPerCustomer) {
        this.discountCode = discountCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discountAmount = discountAmount;
        this.discountPerCustomer = discountPerCustomer;
    }
}
