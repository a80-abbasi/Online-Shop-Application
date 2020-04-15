package View.ProductMenus;

import Model.Product.Product;
import View.Menu;

import java.util.ArrayList;

public class DigestMenu extends Menu {
    private Product product;

    public DigestMenu(Menu parentMenu, Product product) {
        super("Digest Menu", parentMenu);
        this.product = product;
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getAddToCartMenu());
    }

    @Override
    public void show() {
        product.digest();
        super.show();
    }

    private Menu getAddToCartMenu(){
        return new Menu("AddToCart", this) {
            @Override
            public void execute() {
                if (!loginAndRegisterManager.isLogin()){
                    System.out.println("You must login first\n");
                    loginAndRegisterMenu.execute();
                }
                try {
                    productsManager.addProductToCart(product, 1);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                finally {
                    parentMenu.execute();
                }
            }
        };
    }
}
