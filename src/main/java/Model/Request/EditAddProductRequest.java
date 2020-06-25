package Model.Request;

import Model.Account.Seller;
import Model.Product.Category;
import Model.Product.ProductStatus;

import java.util.HashMap;

public abstract class EditAddProductRequest extends Request {
    protected String productId;
    protected ProductStatus productStatus;
    protected String productName;
    protected String companyName;
    protected double price;
    protected int existingNumber;
    protected Seller productSeller;
    protected Category productCategory;
    protected HashMap<String, Integer> productSpecialFeatures;

    public EditAddProductRequest(String requestId, RequestType requestType) {
        super(requestId, requestType);
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public String getProductId() {
        return productId;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public String getProductName() {
        return productName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getPrice() {
        return price;
    }

    public int getExistingNumber() {
        return existingNumber;
    }

    public Seller getProductSeller() {
        return productSeller;
    }

    public HashMap<String, Integer> getProductSpecialFeatures() {
        return productSpecialFeatures;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExistingNumber(int existingNumber) {
        this.existingNumber = existingNumber;
    }

    public void setProductSeller(Seller productSeller) {
        this.productSeller = productSeller;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductSpecialFeatures(HashMap<String, Integer> productSpecialFeatures) {
        this.productSpecialFeatures = productSpecialFeatures;
    }

    public void setProductSpecialFeatureValue(String key, int value) {
        this.productSpecialFeatures.replace(key, value);
    }

    @Override
    public abstract void acceptRequest();

    @Override
    public abstract String toString();
}
