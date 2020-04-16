package View.CustomerProfileMenus;

import View.Menu;

import java.util.ArrayList;

public class DiscountCodeMenu extends Menu {
    private String discountCode;
    public DiscountCodeMenu(Menu parentMenu) {
        super("DiscountCode Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(new PaymentMenu(this));
        setSubMenus(subMenus);
    }



    @Override
    public void execute() {
        //todo check is code available;
        //todo show is it available or not;
        //todo do code;
        super.execute();
    }


    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
