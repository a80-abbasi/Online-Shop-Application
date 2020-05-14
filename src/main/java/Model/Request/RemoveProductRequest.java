package Model.Request;

import Model.Product.Product;

public class RemoveProductRequest extends Request {
    private Product product;

    public RemoveProductRequest(Product product) {
        super("remove_product_" + allRequests.size());
        this.product = product;
    }

    @Override
    public void acceptRequest() {
        Product.removeProduct(product);
    }

}
