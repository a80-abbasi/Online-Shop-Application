package View;

import java.util.ArrayList;

public class DisablingFiltersMenu extends Menu
{
    public DisablingFiltersMenu(Menu parentMenu) {
        super("Disabling Filters", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getDisableCategoryFilterMenu());
        subMenus.add(getDisableNameFilteringMenu());
        subMenus.add(getDisableExistenceFilteringMenu());
        this.setSubmenus(subMenus);
    }

    private Menu getDisableCategoryFilterMenu(){
        return new Menu("Disable Category Filtering", this) {
            @Override
            public void execute() {
                productsManager.disableCategoryFilter();
                parentMenu.execute();
            }
        };
    }

    private Menu getDisableNameFilteringMenu(){
        return new Menu("Disable Name Filtering", this) {
            @Override
            public void execute() {
                productsManager.addNameFiltering(null);
                parentMenu.execute();
            }
        };
    }

    private Menu getDisableExistenceFilteringMenu(){
        return new Menu("Disable Existence Filtering", this) {
            @Override
            public void execute() {
                productsManager.addExistenceFilter(false);
                parentMenu.execute();
            }
        };
    }
}
