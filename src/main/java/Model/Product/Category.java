package Model.Product;

import java.util.ArrayList;

public class Category {
    private static ArrayList<Category> allCategories = new ArrayList<>();
    private String name;
    private ArrayList<Category> subCategories;
    private ArrayList<Product> products;
    //vizhegi makhsus??!

    public Category(String name) {
        this.name = name;
        subCategories = new ArrayList<>();
        products = new ArrayList<>();
        allCategories.add(this);
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
