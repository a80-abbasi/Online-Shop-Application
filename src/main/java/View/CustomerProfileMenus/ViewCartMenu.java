package View.CustomerProfileMenus;

import Model.Product.Product;
import View.Menu;
import View.ProductMenus.ProductMenu;
import View.ProductsMenus.ProductsMenu;


import java.util.ArrayList;
import java.util.HashMap;

public class ViewCartMenu extends Menu {
    public ViewCartMenu(Menu parentMenu) {
        super("View Cart Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getShowProductsMenu());
        subMenus.add(new ProductMenu(this));
        subMenus.add(getIncreaseMenu());
        subMenus.add(getDecreaseMenu());
        subMenus.add(getShowTotalPriceMenu());
        subMenus.add(new PurchaseMenu(this));
        this.submenus = subMenus;
    }

    @Override
    public void execute() {
        show();
        ProductsMenu.goToProductMenu(this, parentMenu, submenus, (ProductMenu) submenus.get(1));
    }

    private Menu getShowProductsMenu() {
        return new Menu("Show Products Menu", this) {
            @Override
            public void execute() {
                HashMap<Product, Integer> cart = productsManager.getCart();
                for (Product product : cart.keySet()) {
                    product.digest();
                    System.out.println("you have " + cart.get(product) + " of this product in your cart\n");
                }
                parentMenu.execute();
            }
        };
    }

    private Menu getIncreaseMenu() {
        return new Menu("Increase Product", this) {
            @Override
            public void execute() {
                Product product = ProductsMenu.getProduct();
                if (product != null) {
                    System.out.println("Enter the number you want to increase:");
                    String number = scanner.nextLine().trim();
                    if (number.matches("\\d+")) {
                        productsManager.addProductToCart(product, Integer.parseInt(number));
                    }
                    else {
                        System.out.println("Invalid number");
                    }
                }
                parentMenu.execute();
            }
        };
    }

    private Menu getDecreaseMenu() {
        return new Menu("Decrease Product", this) {
            @Override
            public void execute() {
                Product product = ProductsMenu.getProduct();
                if (product != null) {
                    System.out.println("Enter the number you want to decrease:");
                    String number = scanner.nextLine().trim();
                    if (number.matches("\\d+")) {
                        productsManager.addProductToCart(product, -Integer.parseInt(number));
                    }
                    else {
                        System.out.println("Invalid number");
                    }
                }
                parentMenu.execute();
            }
        };
    }

    private Menu getShowTotalPriceMenu() {
        return new Menu("Show TotalPrice", this) {
            @Override
            public void execute() {
                System.out.println("Total price of your order:  " + productsManager.getTotalPrice());
            }
        };
    }
}
