package Model.Product;

import Model.Account.Customer;
import Model.Account.Discount;
import Model.Account.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Product {
    private static ArrayList<Product> allProducts = new ArrayList<>();
    private String productId;
    private ProductStatus productStatus;
    private String productName;
    private String companyName;
    private double price;
    private int existingNumber;
    private Category productCategory;
    private String explanations;
    private Discount discount;
    private ArrayList<Score> allScores;
    private ArrayList<Comment> productComments;
    private Seller productSeller;
    private int visitNumber;
    private Date timeOfCreation;
    private ArrayList<Customer> productBuyers;
    private HashMap<String, Integer> specialFeatures;

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
                    Seller seller, int existingNumber) {
        this.productId = productId;
        this.productStatus = productStatus;
        this.productName = productName;
        this.companyName = companyName;
        this.price = price;
        this.productSeller = seller;
        this.existingNumber = existingNumber;
        specialFeatures = new HashMap<>();
        timeOfCreation = new Date();
        allProducts.add(this);
    }

    public Product() {
        this("", null, "", "", 0, null, 0);
    }

    public HashMap<String, Integer> getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(HashMap<String, Integer> specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public void setASpecialFeature(String feature, int value) throws Exception {
        if (specialFeatures.containsKey(feature)){
            specialFeatures.put(feature, value);
        }
        else {
            throw new Exception("This product doesn't have such a feature");
        }
    }

    public int getValueOfAFeature(String feature) throws Exception {
        if (specialFeatures.containsKey(feature)){
            return specialFeatures.get(feature);
        }
        else {
            throw new Exception("This product doesn't have such a feature");
        }
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

    public static Product getProductByID(String ID){
        for (Product product : allProducts) {
            if (product.productId.equals(ID)){
                return product;
            }
        }
        return null;
    }

    public static void removeProduct(Product product) {
        allProducts.remove(product);
    }

    public void digest(){
        System.out.printf("%s: %s%n%s: %f%n%s: %d %s: %f%n%s: %s%n%s: %s%n%s: %f%n%n",
                "explanations", explanations,
                "price", price,
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
                ", price=" + price +
                ", score=" + getTotalScore() +
                ", 'does" + (existingNumber != 0 ? "" :  "not") + "exist'" +
                '}';
    }
}
