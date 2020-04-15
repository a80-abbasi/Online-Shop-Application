package View.ProductMenus;

import Model.Product.Product;
import View.Menu;

import java.util.ArrayList;

public class ProductMenu extends Menu {
    private Product product;
    public ProductMenu(Menu parentMenu) {
        super("Show Product By Id", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(new DigestMenu(this, product));
        subMenus.add(getAttributesMenu());
        subMenus.add(getCompareMenu());
        subMenus.add(new CommentsMenu(this, product));

        this.setSubmenus(subMenus);
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private Menu getAttributesMenu(){
        return new Menu("Attributes", this) {
            @Override
            public void execute() {
                //todo: print attributes
            }
        };
    }

    private Menu getCompareMenu(){
        return new Menu("Compare with other products", this) {
            @Override
            public void execute() {
                Product product2 = productsManager.getProductByID(scanner.nextLine().trim());
                if (product2 == null){
                    System.out.println("There is no product with this Id");
                }
                else {
                    product.digest();
                    System.out.println();
                    product2.digest();
                }
                parentMenu.execute();
            }
        };
    }
}
