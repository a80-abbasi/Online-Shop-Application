package View;

import Model.Product.Product;

import java.util.ArrayList;

public class ProductsMenu extends Menu {
    public ProductsMenu(Menu parentMenu) {
        super("Products Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getViewCategoriesMenu());
        subMenus.add(getShowProductsMenu());
    }

    private Menu getViewCategoriesMenu(){
        return new Menu("View Categories", this) {
            @Override
            public void execute() {
                manager.showAllCategories();
                parentMenu.execute();
            }
        };
    }

    private Menu getShowProductsMenu(){
        return new Menu("Show Products", this) {
            @Override
            public void execute() {
                ArrayList<Product> sortedFilteredProducts = manager.showProducts();
                for (Product product : sortedFilteredProducts) {
                    System.out.println(product);
                    System.out.println();
                }
            }
        };
    }
}
