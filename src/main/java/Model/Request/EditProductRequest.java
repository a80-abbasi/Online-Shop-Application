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
        super("edit_product_" + allRequests.size());
        this.product = product;
        this.fieldChanges = fieldChanges;
    }

    @Override
    public void acceptRequest() {

    }
}
