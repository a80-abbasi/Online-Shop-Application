package View.ProductsMenus;

import Controller.FilteringType;
import View.Menu;

import java.util.ArrayList;

public class SetFilteringTypeMenu extends Menu {
    public SetFilteringTypeMenu(Menu parentMenu) {
        super("Set Filtering Type Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(getCategoryFilterMenu());
        subMenus.add(getNameFilteringMenu());
        subMenus.add(getExistenceFilteringMenu());
        this.setSubmenus(subMenus);
    }

    private Menu getCategoryFilterMenu(){
        return new Menu(FilteringType.CATEGORY_FILTER.getFilterType(), this) {
            @Override
            public void execute() {
                String categoryName = scanner.nextLine().trim();
                try {
                    productsManager.addCategoryFilter(categoryName);
                }
                catch (IllegalArgumentException e){
                    System.out.println("there is no such a category!");
                }
                finally {
                    parentMenu.execute();
                }
            }
        };
    }

    private Menu getNameFilteringMenu(){
        return new Menu(FilteringType.NAME_FILTER.getFilterType(), this) {
            @Override
            public void execute() {
                productsManager.addNameFiltering(scanner.nextLine().trim());
                parentMenu.execute();
            }
        };
    }

    private Menu getExistenceFilteringMenu(){
        return new Menu(FilteringType.EXISTENCE_FILTER.getFilterType(), this) {
            @Override
            public void execute() {
                System.out.println("1. only existing files");
                System.out.println("2. all of files");
                String command = scanner.nextLine().trim();
                if (command.equals("1") || command.equalsIgnoreCase("only existing files")){
                    productsManager.addExistenceFilter(true);
                }
                else if (command.equalsIgnoreCase("all of files") || command.equals("2")){
                    productsManager.addExistenceFilter(false);
                }
                else {
                    System.out.println("wrong input!");
                }
                parentMenu.execute();
            }
        };
    }
}
