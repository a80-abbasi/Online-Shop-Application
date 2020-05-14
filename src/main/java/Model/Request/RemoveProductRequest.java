package Model.Request;

import Model.Product.Product;

public class RemoveProductRequest extends Request {
    private Product product;

    public RemoveProductRequest(Product product) {
        super("remove_product_" + allRequests.size(), RequestType.Remove_Product_Request);
        this.product = product;
    }

    @Override
    public void acceptRequest() {
        Product.removeProduct(product);
    }

    @Override
    public String toString() {
        return null;
        //todo: completing
    }
}
