package View.ProductsMenus;

import Controller.FilteringType;
import View.Menu;

import java.util.ArrayList;

public class DisablingFiltersMenu extends Menu
{
    public DisablingFiltersMenu(Menu parentMenu) {
        super("Disabling Filters", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getDisableCategoryFilterMenu());
        subMenus.add(getDisableNameFilteringMenu());
        subMenus.add(getDisableExistenceFilteringMenu());
        this.setSubMenus(subMenus);
    }

    private Menu getDisableCategoryFilterMenu(){
        return new Menu("Disable" + FilteringType.CATEGORY_FILTER.getFilterType(), this) {
            @Override
            public void execute() {
                productsManager.disableCategoryFilter();
                parentMenu.execute();
            }
        };
    }

    private Menu getDisableNameFilteringMenu(){
        return new Menu("Disable" + FilteringType.NAME_FILTER.getFilterType(), this) {
            @Override
            public void execute() {
                productsManager.disableNameFiltering();
                parentMenu.execute();
            }
        };
    }

    private Menu getDisableExistenceFilteringMenu(){
        return new Menu("Disable" + FilteringType.EXISTENCE_FILTER.getFilterType(), this) {
            @Override
            public void execute() {
                productsManager.addExistenceFilter(false);
                parentMenu.execute();
            }
        };
    }
}
