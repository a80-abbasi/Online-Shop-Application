package View;

import Model.Account.Off;

import java.util.ArrayList;

public class OffMenu extends Menu{

    public OffMenu(Menu parentMenu) {
        super("Off Menu", parentMenu);
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getShowProductMenu());
        this.setSubMenus(submenus);
    }

    @Override
    public void show() {
        ArrayList<String> allOffs = Off.getAllOffsStatus();
        for (String off : allOffs) {
            System.out.println(off);
        }
        super.show();
    }

    private Menu getShowProductMenu() {
        return new Menu("Show Product", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter productId to show or (back) to return:");
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
                    //todo: show Product Menu
                }
            }
        };
    }

}
