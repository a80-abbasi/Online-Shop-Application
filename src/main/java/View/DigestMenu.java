package View;

import Model.Product.Product;

public class DigestMenu extends Menu {
    private Product product;

    public DigestMenu(Menu parentMenu, Product product) {
        super("Digest Menu", parentMenu);
        this.product = product;
    }

    @Override
    public void show() {
        product.digest();
        super.show();
    }
}
