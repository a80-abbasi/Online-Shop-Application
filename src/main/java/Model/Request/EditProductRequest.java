package Model.Request;

import Model.Account.Seller;
import Model.Product.Product;
import Model.Product.ProductStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class EditProductRequest extends Request {
    private static ArrayList<EditProductRequest> allEditProductRequests = new ArrayList<>();
    private Product product;
    private String productId;
    private ProductStatus productStatus;
    private String productName;
    private String companyName;
    private double price;
    private int existingNumber;
    private Seller productSeller;

    public EditProductRequest(Product product) {
        super("edit_product_" + allRequests.size(), RequestType.Editing_Product_Request);
        this.setProduct(product);
        this.setProductId(product.getProductId());
        this.setProductName(product.getProductName());
        this.setProductStatus(ProductStatus.WAITING_TO_EDIT);
        this.setCompanyName(product.getCompanyName());
        this.setPrice(product.getPrice());
        this.setExistingNumber(product.getExistingNumber());
        this.setProductSeller(product.getProductSeller());
    }

    public EditProductRequest(){
        this(null);
    }

    public static ArrayList<EditProductRequest> getAllEditProductRequests() {
        return allEditProductRequests;
    }

    public static void setAllEditProductRequests(ArrayList<EditProductRequest> allEditProductRequests) {
        EditProductRequest.allEditProductRequests = allEditProductRequests;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    @Override
    public void acceptRequest() throws IllegalArgumentException{
        if (Product.getProductByID(productId) != null && (!(Product.getProductByID(productId)).equals(product))) {
            throw new IllegalArgumentException();
        }
        else {
            product.setProductId(productId);
            product.setProductName(productName);
            product.setProductStatus(ProductStatus.CONFIRMED);
            product.setCompanyName(companyName);
            product.setPrice(price);
            product.setExistingNumber(existingNumber);
            product.setProductSeller(productSeller);
        }
    }

    @Override
    public String toString() {
        return "EditProductRequest{" +
                "product=" + product +
                ", productId='" + productId + '\'' +
                ", productStatus=" + productStatus +
                ", productName='" + productName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", price=" + price +
                ", existingNumber=" + existingNumber +
                ", productSeller=" + productSeller +
                ", requestId='" + requestId + '\'' +
                ", requestType=" + requestType +
                '}';
    }
}
