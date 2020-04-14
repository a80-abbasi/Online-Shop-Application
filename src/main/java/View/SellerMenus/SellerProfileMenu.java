package View.SellerMenus;

import Model.Account.Seller;
import View.Menu;

public class SellerProfileMenu extends Menu {
    private Seller seller;

    public SellerProfileMenu(Seller seller) {
        super("Seller Profile Menu", null);
        this.seller = seller;
    }
}
