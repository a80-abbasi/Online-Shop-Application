package Model.Request;

import Model.Account.Seller;
import Model.Product.Product;
import Model.Product.ProductStatus;

import java.util.ArrayList;

public class AddProductRequest extends Request {
    private static ArrayList<AddProductRequest> allAddProductRequest = new ArrayList<>();
    private String productId;
    private ProductStatus productStatus;
    private String productName;
    private String companyName;
    private double price;
    private int existingNumber;
    private Seller productSeller;

    public AddProductRequest(String productId, ProductStatus productStatus, String productName, String companyName,
                             double price, int existingNumber, Seller productSeller) {
        super("add_product_" + allRequests.size(), RequestType.Adding_Product_Request);
        allAddProductRequest.add(this);
        this.setProductStatus(ProductStatus.WAITING_TO_PRODUCE);
    }

    public static ArrayList<AddProductRequest> getAllAddProductRequest() {
        return allAddProductRequest;
    }

    public static void setAllAddProductRequest(ArrayList<AddProductRequest> allAddProductRequest) {
        AddProductRequest.allAddProductRequest = allAddProductRequest;
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
        if (Product.getProductByID(productId) == null) {
            new Product(productId, ProductStatus.CONFIRMED, productName, companyName, price, productSeller, existingNumber);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "AddProductRequest{" +
                "productId ='" + productId + '\'' +
                ", productStatus = " + productStatus +
                ", productName = '" + productName + '\'' +
                ", companyName = '" + companyName + '\'' +
                ", price = " + price +
                ", existingNumber = " + existingNumber +
                ", productSeller = " + productSeller +
                ", requestId = '" + requestId + '\'' +
                '}';
    }
}
