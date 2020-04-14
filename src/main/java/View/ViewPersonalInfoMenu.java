package View;

import Model.Account.Account;
import Model.Account.Customer;
import Model.Account.Field;

import java.util.ArrayList;

public class ViewPersonalInfoMenu extends Menu {
    Account account;
    String accountType;
    public ViewPersonalInfoMenu(Account account, String accountType ,Menu parentMenu) {
        super("View Personal Info Menu", parentMenu);
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(new EditMenu(account, parentMenu));
        this.account = account;
        this.accountType = accountType;
        this.submenus = submenus;
    }

    @Override
    public void execute() {
        for (Field personalField : account.personalFields) {
            System.out.println(personalField.getName() + " : " + personalField.getValue());
        }
        if (accountType.equals("Customer")) {
            System.out.println();
        } else if (accountType.equals("Seller")) {
            System.out.println();
        } else if (accountType.equals("Admin")) {
            System.out.println();
        }
        //todo: print info about ...
        super.execute();
    }
}
