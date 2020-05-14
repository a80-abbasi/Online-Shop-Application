package View.SellerProfileMenus;

import Controller.SellerProfileManager;
import Model.Account.Seller;
import Model.Product.ProductStatus;
import View.Menu;
import View.ViewPersonalInfoMenu;

import java.util.ArrayList;

public class SellerProfileMenu extends Menu {
    private Seller seller;
    private SellerProfileManager sellerProfileManager;

    public SellerProfileMenu(Seller seller, Menu parentMenu) {
        super("Seller Profile Menu", parentMenu);
        this.seller = seller;
        this.sellerProfileManager = new SellerProfileManager(seller);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(new ViewPersonalInfoMenu(parentMenu, sellerProfileManager));
        subMenus.add(getViewCompanyInformationMenu());
        subMenus.add(getViewSalesHistoryMenu());
        subMenus.add(new ManageProductsMenu(parentMenu, sellerProfileManager));
        subMenus.add(getAddProductMenu());
        subMenus.add(getRemoveProductMenu());
        subMenus.add(getShowCategoriesMenu());
        subMenus.add(new ManageOffsMenu(parentMenu, sellerProfileManager));
        subMenus.add(getViewBalanceMenu());
        this.setSubMenus(subMenus);
    }

    public Menu getViewCompanyInformationMenu() {
        return new Menu("View Company Information Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println(sellerProfileManager.getCompanyInformation());
                System.out.println("Enter (View) to view company's information or (Back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    this.show();
                    this.execute();
                }
            }
        };
    }

    public Menu getViewSalesHistoryMenu() {
        return new Menu("View Sales History Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                for (String saleHistory : sellerProfileManager.getSalesHistory()) {
                    System.out.println(saleHistory);
                }
                System.out.println("Enter (View) to view sales history or (Back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    this.show();
                    this.execute();
                }
            }
        };
    }

    public Menu getAddProductMenu() {
        return new Menu("Add Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter productId or (Back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String productId = input;
                    System.out.println("Enter product status:");
                    ProductStatus productStatus = ProductStatus.valueOf(scanner.nextLine());
                    System.out.println("Enter product name:");
                    String productName = scanner.nextLine();
                    System.out.println("Enter product company name:");
                    String companyName = scanner.nextLine();
                    System.out.println("Enter product price:");
                    double price = scanner.nextDouble();
                    System.out.println("Enter existing number of product");
                    int existingNumber = scanner.nextInt();
                    sellerProfileManager.addProduct(productId, productStatus, productName, companyName, price, existingNumber, seller);
                    this.show();
                    this.execute();
                }
            }
        };
    }

    public Menu getRemoveProductMenu() {
        return new Menu("Remove Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter productId to remove or (Back) to return:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    String productId = input;
                    sellerProfileManager.removeProduct(productId);
                    this.show();
                    this.execute();
                }
            }
        };
    }

    public Menu getShowCategoriesMenu() {
        return new Menu("Show Categories Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                for (String category : sellerProfileManager.getAllCategories()) {
                    System.out.println(category);
                }
                System.out.println("Enter (show) to view categories or (back) to return:");
            }
            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else if (input.equalsIgnoreCase("show")) {
                    this.show();
                    this.execute();
                }
            }
        };
    }

    public Menu getViewBalanceMenu() {
        return new Menu("View Balance Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println();
            }

            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
}
