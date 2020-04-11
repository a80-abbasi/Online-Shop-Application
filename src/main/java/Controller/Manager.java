package Controller;

import Model.Product.Category;
import Model.Product.Product;
import Model.Product.ProductComparatorForVisitNumber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
    private ArrayList<Product> allProducts = Product.getAllProducts();

    private Comparator<Product> currentSortMode = new ProductComparatorForVisitNumber();
    private String nameFilter;
    private Category categoryFilter;
    private boolean existingFilter;

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
            if (existingFilter){
                if (!product.getExistenceStatus()){
                    continue;
                }
            }
            sortedFilteredProducts.add(product);
        }
        sortedFilteredProducts.sort(currentSortMode);
        return sortedFilteredProducts;
    }

    public static Matcher getMatcher(String input, String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
