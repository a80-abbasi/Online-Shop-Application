package View.SellerProfileMenus;

import Controller.SellerProfileManager;
import Model.Request.AddProductRequest;
import View.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class AddProductMenu extends Menu {
    private SellerProfileManager sellerProfileManager;
    private AddProductRequest addProductRequest;

    public AddProductMenu(Menu parentMenu, SellerProfileManager sellerProfileManager) {
        super("Add Product Menu", parentMenu);
        this.sellerProfileManager = sellerProfileManager;
        this.addProductRequest = sellerProfileManager.addProductRequest();
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getAddProductId());
        submenus.add(getAddProductName());
        submenus.add(getAddCompanyName());
        submenus.add(getAddPrice());
        submenus.add(getAddExistingNumberOfProduct());
        submenus.add(getAddSellerOfProduct());
        this.setSubMenus(submenus);
    }

    private Menu getAddProductId() {
        return new Menu("Add ProductId", this) {
            @Override
            public void execute() {
                System.out.println("Enter the Product ID:");
                String productId = scanner.nextLine();
                try {
                    sellerProfileManager.addProductId(addProductRequest, productId);
                    System.out.println("Product ID " + productId + " successfully added to your request.");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("There is a Product with this ID.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getAddProductName() {
        return new Menu("Add Product Name", this) {
            @Override
            public void execute() {
                System.out.println("Enter Product Name:");
                String productName = scanner.nextLine();
                sellerProfileManager.addProductName(addProductRequest, productName);
                System.out.println("Product Name " + productName + " successfully added to your request.");
                this.parentMenu.execute();
            }
        };
    }

    private Menu getAddCompanyName() {
        return new Menu("Add Company Name", this) {
            @Override
            public void execute() {
                System.out.println("Enter Company Name:");
                String companyName = scanner.nextLine();
                sellerProfileManager.addProductCompanyName(addProductRequest, companyName);
                System.out.println("Company Name " + companyName + " successfully added to your request");
                this.parentMenu.execute();
            }
        };
    }

    private Menu getAddPrice() {
        return new Menu("Add Price", this) {
            @Override
            public void execute() {
                System.out.println("Enter Product Price:");
                String productPrice = scanner.nextLine();
                try {
                    sellerProfileManager.addProductPrice(addProductRequest, productPrice);
                    System.out.println("Product Price " + productPrice + " successfully added to your request");
                }
                catch (InputMismatchException e) {
                    System.out.println("You must enter a number.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getAddExistingNumberOfProduct() {
        return new Menu("Add Existing Number of Product", this) {
            @Override
            public void execute() {
                System.out.println("Enter the Number Of Existing Products:");
                String existingNumber = scanner.nextLine();
                try {
                    sellerProfileManager.addExistingNumberOfProduct(addProductRequest, existingNumber);
                    System.out.println("Existing Number Of Product " + existingNumber + " successfully added to your request.");
                }
                catch (InputMismatchException e) {
                    System.out.println("You must enter an integer number.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getAddSellerOfProduct() {
        return new Menu("Add Seller of Product", this) {
            @Override
            public void execute() {
                System.out.println("Enter Seller Username:");
                String sellerUsername = scanner.nextLine();
                try {
                    sellerProfileManager.addProductSeller(addProductRequest, sellerUsername);
                    System.out.println("Product Seller " + sellerUsername + " successfully added to your request.");
                }
                catch (NullPointerException n) {
                    System.out.println("There is no Seller with this username.");
                }
                catch (InputMismatchException i) {
                    System.out.println("This username doesn't belong to a seller.");
                }
                this.parentMenu.execute();
            }
        };
    }
}
