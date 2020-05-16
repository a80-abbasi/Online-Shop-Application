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

    public ArrayList<Product> showProducts(Account account) {
        return null;
    }
    public ArrayList<String> viewProduct(String id) {
        return null;
    }
    public int showTotalPrice(Account account)  {
        return 0;
    }
    public boolean purchase(ArrayList<String> properties, String discountCode) {
        return true;
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
    public boolean isDiscountCodeAvailableForCustomer(Account account, Discount discount) {
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

    public static boolean isDiscountCodeValid(String discountCode) { //todo: ali! complete please
        Discount.getDiscountByDiscountCode(discountCode);
        //if () {
        return true;
        //} else {
        //return false;
        //}
        //todo
    }

    public static double costCalculator() {
        return 0;
        //todo;
    }

    public static void doingsAfterBuyProducts(String discount) {
        return;
        //todo;
    }

    public static boolean canCustomerPay() {
        return true;
        //todo;
    }


}
