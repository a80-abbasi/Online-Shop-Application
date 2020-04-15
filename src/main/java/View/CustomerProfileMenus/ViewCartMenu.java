package View.CustomerProfileMenus;

import Model.Product.Product;
import View.Menu;
import View.ProductMenus.ProductMenu;


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
        //todo: create and add purchase menu
        this.submenus = subMenus;
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
                if (chosenMenu == 1) {
                    Product product = getProduct();
                    if (product == null) {
                        execute();
                    } else {
                        ProductMenu productMenu = (ProductMenu) submenus.get(1);
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
                Product product = getProduct();
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
                Product product = getProduct();
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

    private Product getProduct(){
        System.out.println("Enter product Id");
        Product product = productsManager.getProductByID(scanner.nextLine().trim());
        if (product == null){
            System.out.println("There is no product with this Id");
            return null;
        }
        if (productsManager.hasProductInCart(product)) {
            System.out.println("You dont have this Product in your cart");
            return null;
        }
        return product;
    }
}
