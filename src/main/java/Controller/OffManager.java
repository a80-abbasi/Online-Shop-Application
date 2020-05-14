package Controller;

import Model.Product.Product;

public class OffManager {



    public Product getProductById(String productId) throws NullPointerException{
        Product product = Product.getProductByID(productId);
        if (product == null) {
            throw new NullPointerException();
        }
        return product;
    }
}
