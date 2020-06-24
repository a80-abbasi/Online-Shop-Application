package Model.Request;

import Model.Product.Product;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class RemoveProductRequest extends Request {
    private static ArrayList<RemoveProductRequest> allRemoveProductRequests = new ArrayList<>();
    private Product product;

    public RemoveProductRequest(Product product) {
        super("remove_product_" + allRequests.size(), RequestType.Remove_Product_Request);
        this.product = product;
        allRemoveProductRequests.add(this);
    }

    public RemoveProductRequest(){
        this(null);
    }

    public static ArrayList<RemoveProductRequest> getAllRemoveProductRequests() {
        return allRemoveProductRequests;
    }

    public static void setAllRemoveProductRequests(ArrayList<RemoveProductRequest> allRemoveProductRequests) {
        RemoveProductRequest.allRemoveProductRequests = allRemoveProductRequests;
    }

    @Override
    public void acceptRequest() {
        Product.removeProduct(product);
    }

    @Override
    public String toString() {
        return "RemoveProductRequest{" +
                "product=" + product.toString() +
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

        TableColumn<Product, RemoveProductRequest> column3 = new TableColumn<>("Product");
        column3.setCellValueFactory(new PropertyValueFactory<>("product"));

        requestDetails.getItems().add(this);
        return requestDetails;
    }

    @Override
    public void remove() {
        allRemoveProductRequests.remove(this);
    }
}
