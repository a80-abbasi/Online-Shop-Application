package View.CustomerProfileMenus;

import View.Menu;

public class PaymentMenu extends Menu{
    public PaymentMenu(Menu ParentMenu) {
        super("Payment Menu", ParentMenu);
    }

    public boolean isPaymentDone () {
        return true;
    }

    @Override
    public void execute() {
        super.execute();
    }
}
