package View.SellerProfileMenus;

import View.Menu;

import java.util.ArrayList;

public class ViewOffsMenu extends Menu {
    public ViewOffsMenu(Menu parentMenu) {
        super("View Offs Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getViewMenu());
        subMenus.add(getEditMenu());
        subMenus.add(getAddOffMenu());
        this.setSubmenus(subMenus);
    }
    public Menu getViewMenu() {
        return new Menu("View Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    public Menu getEditMenu() {
        return new Menu("Edit Menu", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }
    public Menu getAddOffMenu() {
        return new Menu("Add Off Information", this) {
            @Override
            public void execute() {
                parentMenu.execute();
            }
        };
    }

}
