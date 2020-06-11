package Controller;

import Model.Account.Account;
import Model.Account.Customer;
import Model.Account.Discount;
import Model.Product.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductsManager {
    private ArrayList<Product> allProducts = Product.getAllProducts();

    private Comparator<Product> currentSortMode = new ProductComparatorForVisitNumber();
    private ArrayList<FilteringType> currentFilters = new ArrayList<>();
    private SortType currentSort = SortType.SORT_BY_VISIT;
    private String nameFilter;
    private Category categoryFilter;
    private boolean existenceFilter;
    private int maximumPriceFilter;
    private int minimumPriceFilter;

    public void showAllCategories () {
        Category.showAllCategories();
    }

    public Category getCategoryFilter(){
        return categoryFilter;
    }

    public void useSortBySpecialFeature(String feature) {
        currentSort = SortType.SORT_BY_SPECIAL_FEATURE;
        currentSortMode = new ProductComparatorForSpecialFeature(feature);
    }

    public HashMap<String, Integer> getSpecialFeatures(Product product){
        return product.getSpecialFeatures();
    }

    public ArrayList<Product> showProducts () {
        ArrayList<Product> sortedFilteredProducts = new ArrayList<>();
        ArrayList<Product> products;
        if (categoryFilter != null){
            products = categoryFilter.getProducts();
        }
        else {
            products = allProducts;
        }
        for (Product product : products) {
            if (nameFilter != null) {
                if (!getMatcher(product.getProductName(), "(?i)" + nameFilter).find()){
                    continue;
                }
            }
            if (existenceFilter){
                if (product.getExistingNumber() == 0){
                    continue;
                }
            }
            if (maximumPriceFilter > 0){
                if (product.getPriceWithOff() > maximumPriceFilter){
                    continue;
                }
            }
            if (minimumPriceFilter > 0){
                if (product.getPriceWithOff() < minimumPriceFilter){
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
        if (!currentFilters.contains(FilteringType.CATEGORY_FILTER)){
            currentFilters.add(FilteringType.CATEGORY_FILTER);
        }
    }

    public void addMaximumPriceFilter(int maxPrice){
        maximumPriceFilter = maxPrice;
        if (!currentFilters.contains(FilteringType.MAXIMUM_PRICE_FILTER)) {
            currentFilters.add(FilteringType.MAXIMUM_PRICE_FILTER);
        }
    }

    public void addMinimumPriceFilter(int minPrice){
        minimumPriceFilter = minPrice;
        if (!currentFilters.contains(FilteringType.MINIMUM_PRICE_FILTER)) {
            currentFilters.add(FilteringType.MINIMUM_PRICE_FILTER);
        }
    }

    public void disableMaximumPriceFilter(){
        currentFilters.remove(FilteringType.MAXIMUM_PRICE_FILTER);
        maximumPriceFilter = 0;
    }

    public void disableMinimumPriceFilter(){
        currentFilters.remove(FilteringType.MINIMUM_PRICE_FILTER);
        minimumPriceFilter = 0;
    }

    public void disableCategoryFilter(){
        categoryFilter = null;
        currentFilters.remove(FilteringType.CATEGORY_FILTER);
    }

    public void addNameFiltering(String name){
        nameFilter = name;
        if (!currentFilters.contains(FilteringType.NAME_FILTER)){
            currentFilters.add(FilteringType.NAME_FILTER);
        }
    }

    public void disableNameFiltering(){
        nameFilter = null;
        currentFilters.remove(FilteringType.NAME_FILTER);
    }

    public void addExistenceFilter(boolean existenceFilter){
        this.existenceFilter = existenceFilter;
        if (!currentFilters.contains(FilteringType.EXISTENCE_FILTER) && existenceFilter){
            currentFilters.add(FilteringType.EXISTENCE_FILTER);
        }
        if (!existenceFilter){
            currentFilters.remove(FilteringType.EXISTENCE_FILTER);
        }
    }

    public ArrayList<FilteringType> getCurrentFilters(){
        return currentFilters;
    }

    public void useSortByTime(){
        currentSort = SortType.SORT_BY_TIME;
        currentSortMode = new ProductComparatorForTime();
    }

    public void useSortByScore(){
        currentSort = SortType.SORT_BY_SCORE;
        currentSortMode = new ProductComparatorForScore();
    }

    public void useSortByVisit(){
        currentSort = SortType.SORT_BY_VISIT;
        currentSortMode = new ProductComparatorForVisitNumber();
    }

    public void useSortByHighestPrice(){
        currentSort = SortType.SORT_BY_HIGHEST_PRICE;
        currentSortMode = Comparator.comparing(Product::getPrice).reversed();
    }

    public void useSortByLowestPrice(){
        currentSort = SortType.SORT_BY_LOWEST_PRICE;
        currentSortMode = Comparator.comparing(Product::getPrice);
    }

    public String getCurrentSort(){
        return currentSort.getSortType();
    }

    public Product getProductByID(String ID){
        return Product.getProductByID(ID);
    }

    public ArrayList<Comment> getProductComments(Product product){
        return product.getProductComments();
    }

    private static Matcher getMatcher(String input, String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public void addProductToCart(Product product, int number){
        Account account = Account.getLoggedInAccount();
        if (account instanceof Customer) {
            Customer customer = (Customer) account;
            customer.addToCart(product, number);
        }
        else {
            throw new IllegalArgumentException("You are not logged-in as a customer");
        }
    }

    public boolean hasProductInCart(Product product){
        return ((Customer) Account.getLoggedInAccount()).getCart().containsKey(product);
    }

    public static double getTotalPrice(){
        Customer customer = (Customer) Account.getLoggedInAccount();
        return customer.getTotalPrice();
    }

    public static boolean isValidIDForProductID(String ID) {
        for (Product product : Product.getAllProducts()) {
            if (product.getProductId().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public HashMap<Product, Integer> getCart(){
        return ((Customer) Account.getLoggedInAccount()).getCart();
    }

    public static double costWithOffAndDigest (String discountCode) {
        getTotalPrice();
        //todo
        return 0;
    }

}
