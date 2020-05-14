package Model.Request;

import Model.Account.Seller;
import Model.Product.Product;
import Model.Product.ProductStatus;

public class AddProductRequest extends Request {
    private String productId;
    private ProductStatus productStatus;
    private String productName;
    private String companyName;
    private double price;
    private int existingNumber;
    private Seller productSeller;

    public AddProductRequest(String productId, ProductStatus productStatus, String productName, String companyName,
                             double price, int existingNumber, Seller productSeller) {
        super("add_product_" + allRequests.size());
        this.productId = productId;
        this.productStatus = productStatus;
        this.productName = productName;
        this.companyName = companyName;
        this.price = price;
        this.existingNumber = existingNumber;
        this.productSeller = productSeller;
    }

    @Override
    public void acceptRequest() {
        new Product(productId, productStatus, productName, companyName, price, productSeller, existingNumber);
    }
}
