package View.SellerProfileMenus;

import Controller.SellerProfileManager;
import Model.Account.Seller;
import View.Menu;
import View.ViewPersonalInfoMenu;

import java.util.ArrayList;
import java.util.HashMap;

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
        subMenus.add(new ViewOffsMenu(parentMenu, sellerProfileManager));
        subMenus.add(getViewBalanceMenu());
        this.setSubMenus(subMenus);
    }

    public Menu getViewCompanyInformationMenu() {
        return new Menu("View Company Information Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println(sellerProfileManager.getCompanyInformation());
                System.out.println("1. Logout");
                System.out.println("2. Back");
            }
        };
    }

    public Menu getViewSalesHistoryMenu() {
        return new Menu("View Sales History Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                for (String saleHistory : sellerProfileManager.getSalesHistory()) {
                    System.out.print(saleHistory + ", ");
                    System.out.println("1. Logout");
                    System.out.println("2. Back");
                }
            }
        };
    }

    public Menu getAddProductMenu() {
        return new Menu("Add Product Menu", this) {
            @Override
            public void execute() {
                ArrayList<String> productFieldsValue = new ArrayList<>(SellerProfileManager.getAllProductFields());
                int pageNumber = 1;
                while (true) {
                    if (pageNumber == 1) {
                        System.out.println(this.getName() + ":");
                        System.out.println("In each Menu you can use (Back) to return, (Next) to go next page or (Logout) to leave your account!");
                    }
                    if(pageNumber <= SellerProfileManager.getAllProductFields().size()) {
                        System.out.println("Enter " + SellerProfileManager.getAllProductFields().get(pageNumber - 1) + ":");
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
                            productFieldsValue.set(pageNumber - 1, input);
                            pageNumber += 1;
                        }
                    }
                    else if (pageNumber == SellerProfileManager.getAllProductFields().size() + 1) {
                        System.out.println("are this fields true?(Write (Next) if they are true)");
                        HashMap<String, String> newProductFields = new HashMap<>();
                        for (int i = 0; i < productFieldsValue.size(); i++) {
                            newProductFields.put(SellerProfileManager.getAllProductFields().get(i), productFieldsValue.get(i));
                        }
                        System.out.println(newProductFields);
                        String input = scanner.nextLine();
                        if (input.equals("Back")) {
                            pageNumber -= 1;
                        } else if(input.equals("Next")) {
                            if (SellerProfileManager.areNewProductFieldsValueValid(newProductFields)) {
                                sellerProfileManager.addProduct(newProductFields);
                                System.out.println("Your Request Sent to Admin please w8 for answer");
                                parentMenu.execute();
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

    public Menu getRemoveProductMenu() {
        return new Menu("Remove Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter (productId) to remove a product, (Back) to return or (Logout) to leave your account:");
            }

            @Override
            public void execute() {
                show();
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Back")) {
                    this.parentMenu.execute();
                } else if (input.equals("Logout")) {
                    loginAndRegisterManager.logoutUser();
                } else {
                    if (SellerProfileManager.isProductIdFormatValid(input)) {
                        sellerProfileManager.removeProduct(input);
                        System.out.println("Your product removed successfully");
                        parentMenu.execute();
                    } else {
                        System.out.println("ProductID is Invalid");
                        this.execute();
                    }
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
                System.out.println("1. Logout");
                System.out.println("2. Back");
            }
        };
    }

    public Menu getViewBalanceMenu() {
        return new Menu("View Balance Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("your balance is:" + seller.getBalance());
                System.out.println("1. Logout");
                System.out.println("2. Back");
            }

        };
    }

}
