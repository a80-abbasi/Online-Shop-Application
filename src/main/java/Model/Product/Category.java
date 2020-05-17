package Model.Product;

import java.util.ArrayList;

public class Category {
    private static ArrayList<Category> allCategories = new ArrayList<>();
    private String name;
    private ArrayList<Category> subCategories;
    private ArrayList<Product> products;
    private ArrayList<String> specialFeatures;

    public Category(String name) {
        this.name = name;
        subCategories = new ArrayList<>();
        products = new ArrayList<>();
        specialFeatures = new ArrayList<>();
        allCategories.add(this);
    }

    public Category() {
        this("");
    }

    public void addAFeature(String feature){
        specialFeatures.add(feature);
        for (Product product : products) {
            product.getSpecialFeatures().put(feature, 0);
        }
    }

    public ArrayList<String> getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(ArrayList<String> specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public void addProductToCategory(Product product){
        for (String feature : specialFeatures) {
            product.getSpecialFeatures().put(feature, 0);
        }
        products.add(product);
    }

    public static void setAllCategories(ArrayList<Category> allCategories) {
        Category.allCategories = allCategories;
    }

    public static ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public static void showAllCategories(){
        for (Category category : allCategories) {
            System.out.println(category);
        }
    }

    public static Category getCategoryByName(String name){
        for (Category category : allCategories) {
            if (category.name.equals(name)){
                return category;
            }
        }
        return null;
    }

    public static void removeCategory(Category category) {
        allCategories.remove(category);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Category: ").append(name);
        for (Category subCategory : subCategories) {
            stringBuilder.append("\n\t").append(subCategory.getName());
        }
        return stringBuilder.toString();
    }
}
