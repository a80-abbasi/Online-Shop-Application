package Model;

import java.util.ArrayList;

public class Product {
    private String productId;
    private ProductStatus productStatus;
    private String productName;
    private String companyName;
    private double price;
    private Seller seller;
    private String existenceStatus;
    private Category productCategory;
    private String explanations;
    private double productRate;
    private ArrayList<String> opinionsForProduct;

    {
        opinionsForProduct = new ArrayList<>();
    }

    public Product (String productId, ProductStatus productStatus, String productName, String companyName, double price, Seller seller, String existenceStatus) {
        this.productId = productId;
        this.productStatus = productStatus;
        this.productName = productName;
        this.companyName = companyName;
        this.price = price;
        this.seller = seller;
        this.existenceStatus = existenceStatus;
    }
}
