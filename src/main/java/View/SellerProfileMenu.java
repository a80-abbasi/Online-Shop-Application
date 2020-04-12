package View;

import Model.Account.Seller;

public class SellerProfileMenu extends Menu{
    private Seller seller;

    public SellerProfileMenu(Seller seller) {
        super("Seller Profile Menu", null);
        this.seller = seller;
    }
}
