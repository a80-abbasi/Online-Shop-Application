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
        this.productId = productId;
        this.productStatus = productStatus;
        this.productName = productName;
        this.companyName = companyName;
        this.price = price;
        this.existingNumber = existingNumber;
        this.productSeller = productSeller;
    }

    public static ArrayList<AddProductRequest> getAllAddProductRequest() {
        return allAddProductRequest;
    }

    public static void setAllAddProductRequest(ArrayList<AddProductRequest> allAddProductRequest) {
        AddProductRequest.allAddProductRequest = allAddProductRequest;
    }

    @Override
    public void acceptRequest() {
        new Product(productId, productStatus, productName, companyName, price, productSeller, existingNumber);
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
