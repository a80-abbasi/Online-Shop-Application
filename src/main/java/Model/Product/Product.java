package Model.Product;

import Model.Account.Seller;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Product {
    private String productId;
    private ProductStatus productStatus;
    private String productName;
    private String companyName;
    private double price;
    private boolean existenceStatus;
    private Category productCategory;
    private ArrayList<Score> allScores;
    private ArrayList<Comment> productComments;
    private ArrayList<Seller> productSellers;
    private int visitNumber;
    private static Comparable<Product> currentSortMode;
    private LocalDateTime timeOfCreation;

    {
        productComments = new ArrayList<>();
        productSellers = new ArrayList<>();
    }

    public Product (String productId, ProductStatus productStatus, String productName, String companyName, double price,
                    Seller seller, boolean existenceStatus) {
        this.productId = productId;
        this.productStatus = productStatus;
        this.productName = productName;
        this.companyName = companyName;
        this.price = price;
        this.productSellers.add(seller);
        this.existenceStatus = existenceStatus;
        timeOfCreation = LocalDateTime.now();
    }

    public Product() {
        this("", null, "", "", 0, null, false);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isExistenceStatus() {
        return existenceStatus;
    }

    public void setExistenceStatus(boolean existenceStatus) {
        this.existenceStatus = existenceStatus;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public ArrayList<Score> getAllScores() {
        return allScores;
    }

    public void setAllScores(ArrayList<Score> allScores) {
        this.allScores = allScores;
    }

    public ArrayList<Comment> getProductComments() {
        return productComments;
    }

    public void setProductComments(ArrayList<Comment> productComments) {
        this.productComments = productComments;
    }

    public ArrayList<Seller> getProductSellers() {
        return productSellers;
    }

    public void setProductSellers(ArrayList<Seller> productSellers) {
        this.productSellers = productSellers;
    }

    public int getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(int visitNumber) {
        this.visitNumber = visitNumber;
    }

    public static Comparable<Product> getCurrentSortMode() {
        return currentSortMode;
    }

    public static void setCurrentSortMode(Comparable<Product> currentSortMode) {
        Product.currentSortMode = currentSortMode;
    }

    public LocalDateTime getTimeOfCreation() {
        return timeOfCreation;
    }

    public double getTotalScore() {
        if (allScores.size() == 0)
            return 0;
        double productScore = 0;
        for (Score score : allScores) {
            productScore += score.getScore().score;
        }
        return productScore / allScores.size();
    }
}
