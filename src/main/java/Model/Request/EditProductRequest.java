package Model.Request;

import Model.Product.Product;

import java.util.HashMap;

public class EditProductRequest extends Request {
    private Product product;
    private HashMap<String, String> fieldChanges;

    {
        fieldChanges = new HashMap<>();
    }

    public EditProductRequest(Product product, HashMap<String, String> fieldChanges) {
        super("edit_product_" + allRequests.size(), RequestType.Editing_Product_Request);
        this.product = product;
        this.fieldChanges = fieldChanges;
    }

    @Override
    public void acceptRequest() {

    }

    @Override
    public String toString() {
        return null;
        //todo: completing
    }
}
