package Model.Request;

import Model.Product.Product;
import Model.Product.ProductStatus;

import java.util.ArrayList;

public class AddProductRequest extends EditAddProductRequest {
    private static ArrayList<AddProductRequest> allAddProductRequest = new ArrayList<>();

    public AddProductRequest() {
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

    @Override
    public void acceptRequest() throws IllegalArgumentException{
        if (Product.getProductByID(productId) == null) {
            new Product(productId, ProductStatus.CONFIRMED, productName, companyName, price, productSeller, existingNumber, productCategory, productSpecialFeatures);
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
                ", companyName='" + companyName + '\'' +
                ", price=" + price +
                ", existingNumber=" + existingNumber +
                ", productSeller=" + productSeller +
                ", productCategory=" + productCategory +
                ", specialFeatures=" + productSpecialFeatures +
                ", requestId='" + requestId + '\'' +
                ", requestType=" + requestType +
                '}';
    }
}
