package Model.Request;

import Model.Product.Product;

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
}
