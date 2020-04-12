package View;

import java.util.ArrayList;

public class SortingMenu extends Menu{
    public SortingMenu(Menu parentMenu) {
        super("Sorting Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getShowAvailableSortsMenu());
        subMenus.add(new SetFilteringTypeMenu(this));
        subMenus.add(getCurrentSortMenu());
        subMenus.add(getDisableSortMenu());
        this.setSubmenus(subMenus);
    }

    private Menu getShowAvailableSortsMenu(){
        return new Menu("Show Available Sorts", this) {
            @Override
            public void execute() {
                submenus.get(1).show();
                parentMenu.execute();
            }
        };
    }

    private Menu getCurrentSortMenu (){
        return new Menu("Current Sort", this) {
            @Override
            public void execute() {
                System.out.println(productsManager.getCurrentSort());
                System.out.println();
                parentMenu.execute();
            }
        };
    }

    private Menu getDisableSortMenu(){
        return new Menu("Disable Current Sort", this) {
            @Override
            public void execute() {
                productsManager.useSortByVisit();
                parentMenu.execute();
            }
        };
    }
}
