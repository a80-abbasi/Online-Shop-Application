package Controller;

import Model.Account.Account;
import Model.Account.BuyLog;
import Model.Account.Customer;
import Model.Account.Discount;
import Model.Product.Product;
import Model.Product.Score;
import Model.Product.ScoreEnumeration;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CustomerProfileManager extends ProfileManager{
    private Customer customer;

    public CustomerProfileManager(Customer customer) {
        super(customer);
        this.customer = customer;
    }

    public boolean isInputValidForBuyLogID(String ID) {
        for (BuyLog buyLog : customer.getBuyLogs()) {//todo:if buy log be null we will give wrong input;
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

    public int viewBalance() {
        return customer.getBalance();
    }

    /*public boolean isDiscountCodeAvailableForCustomer(Discount discount) {
        //todo: ali! complete this please!
        return true;
    }*/

    public static int getExistingNumberOfProductInStore(Product product, int number) {
        return product.getExistingNumber();
    }

    public static ArrayList<String> getReceiveFieldsForPurchase() {
        return Customer.getCustomerFieldsForPurchase();
    }

    public MyResult areNewReceivedFieldsValueValid(HashMap<String, String> receivedFields) {
        boolean valid = true;
        String message = "Your information submitted";
        if (!receivedFields.get("name").matches("\\A\\w+\\z")) {
            valid = false;
            message = "please enter a valid name";
        } else if (!receivedFields.get("name").matches("\\A\\w+\\z")) {
            valid = false;
            message = "please enter a valid lastName";
        } else if (!receivedFields.get("lastName").matches("\\A\\w+\\z")) {
            valid = false;
            message = "please enter a valid name";
        } else if (!receivedFields.get("phoneNumber").matches("\\A\\d+\\z")) {
            valid = false;
            message = "please enter a valid phoneNumber";
        } else if (!receivedFields.get("email").matches("@gmail.com\\z")) {
            valid = false;
            message = "please enter a valid email";
        } else if (!receivedFields.get("PostCode").matches("\\A\\d+\\z")) {
            valid = false;
            message = "please enter a valid PostCode";
        }
        return new MyResult(valid,message);
    }

    public ArrayList<Discount> getDiscountCodes() {
        return customer.getAllDiscountCodesForCustomer();
    }

    public static boolean isDiscountCodeValid(String discountCode) {
        Discount discount = Discount.getDiscountByDiscountCode(discountCode);
        if (discount == null){
            return false;
        }
        if (discount.getIncludingCustomers().contains((Customer) Account.getLoggedInAccount())){
            Date date = new Date();
            return date.compareTo(discount.getStartTime()) >= 0 && date.compareTo(discount.getEndTime()) <= 0;
        }
        return false;
    }

    public double costCalculator(String discountCode) {
        double lastPrice = 0;
        lastPrice += customer.getTotalPrice();
        //todo: ali! complete this!
        //lastPrice -= lastPrice * off;
        lastPrice -= lastPrice * (Discount.getDiscountByDiscountCode(discountCode).getDiscountPercent());
        return lastPrice;
    }

//    public void doingsAfterBuyProducts(double cost, String UsedDiscountCode) {
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
//    }

    public boolean canCustomerPay(double cost) {
        if (customer.getBalance() >= cost) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCustomerBuyLogsNull() {
        if (customer.getBuyLogs() == null) {
            return true;
        }
        return false;
    }

    public final class MyResult {
        private final boolean valid;
        private final String  message;

        public MyResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }

        public boolean getValid() {
            return valid;
        }

        public String getMessage() {
            return message;
        }
    }

}
