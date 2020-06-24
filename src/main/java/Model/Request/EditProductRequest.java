package Model.Request;

import Model.Product.Product;
import Model.Product.ProductStatus;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
            if (!(productCategory.getProducts().contains(product))) {
                productCategory.addProductToCategory(product);
            }
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

    @Override
    public TableView getRequestDetails() {
        TableView requestDetails = new TableView();

        TableColumn<String, Request> column1 = new TableColumn<>("Request ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("requestId"));

        TableColumn<RequestType, Request> column2 = new TableColumn<>("Request Type");
        column2.setCellValueFactory(new PropertyValueFactory<>("requestType"));

        //todo

        return requestDetails;
    }

    @Override
    public void remove() {
        allEditProductRequests.remove(this);
    }
}
