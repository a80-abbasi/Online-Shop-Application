package Model.Request;

import Model.Product.Product;
import Model.Product.ProductStatus;

import java.util.ArrayList;

public class EditProductRequest extends EditAddProductRequest {
    private static ArrayList<EditProductRequest> allEditProductRequests = new ArrayList<>();
    private Product product;

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
        this.setProductCategory(product.getProductCategory());
        this.setProductSpecialFeatures(product.getSpecialFeatures());
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
            product.setProductCategory(productCategory);
            product.setSpecialFeatures(productSpecialFeatures);
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
