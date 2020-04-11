package Controller;

import Model.Product.Category;
import Model.Product.Product;
import Model.Product.ProductComparatorForVisitNumber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductsManager {
    private ArrayList<Product> allProducts = Product.getAllProducts();

    private Comparator<Product> currentSortMode = new ProductComparatorForVisitNumber();
    private String nameFilter;
    private Category categoryFilter;
    private boolean existenceFilter;

    public void showAllCategories () {
        Category.showAllCategories();
    }

    public ArrayList<Product> showProducts () {
        ArrayList<Product> sortedFilteredProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (nameFilter != null) {
                if (!getMatcher(product.getProductName(), "(?i)" + nameFilter).find()){
                    continue;
                }
            }
            if (categoryFilter != null){
                if (!product.getProductCategory().equals(categoryFilter)){
                    continue;
                }
            }
            if (existenceFilter){
                if (!product.getExistenceStatus()){
                    continue;
                }
            }
            sortedFilteredProducts.add(product);
        }
        sortedFilteredProducts.sort(currentSortMode);
        return sortedFilteredProducts;
    }

    public void addCategoryFilter(String name) throws IllegalArgumentException{
        Category category = Category.getCategoryByName(name);
        if (categoryFilter == null){
            throw new IllegalArgumentException();
        }
        categoryFilter = category;
    }

    public void disableCategoryFilter(){
        categoryFilter = null;
    }

    public void addNameFiltering(String name){
        nameFilter = name;
    }

    public void addExistenceFilter(boolean existenceFilter){
        this.existenceFilter = existenceFilter;
    }

    public void printCurrentFilters(){
        System.out.println("Active Filters");
        if (categoryFilter != null){
            System.out.println("\tCategory Filter");
        }
        if (nameFilter != null){
            System.out.println("\tName Filter");
        }
        if (existenceFilter){
            System.out.println("\tExistence Filter");
        }
        System.out.println();
    }

    private static Matcher getMatcher(String input, String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
