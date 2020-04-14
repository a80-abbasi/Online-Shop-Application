package View;

import Controller.EditManager;
import Model.Account.Account;

import java.util.ArrayList;

public class EditMenu extends Menu {
    Account account;
    public EditMenu(Account account, Menu parentMenu) {
        super("Edit Personal Info", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getEditMenu());
        this.account = account;
        this.setSubmenus(subMenus);

    }
    private Menu getEditMenu() {
        return new Menu("View Categories", this) {
            @Override
            public void execute() {
                productsManager.showAllCategories();
                parentMenu.execute();
            }
        };
    }

    @Override
    public void execute() {
        show();
        try {
            Menu nextMenu;
            int chosenMenu = Integer.parseInt(scanner.nextLine());
            if (chosenMenu == submenus.size() + 1) {
                nextMenu = this.parentMenu;
            } else {
                if (chosenMenu == submenus.size()) {
                    System.out.println("Enter Field You Want To Edit : ");
                    String fieldToEdit = scanner.nextLine().trim();
                    if (!fieldToEdit.equals("username")) {
                        if (account.hasField(fieldToEdit)) {
                            System.out.println("Enter New " + fieldToEdit + " : ");
                            String newThing = scanner.nextLine();
                            if (EditManager.canEditWithNewString(fieldToEdit, newThing)) {
                                account.setFieldValue(fieldToEdit, newThing);
                            }
                        } else {
                            System.err.println("you cant use this " + fieldToEdit);
                        }

                    } else {
                        System.err.println("you cant change your username");
                        execute();
                    }
                }
                nextMenu = submenus.get(chosenMenu - 1);
            }
            nextMenu.execute();
        } catch (Exception e) {
            System.err.println("Wrong Input\n");
            execute();
        }
    }
}
