package Model.Product;

import Model.Account.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Product {
    private static ArrayList<Product> allProducts = new ArrayList<>();
    private String productId;
    private String imageAddress;
    private ProductStatus productStatus; 
    private String productName;
    private String companyName;
    private double price;
    private int existingNumber;
    private Category productCategory;
    private HashMap<String, Integer> specialFeatures;
    private String explanations;
    private Discount discount;
    private ArrayList<Score> allScores;
    private ArrayList<Comment> productComments;
    private Seller productSeller;
    private int visitNumber;
    private Date timeOfCreation;
    private ArrayList<Customer> productBuyers;
    private Off off;

    private static ArrayList<String> productFields = new ArrayList<>();
    static {
        productFields.add("productId");
        productFields.add("productStatus");
        productFields.add("productName");
        productFields.add("companyName");
        productFields.add("price");
        productFields.add("ProductSeller");
        productFields.add("existingNumber");
    }

    {
        allScores = new ArrayList<>();
        productComments = new ArrayList<>();
        productBuyers = new ArrayList<>();
    }

    public Product (String productId, ProductStatus productStatus, String productName, String companyName, double price,
                    int existingNumber, String explanations, String productImageAddress, Category productCategory,
                    HashMap<String, Integer> specialFeatures, Seller productSeller) {
        this.productId = productId;
        this.productStatus = productStatus;
        this.productName = productName;
        this.companyName = companyName;
        this.price = price;
        this.existingNumber = existingNumber;
        this.explanations = explanations;
        this.setImageAddress(productImageAddress);
        this.productCategory = productCategory;
        this.specialFeatures = specialFeatures;
        this.productSeller = productSeller;
        timeOfCreation = new Date();
        allProducts.add(this);
    }

    public Product() {
        this("", null, "", "", 0, null, 0, null, null);
    }

    public HashMap<String, Integer> getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(HashMap<String, Integer> specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public Image getImage() {
        return new Image(imageAddress);
    }

    public void setImageAddress(Image image) {
        this.imageAddress = image.getUrl();
    }

    public void addRate(Customer customer, int score){
        Score newScore = new Score(customer, this, score);
        this.allScores.add(newScore);
    }

    public void setASpecialFeature(String feature, int value) throws Exception {
        if (specialFeatures.containsKey(feature)){
            specialFeatures.put(feature, value);
        }
        else {
            throw new Exception("This product doesn't have such a feature");
        }
    }

    public void addAComment(Comment comment){
        productComments.add(comment);
    }

    public int getValueOfAFeature(String feature) throws Exception {
        if (specialFeatures.containsKey(feature)){
            return specialFeatures.get(feature);
        }
        else {
            throw new Exception("This product doesn't have such a feature");
        }
    }

    public void removeSpecialFeature(String specialFeature) {
        if (specialFeatures.keySet().contains(specialFeature)) {
            specialFeatures.remove(specialFeature);
        }
    }

    public Off getOff() {
        return off;
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

    public int getExistingNumber() {
        return existingNumber;
    }

    public void setExistingNumber(int existingNumber) {
        this.existingNumber = existingNumber;
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

    public Seller getProductSeller() {
        return productSeller;
    }

    public void setProductSeller(Seller productSeller) {
        this.productSeller = productSeller;
    }

    public int getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(int visitNumber) {
        this.visitNumber = visitNumber;
    }

    public Date getTimeOfCreation() {
        return timeOfCreation;
    }

    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public String getExplanations() {
        return explanations;
    }

    public void setExplanations(String explanations) {
        this.explanations = explanations;
    }

    public static void setAllProducts(ArrayList<Product> allProducts) {
        Product.allProducts = allProducts;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setTimeOfCreation(Date timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public ArrayList<Customer> getProductBuyers() {
        return productBuyers;
    }

    public static ArrayList<String> getProductFields() {
        return productFields;
    }

    public double getTotalScore() {
        if (allScores.size() == 0)
            return 0;
        double productScore = 0;
        for (Score score : allScores) {
            productScore += score.getScore();
        }
        return productScore / allScores.size();
    }

    public double getPriceWithOff(){
        if (off != null && off.isAvailable()){
            return price * (100 - off.getOffAmount()) / 100;
        }
        return price;

    }

    public void setOff(Off off) {
        this.off = off;
    }

    public void setProductBuyers(ArrayList<Customer> productBuyers) {
        this.productBuyers = productBuyers;
    }

    //todo: checking this
    public void removeCategory(Category category) {
        productCategory = null;
        specialFeatures.clear();
    }

    public static void setProductFields(ArrayList<String> productFields) {
        Product.productFields = productFields;
    }

    public static Product getProductByID(String ID){
        for (Product product : allProducts) {
            if (product.productId.equals(ID)){
                return product;
            }
        }
        return null;
    }

    //todo: checking this, removing from offs, discounts and categories?
    public static void removeProduct(Product product) {
        allProducts.remove(product);
    }

    public void digest(){
        System.out.printf("%s: %s%n%s: %f%n%s: %d%n%s: %f %s: %f%n%s: %s%n%s: %s%n%s: %f%n%n",
                "explanations", explanations,
                "price", price,
                "Off Percentage", (off == null || !off.isAvailable() ? 0 : off.getOffAmount()),
                "discount percentage", discount.getDiscountPercent(), "max discount amount", discount.getMaxPossibleDiscount(),
                "category", productCategory.getName(),
                "seller", productSeller.getName(),
                "average score", getTotalScore());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productId='" + productId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", Original Price=" + price +
                ", price after off=" + getPriceWithOff() +
                ", score=" + getTotalScore() +
                ", 'does" + (existingNumber != 0 ? "" :  "not") + "exist'" +
                '}';
    }
}
