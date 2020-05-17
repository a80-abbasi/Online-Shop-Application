package View.SellerProfileMenus;

import Controller.SellerProfileManager;
import Model.Request.EditProductRequest;
import View.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class EditProductMenu extends Menu {
    private SellerProfileManager sellerProfileManager;
    private EditProductRequest editProductRequest;

    public EditProductMenu(Menu parentMenu, SellerProfileManager sellerProfileManager) {
        super("Edit Product Menu", parentMenu);
        this.sellerProfileManager = sellerProfileManager;
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getEditProductId());
        submenus.add(getEditProductName());
        submenus.add(getEditCompanyName());
        submenus.add(getEditPrice());
        submenus.add(getEditExistingNumberOfProduct());
        submenus.add(getEditSellerOfProduct());
        this.setSubMenus(submenus);
    }

    @Override
    public void show() {
        System.out.println("Enter ID of Product you want to edit:");
        String productId = scanner.nextLine();
        try {
            this.editProductRequest = sellerProfileManager.makeNewEditProductRequest(productId);
        }
        catch (NullPointerException e) {
            System.out.println("There is no Product with this ID.");
            this.show();
        }
    }

    private Menu getEditProductId() {
        return new Menu("Edit ProductId", this) {
            @Override
            public void execute() {
                System.out.println("Enter the Product ID:");
                String productId = scanner.nextLine();
                try {
                    sellerProfileManager.editProductId(editProductRequest, productId);
                    System.out.println("Product ID " + productId + " successfully added to your request.");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("There is a Product with this ID.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getEditProductName() {
        return new Menu("Edit Product Name", this) {
            @Override
            public void execute() {
                System.out.println("Enter Product Name:");
                String productName = scanner.nextLine();
                sellerProfileManager.editProductName(editProductRequest, productName);
                System.out.println("Product Name " + productName + " successfully added to your request.");
                this.parentMenu.execute();
            }
        };
    }

    private Menu getEditCompanyName() {
        return new Menu("Edit Company Name", this) {
            @Override
            public void execute() {
                System.out.println("Enter Company Name:");
                String companyName = scanner.nextLine();
                sellerProfileManager.editProductCompanyName(editProductRequest, companyName);
                System.out.println("Company Name " + companyName + " successfully added to your request");
                this.parentMenu.execute();
            }
        };
    }

    private Menu getEditPrice() {
        return new Menu("Edit Price", this) {
            @Override
            public void execute() {
                System.out.println("Enter Product Price:");
                String productPrice = scanner.nextLine();
                try {
                    sellerProfileManager.editProductPrice(editProductRequest, productPrice);
                    System.out.println("Product Price " + productPrice + " successfully added to your request");
                }
                catch (InputMismatchException e) {
                    System.out.println("You must enter a number.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getEditExistingNumberOfProduct() {
        return new Menu("Edit Existing Number of Product", this) {
            @Override
            public void execute() {
                System.out.println("Enter the Number Of Existing Products:");
                String existingNumber = scanner.nextLine();
                try {
                    sellerProfileManager.editExistingNumberOfProduct(editProductRequest, existingNumber);
                    System.out.println("Existing Number Of Product " + existingNumber + " successfully added to your request.");
                }
                catch (InputMismatchException e) {
                    System.out.println("You must enter an integer number.");
                }
                this.parentMenu.execute();
            }
        };
    }

    private Menu getEditSellerOfProduct() {
        return new Menu("Edit Seller of Product", this) {
            @Override
            public void execute() {
                System.out.println("Enter Seller Username:");
                String sellerUsername = scanner.nextLine();
                try {
                    sellerProfileManager.editProductSeller(editProductRequest, sellerUsername);
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
