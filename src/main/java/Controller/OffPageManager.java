package Controller;

import Model.Account.Off;
import Model.Product.Product;

import java.util.ArrayList;

public class OffPageManager {

    public void showOffs() {
        ArrayList<Off> allOffs = Off.getAllOffs();
        System.out.println("products \t" + "price before off \t" + "price after off \t" + "off Id \t" + "off start time \t" + "off end time \t" );
        for (Off off : allOffs) {
            for (Product product : off.getProducts()) {

            }
        }
    }

    public void showProduct(String productId) {
        Product product = Product.getProductByName(productId);
    }
}
