package View;

import java.util.ArrayList;

public class FilteringMenu extends Menu {
    public FilteringMenu(Menu parentMenu) {
        super("Filtering Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getShowAvailableFiltersMenu());
        subMenus.add(new SetFilteringTypeMenu(this));
        subMenus.add(getCurrentFiltersMenu());
        subMenus.add(new DisablingFiltersMenu(this));
        this.setSubmenus(subMenus);
    }

    private Menu getShowAvailableFiltersMenu(){
        return new Menu("Show Available Filters", this) {
            @Override
            public void execute() {
                submenus.get(1).show();
                parentMenu.execute();
            }
        };
    }

    private Menu getCurrentFiltersMenu(){
        return new Menu("CurrentFilters", this) {
            @Override
            public void execute() {
                productsManager.printCurrentFilters();
                parentMenu.execute();
            }
        };
    }
}
