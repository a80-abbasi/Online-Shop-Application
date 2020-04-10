package Model;

import java.util.ArrayList;

public class Product {
    private String productId;
    private ProductStatus productStatus;
    private String productName;
    private String companyName;
    private double price;
    private String existenceStatus;
    private Category productCategory;
    private String explanations;
    private double productRate;
    private ArrayList<Comment> productComments;
    private ArrayList<Seller> productSellers;

    {
        productComments = new ArrayList<>();
        productSellers = new ArrayList<>();
    }

    public Product (String productId, ProductStatus productStatus, String productName, String companyName, double price, Seller seller, String existenceStatus) {
        this.productId = productId;
        this.productStatus = productStatus;
        this.productName = productName;
        this.companyName = companyName;
        this.price = price;
        this.productSellers.add(seller);
        this.existenceStatus = existenceStatus;
    }
}
