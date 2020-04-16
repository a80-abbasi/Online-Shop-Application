package View.ProductsMenus;

import Model.Product.Product;
import View.Menu;
import View.ProductMenus.ProductMenu;

import java.util.ArrayList;

public class ProductsMenu extends Menu {
    public ProductsMenu(Menu parentMenu) {
        super("Products Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getViewCategoriesMenu());
        subMenus.add(new FilteringMenu(this));
        subMenus.add(new SortingMenu(this));
        subMenus.add(getShowProductsMenu());
        subMenus.add(new ProductMenu(this));
        this.setSubMenus(subMenus);
    }

    private Menu getViewCategoriesMenu(){
        return new Menu("View Categories", this) {
            @Override
            public void execute() {
                productsManager.showAllCategories();
                parentMenu.execute();
            }
        };
    }

    private Menu getShowProductsMenu(){
        return new Menu("Show Products", this) {
            @Override
            public void execute() {
                ArrayList<Product> sortedFilteredProducts = productsManager.showProducts();
                for (Product product : sortedFilteredProducts) {
                    System.out.println(product);
                    System.out.println();
                }
                parentMenu.execute();
            }
        };
    }

    @Override
    public void execute() {
        show();
        try {
            Menu nextMenu;
            int chosenMenu = Integer.parseInt(scanner.nextLine());
            if (chosenMenu == submenus.size() + 1) {
                nextMenu = this.parentMenu;
            } else {
                if (chosenMenu == submenus.size()) {
                    System.out.println("Enter Product ID");
                    Product product = productsManager.getProductByID(scanner.nextLine().trim());
                    if (product == null) {
                        System.out.println("There is no product with this Id");
                        execute();
                    } else {
                        ProductMenu productMenu = (ProductMenu) submenus.get(chosenMenu - 1);
                        productMenu.setProduct(product);
                    }
                }
                nextMenu = submenus.get(chosenMenu - 1);
            }
            nextMenu.execute();
        } catch (Exception e) {
            System.out.println("Wrong Input\n");
            execute();
        }
    }
}
