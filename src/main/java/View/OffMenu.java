package View;

import java.util.ArrayList;

public class OffMenu extends Menu{

    public OffMenu(Menu parentMenu) {
        super("Off Menu", parentMenu);
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getShowProductMenu());

        this.setSubmenus(submenus);
    }

    @Override
    public void show() {
        super.show();
    }

    private Menu getShowProductMenu() {
        return new Menu("Show Product", this) {
            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }

}
