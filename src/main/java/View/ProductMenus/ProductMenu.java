package View.ProductMenus;

import Model.Product.CommentsMenu;
import Model.Product.Product;
import View.Menu;
import View.ProductMenus.DigestMenu;

import java.util.ArrayList;

public class ProductMenu extends Menu {
    private Product product;
    public ProductMenu(Menu parentMenu) {
        super("Show Product By Id", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(new DigestMenu(this, product));
        subMenus.add(new CommentsMenu(this, product));

        this.setSubmenus(subMenus);
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
