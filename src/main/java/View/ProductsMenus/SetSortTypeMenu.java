package View.ProductsMenus;

import Controller.SortType;
import View.Menu;

import java.util.ArrayList;

public class SetSortTypeMenu extends Menu {
    public SetSortTypeMenu(Menu parentMenu) {
        super("Set Sort Type Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getSortByTimeMenu());
        subMenus.add(getSortByScore());
        subMenus.add(getSortByVisitNumber());
        this.setSubmenus(subMenus);
    }

    private Menu getSortByTimeMenu(){
        return new Menu(SortType.SORT_BY_TIME.getSortType(), this) {
            @Override
            public void execute() {
                productsManager.useSortByTime();
                parentMenu.execute();
            }
        };
    }

    private Menu getSortByScore(){
        return new Menu(SortType.SORT_BY_SCORE.getSortType(), this) {
            @Override
            public void execute() {
                productsManager.useSortByScore();
                parentMenu.execute();
            }
        };
    }

    private Menu getSortByVisitNumber(){
        return new Menu(SortType.SORT_BY_VISIT.getSortType(), this) {
            @Override
            public void execute() {
                productsManager.useSortByVisit();
                parentMenu.execute();
            }
        };
    }
}
