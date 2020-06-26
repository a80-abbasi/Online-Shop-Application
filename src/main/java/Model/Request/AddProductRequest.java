package Model.Request;

import Model.Account.Seller;
import Model.Product.Category;
import Model.Product.Product;
import Model.Product.ProductStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class AddProductRequest extends EditAddProductRequest {
    private static ArrayList<AddProductRequest> allAddProductRequest = new ArrayList<>();

    public AddProductRequest() {
        super("add_product_" + allRequests.size(), RequestType.Adding_Product_Request);
        allAddProductRequest.add(this);
        this.setProductStatus(ProductStatus.WAITING_TO_PRODUCE);
    }

    public AddProductRequest(String productID, String productName, String productCompanyName, double productPrice,
                             int productExistingNumber, String productExplanations, String productImageAddress,
                             Category productCategory, HashMap<String, Integer> productSpecialFeatures, Seller productSeller) {
        super("add_product_" + allRequests.size(), RequestType.Adding_Product_Request);
        this.productId = productID;
        this.productName = productName;
        this.productCompanyName = productCompanyName;
        this.productPrice = productPrice;
        this.productExistingNumber = productExistingNumber;
        this.productExplanations = productExplanations;
        this.productImageAddress = productImageAddress;
        this.productCategory = productCategory;
        this.productSpecialFeatures = productSpecialFeatures;
        this.productSeller = productSeller;
        allAddProductRequest.add(this);
    }

    public static ArrayList<AddProductRequest> getAllAddProductRequest() {
        return allAddProductRequest;
    }

    public static void setAllAddProductRequest(ArrayList<AddProductRequest> allAddProductRequest) {
        AddProductRequest.allAddProductRequest = allAddProductRequest;
    }

    @Override
    public void acceptRequest() throws IllegalArgumentException{
        if (Product.getProductByID(productId) == null) {
            Product product = new Product(productId, ProductStatus.CONFIRMED, productName, productCompanyName,
                    productPrice, productExistingNumber, productExplanations, productImageAddress,
                    productCategory, productSpecialFeatures, productSeller);
            productCategory.addProductToCategory(product);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "AddProductRequest{" +
                "productId='" + productId + '\'' +
                ", productStatus=" + productStatus +
                ", productName='" + productName + '\'' +
                ", companyName='" + productCompanyName + '\'' +
                ", price=" + productPrice +
                ", existingNumber=" + productExistingNumber +
                ", productSeller=" + productSeller +
                ", productCategory=" + productCategory +
                ", specialFeatures=" + productSpecialFeatures +
                ", requestId='" + requestId + '\'' +
                ", requestType=" + requestType +
                '}';
    }

    @Override
    public void remove() {
        allAddProductRequest.remove(this);
    }
}
