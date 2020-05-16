package View.CustomerProfileMenus;

import Controller.CustomerProfileManager;
import Controller.ProductsManager;
import Model.Account.Customer;
import Model.Product.Product;
import View.Menu;
import View.ProductMenus.ProductMenu;


import java.util.ArrayList;
import java.util.HashMap;

public class ViewCartMenu extends Menu {
    private Customer customer;
    private CustomerProfileManager customerProfileManager;
    public ViewCartMenu(Customer customer, Menu parentMenu) {
        super("View Cart Menu", parentMenu);
        this.customer = customer;
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getShowProductsMenu());
        subMenus.add(new ProductMenu(this));
        subMenus.add(getIncreaseMenu());
        subMenus.add(getDecreaseMenu());
        subMenus.add(getShowTotalPriceMenu());
        subMenus.add(getPurchaseMenu());
        this.submenus = subMenus;
    }

//    @Override
//    public void execute() {
//        show();
//        try {
//            Menu nextMenu;
//            int chosenMenu = Integer.parseInt(scanner.nextLine());
//            if (chosenMenu == submenus.size() + 2) {
//                nextMenu = this.parentMenu;
//            }
//            else if (chosenMenu == submenus.size() + 1) {
//                if (loginAndRegisterManager.isLogin()) {
//                    loginAndRegisterManager.logoutUser();
//                }
//                else {
//                    loginAndRegisterMenu.execute();
//                }
//                nextMenu = this;
//            }
//            else {
//                if (chosenMenu == 1) {
//                    Product product = getProduct();
//                    if (product == null) {
//                        execute();
//                    } else {
//                        ProductMenu productMenu = (ProductMenu) submenus.get(1);
//                        productMenu.setProduct(product);
//                    }
//                }
//                nextMenu = submenus.get(chosenMenu - 1);
//            }
//            nextMenu.execute();
//        } catch (Exception e) {
//            System.out.println("Wrong Input\n");
//            execute();
//        }
//    }

    private Menu getShowProductsMenu() {
        return new Menu("Show Products Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                HashMap<Product, Integer> cart = productsManager.getCart();
                for (Product product : cart.keySet()) {
                    product.digest();
                    System.out.println("you have " + cart.get(product) + " of this product in your cart\n");
                }
                System.out.println("1. Logout");
                System.out.println("2. Back");
            }
        };
    }

    private Menu getIncreaseMenu() {
        return new Menu("Increase Product", this) {
            @Override
            public void execute() {
                System.out.println(this.getName() + ":");
                Product product = getProduct();
                if (product != null) {
                    System.out.println("Enter the number you want to increase or (back) to return or (Logout) to leave your account:");
                    System.out.printf("You just have %d number of this product now!\n", customerProfileManager.getNumberOfProductInCart(product, customer));
                    while (true) {
                        String input = scanner.nextLine().trim();
                        if (input.equalsIgnoreCase("Back")) {
                            this.parentMenu.execute();
                        } else if (input.equals("Logout")) {
                            loginAndRegisterManager.logoutUser();
                        } else if (input.matches("\\d+")) {
                            int existingNumberInStore = CustomerProfileManager.getExistingNumberOfProductInStore(product, Integer.parseInt(input));
                            if (existingNumberInStore >= Integer.parseInt(input)) {
                                productsManager.addProductToCart(product, Integer.parseInt(input));
                                System.out.println("Product number increased successfully");
                                parentMenu.execute();
                            } else {
                                System.out.printf("we have just %d of this Product. Please enter another number:\n", existingNumberInStore);
                            }
                        } else {
                            System.out.println("Please enter valid number:");
                        }
                    }
                }
                System.out.println("product null!");
                parentMenu.execute();
            }
        };
    }

    private Menu getDecreaseMenu() {
        return new Menu("Decrease Product", this) {
            @Override
            public void execute() {
                System.out.println(this.getName() + ":");
                Product product = getProduct();
                if (product != null) {
                    System.out.println("Enter the number you want to decrease or (back) to return or (Logout) to leave your account:");
                    System.out.printf("You just have %d number of this product now!\n", customerProfileManager.getNumberOfProductInCart(product, customer));
                    while(true) {
                        String input = scanner.nextLine().trim();
                        if (input.equalsIgnoreCase("Back")) {
                            this.parentMenu.execute();
                        } else if (input.equals("Logout")) {
                            loginAndRegisterManager.logoutUser();
                        } else if (input.matches("\\d+")) {
                            productsManager.addProductToCart(product, Integer.parseInt(input));
                            System.out.println("Product decreased successfully");
                            this.parentMenu.execute();
                        } else {
                            System.out.println("Please enter valid number:");
                        }
                    }
                }
                this.execute();
            }
        };
    }

    private Menu getShowTotalPriceMenu() {
        return new Menu("Show TotalPrice", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Total price of your order: " + productsManager.getTotalPrice() + "$");
                System.out.println("Total Price with discount code and off: "); //todo
                System.out.println("1. Logout");
                System.out.println("2. Back");
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
            System.out.println("You don't have this Product in your cart");
            return null;
        }
        return product;
    }

    private Menu getPurchaseMenu() {
        return new Menu("Get Information Menu", this) {

            @Override
            public void execute() {
                ArrayList<String> receiveInformationFields = CustomerProfileManager.getReceiveFieldsForPurchase();
                ArrayList<String> receiveInformationFieldsValue = new ArrayList<>(receiveInformationFields.size());
                HashMap<String, String> FieldsAndNewValues = new HashMap<>();
                String discountCode = "";
                int pageNumber = 1;
                while (true) {
                    if (pageNumber == 1) {
                        System.out.println(this.getName() + ":");
                        System.out.println("In each Menu you can use (Back) to return, (Next) to go next page or (Logout) to leave your account!");
                    }
                    if(pageNumber <= receiveInformationFields.size()) {
                        System.out.println("Enter Your " + receiveInformationFields.get(pageNumber - 1) + ":");
                        String input = scanner.nextLine();
                        if (input.equals("Back")) {
                            if(pageNumber == 1) {
                                this.parentMenu.execute();
                            }
                            else {
                                pageNumber -= 1;
                            }
                        } else if(input.equals("Next")) {
                            pageNumber += 1;
                        } else if (input.equals("Logout")) {
                            loginAndRegisterManager.logoutUser();
                        } else {
                            //todo: check are inputs valid
                            receiveInformationFieldsValue.set(pageNumber - 1, input);
                            pageNumber += 1;
                        }
                    }
                    else if (pageNumber == receiveInformationFields.size() + 1) {
                        System.out.println("are this information true?(Write (Next) if they are true)");
                        for (int i = 0; i < receiveInformationFieldsValue.size(); i++) {
                            FieldsAndNewValues.put(receiveInformationFields.get(i), receiveInformationFieldsValue.get(i));
                        }
                        System.out.println(FieldsAndNewValues);
                        String input = scanner.nextLine();
                        if (input.equals("Back")) {
                            pageNumber -= 1;
                        } else if(input.equals("Next")) {
                            if (CustomerProfileManager.areNewReceivedFieldsValueValid(FieldsAndNewValues)) {
                                System.out.println("Your information submitted");
                                pageNumber += 1;
                                System.out.println("DiscountCode Menu:");
                                System.out.println("Please enter discount code if you have:");
                                while(true) {
                                    if(pageNumber == receiveInformationFields.size() + 2) {
                                        input = scanner.nextLine();
                                        if (input.equals("Back")) {
                                            pageNumber -= 1;
                                            break;
                                        } else if(input.equals("Next")) {
                                            //todo: discount code maybe be null;
                                            pageNumber += 1;
                                        } else if (input.equals("Logout")) {
                                            loginAndRegisterManager.logoutUser();
                                        } else if (CustomerProfileManager.isDiscountCodeValid(input)) {
                                            receiveInformationFieldsValue.set(pageNumber - 1, input);
                                            System.out.println("Your code submitted.");
                                            pageNumber += 1;
                                        } else {
                                            System.out.println("Code is invalid.");
                                        }
                                    } else if (pageNumber == receiveInformationFields.size() + 3) {
                                        //////////////////
                                        while(true) {
                                            if (pageNumber == receiveInformationFields.size() + 3) {
                                                System.out.println("Payment Menu:");
                                                //System.out.printf("The total cost of your products is %s$ and with discount and off is %s$ do you want to pay?(Yes)(No)\n", Double.toString(ProductsManager.getTotalPrice()), Double.toString(ProductsManager.costWithOffAndDigest(discountCode)));
                                                //todo;
                                            }
                                            input = scanner.nextLine();
                                            if (input.equals("Back")) {
                                                pageNumber -= 1;
                                                break;
                                            }else if (input.equals("No")) {
                                                ViewCartMenu.super.execute();
                                            } else if(input.equals("Yes")) {
                                                double cost = customerProfileManager.costCalculator(discountCode);
                                                if (customerProfileManager.canCustomerPay(cost)) {
                                                    System.out.println("you payed successfully, have nice day!");
                                                    //customerProfileManager.doingsAfterBuyProducts();
                                                } else {
                                                    System.out.println("You don`t have enough money! Go work harder!");
                                                    ViewCartMenu.super.execute();
                                                }
                                            } else if (input.equals("Logout")) {
                                                loginAndRegisterManager.logoutUser();
                                            } else {
                                                System.out.println("Code is invalid.");
                                            }
                                        }
                                    }
                                }
                            } else {
                                System.out.println("formats are invalid"); //todo: write a better message;
                            }
                        } else if (input.equals("Logout")) {
                            loginAndRegisterManager.logoutUser();
                        } else {
                            System.out.println("invalid message");
                        }
                    }
                }
            }
        };
    }
}
