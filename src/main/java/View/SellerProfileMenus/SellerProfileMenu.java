package View.SellerProfileMenus;

import Controller.SellerProfileManager;
import Model.Account.Seller;
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
        subMenus.add(new ManageProductsMenu(parentMenu));
        subMenus.add(getAddProductMenu());
        subMenus.add(getRemoveProductMenu());
        subMenus.add(getShowCategoriesMenu());
        subMenus.add(new ViewOffsMenu(parentMenu));
        subMenus.add(getViewBalanceMenu());
        this.setSubmenus(subMenus);
    }

    public Menu getViewCompanyInformationMenu() {
        return new Menu("View Company Information Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    public Menu getViewSalesHistoryMenu() {
        return new Menu("View Sales History Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    public Menu getAddProductMenu() {
        return new Menu("Add Product Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    public Menu getRemoveProductMenu() {
        return new Menu("Remove Product Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    public Menu getShowCategoriesMenu() {
        return new Menu("getShow Categories Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    public Menu getViewBalanceMenu() {
        return new Menu("View Balance Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
}
