package Controller;

import Model.Account.Account;
import Model.Account.BuyLog;
import Model.Account.Customer;
import Model.Account.Discount;
import Model.Product.Product;
import Model.Product.Score;
import Model.Product.ScoreEnumeration;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerProfileManager extends ProfileManager{
    private Customer customer;

    public CustomerProfileManager(Customer customer) {
        super(customer);
        this.customer = customer;
    }

    public boolean isInputValidForBuyLogID(String ID) {
        for (BuyLog buyLog : customer.getBuyLogs()) {
            if (buyLog.getID().equals(ID));
            return true;
        }
        return false;
    }

    public int getNumberOfProductInCart(Product product, Customer customer) {
        for (Product product1 : customer.getCart().keySet()) {
            if (product1.getProductId().equals(product.getProductId())) {
                return customer.getCart().get(product1);
            }
        }
        return 0;
    }

    public HashMap<String, String> showOrdersSellerNameAndDate () {
        HashMap<String, String> sellerNameAndDate = new HashMap<>();
        for (BuyLog buyLog : customer.getBuyLogs()) {
            sellerNameAndDate.put(buyLog.getSellerName(),buyLog.getDate());
        }
        return sellerNameAndDate;
    }

    public BuyLog showOrder(String id) {
        return customer.getBuyLogByID(id);
    }

    public void rateProduct(String id, int intScore) {
        Score score = new Score(customer, Product.getProductByID(id), intScore);
        Product.getProductByID(id).getAllScores().add(score);
    }
    public ArrayList<Discount> viewDiscountCodes(Account account) {
        return null;
    }
    public int viewBalance(Account account) {
        return customer.getBalance();
    }
    public boolean isDiscountCodeAvailableForCustomer(Discount discount) {
        //todo: ali! complete this please!
        return true;
    }

    public static int getExistingNumberOfProductInStore(Product product, int number) {
        return product.getExistingNumber();
    }

    public static ArrayList<String> getReceiveFieldsForPurchase() {
        return Customer.getCustomerFieldsForPurchase();
    }

    public static boolean areNewReceivedFieldsValueValid(HashMap<String, String> receivedFields) {
        //todo: check is wrong format in received fields?(it can be null or everything)
        return true;
    }

    public ArrayList<Discount> getDiscountCodes() {
        return customer.getAllDiscountCodesForCustomer();
    }

    public static boolean isDiscountCodeValid(String discountCode) { //todo: ali! complete this please!
        return true;
        //todo: need time;
    }

    public double costCalculator(String discountCode) {
        double lastPrice = 0;
        lastPrice += customer.getTotalPrice();
        //todo: ali! complete this!
        //lastPrice -= lastPrice * off;
        lastPrice -= lastPrice * (Discount.getDiscountByDiscountCode(discountCode).getDiscountPercent());
        return lastPrice;
    }

    public void doingsAfterBuyProducts(double cost, String UsedDiscountCode) {
//        customer.getCart();
//        for (Product product : customer.getCart().keySet()) {
//            product.setExistingNumber(product.getExistingNumber());
//            product.setVisitNumber();
//            product.setProductStatus();
//        }
//        customer.setCart();
//        customer.setBalance();
//        customer.setUsedDiscounts();
        //todo: complete this please
    }

    public boolean canCustomerPay(double cost) {
        if (customer.getBalance() >= cost) {
            return true;
        } else {
            return false;
        }
    }


}
