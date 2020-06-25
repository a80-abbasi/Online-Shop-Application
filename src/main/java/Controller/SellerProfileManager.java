package Controller;

import Model.Account.*;
import Model.Request.*;
import Model.Product.Category;
import Model.Product.Product;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;

public class SellerProfileManager extends ProfileManager {
    private Seller seller;

    public SellerProfileManager(Seller seller) {
        super(seller);
        this.seller = seller;
    }

    public void editCompanyName(String companyName) {
        this.seller.setNameOfCompany(companyName);
    }

    //todo: does company have other information?
    public HashMap<String, String> getCompanyInformation() {
        HashMap <String, String> companyInformation = new HashMap<String, String>();
        companyInformation.put("Name", seller.getNameOfCompany());
        return companyInformation;
    }

    public ArrayList<String> getSalesHistory() {
        ArrayList<String> salesHistory = new ArrayList<>();
        for (SellLog sellLog : seller.getSellLogs()) {
            salesHistory.add(sellLog.toString());
        }
        return salesHistory;
    }

    public ArrayList<String> getSellerProducts() {
        ArrayList<Product> sellerProducts = seller.getProducts();
        ArrayList<String> sellerProductsIds = new ArrayList<>();
        for (Product product : sellerProducts) {
            sellerProductsIds.add(product.getProductId());
        }
        return sellerProductsIds;
    }

    public Product getProductByID(String id) {
        return Product.getProductByID(id);
    }

    public ArrayList<String> getAllCategories() {
        ArrayList<Category> allCategories = Category.getAllCategories();
        ArrayList<String> allCategoriesNames = new ArrayList<>();
        for (Category category : allCategories) {
            allCategoriesNames.add(category.toString());
        }
        return allCategoriesNames;
    }

    public HashMap<String, String> getProductBuyers(String productId) {
        HashMap<String, String> allBuyersUsernameAndPhoneNumber = new HashMap<>();
        for (Customer customer : Product.getProductByID(productId).getProductBuyers()) {
            allBuyersUsernameAndPhoneNumber.put(customer.getUsername(),customer.getPhoneNumber());
        }
        return allBuyersUsernameAndPhoneNumber;
    }

    public static ArrayList<String> getAllProductFields() {
        return Product.getProductFields();
    }

    public static boolean isProductIdFormatValid(String productId) {
        if (Product.getProductByID(productId) == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public AddProductRequest addProductRequest() {
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setProductSeller(this.seller);
        return addProductRequest;
    }

    public EditProductRequest makeNewEditProductRequest(String productId) throws NullPointerException, IllegalArgumentException{
        Product product = Product.getProductByID(productId);
        if (product == null){
            throw new NullPointerException();
        }
        else if (seller.getProducts().contains(product)) {
            EditProductRequest editProductRequest = new EditProductRequest(product);
            editProductRequest.setProductSeller(this.seller);
            return editProductRequest;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void setProductId(EditAddProductRequest editAddProductRequest, String productId) throws IllegalArgumentException {
        if (Product.getProductByID(productId) != null) {
            throw new IllegalArgumentException();
        }
        editAddProductRequest.setProductId(productId);
    }

    public void setProductName(EditAddProductRequest editAddProductRequest, String productName) {
        editAddProductRequest.setProductName(productName);
    }

    public void setProductCompanyName(EditAddProductRequest editAddProductRequest, String companyName) {
        editAddProductRequest.setCompanyName(companyName);
    }

    public void setProductPrice(EditAddProductRequest editAddProductRequest, String producePrice) throws InputMismatchException {
        editAddProductRequest.setPrice(Double.parseDouble(producePrice));
    }

    public void setExistingNumberOfProduct(EditAddProductRequest editAddProductRequest, String existingNumber) throws InputMismatchException {
        editAddProductRequest.setExistingNumber(Integer.parseInt(existingNumber));
    }

    public void setProductCategory(EditAddProductRequest editAddProductRequest, String categoryName) throws NullPointerException{
        Category category = Category.getCategoryByName(categoryName);
        if (category == null) {
            throw new NullPointerException();
        }
        editAddProductRequest.setProductCategory(category);
        HashMap<String, Integer> productSpecialFeatures = new HashMap<>();
        for (String specialFeature : category.getSpecialFeatures()) {
            productSpecialFeatures.put(specialFeature, 0);
        }
        editAddProductRequest.setProductSpecialFeatures(productSpecialFeatures);
    }

    public Category getProductCategoryInRequest(EditAddProductRequest editAddProductRequest) {
        return editAddProductRequest.getProductCategory();
    }

    public ArrayList<String> getProductCategorySpecialFeatures(EditAddProductRequest editAddProductRequest) {
        return editAddProductRequest.getProductCategory().getSpecialFeatures();
    }

    public void setValueForProductSpecialFeature(EditAddProductRequest editAddProductRequest, int keyIndex, int value) {
        Category category = editAddProductRequest.getProductCategory();
        editAddProductRequest.setProductSpecialFeatureValue(category.getSpecialFeatures().get(keyIndex), value);
    }

    public void removeProduct(String productId) {
        Product product = Product.getProductByID(productId);
        new RemoveProductRequest(product);
    }

    public TableView getSellerProductsTable(TableView sellerProductsTable) {
        TableColumn<String, Product> column = new TableColumn<>("Product ID");
        column.setCellValueFactory(new PropertyValueFactory<>("productId"));

        TableColumn<String, Product> column1 = new TableColumn<>("Product Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("productName"));

        sellerProductsTable.getColumns().addAll(column, column1);
        for (Product product : this.seller.getProducts()) {
            sellerProductsTable.getItems().add(product);
        }
        sellerProductsTable.setPlaceholder(new Label("No Data To Display"));
        return sellerProductsTable;
    }

    public void makeNewAddOffRequest(String offID, Date offStartTime, Date offEndTime, String offAmount, ArrayList<String> offProductIDs) throws Exception {
        try {
            if (checkOffAmountValidity(offAmount) && checkOffIDValidity(offID)) {
                new AddOffRequest(offID, offStartTime, offEndTime, Integer.parseInt(offAmount), offProductIDs);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void makeNewEditOffRequest(String offID, Date offStartTime, Date offEndTime, String offAmount, ArrayList<String> offProductIDs) throws Exception {
        try {
            if (checkOffAmountValidity(offAmount) && checkOffIDValidity(offID)) {
                new EditOffRequest(offID, offStartTime, offEndTime, Integer.parseInt(offAmount), offProductIDs);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean checkOffIDValidity(String offID) throws Exception{
        if (Off.getOffById(offID) != null) {
            throw new Exception("There is another off with this ID");
        }
        else {
            return true;
        }
    }

    private boolean checkOffAmountValidity(String offAmount) throws Exception {
        try {
            Integer.parseInt(offAmount);
            return true;
        } catch (Exception e) {
            throw new Exception("Invalid Off Amount");
        }
    }

    public AddOffRequest addOffRequest () {
        return new AddOffRequest();
    }

    public EditOffRequest makeNewEditOffRequest(String offID) throws NullPointerException, IllegalArgumentException {
        Off off = Off.getOffById(offID);
        if (off == null) {
            throw new NullPointerException();
        }
        else if (this.seller.getOffs().contains(off)) {
            return new EditOffRequest();
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void setOffId(EditAddOffRequest editAddOffRequest, String offId) throws IllegalArgumentException{
        if (Off.getOffById(offId) == null) {
            editAddOffRequest.setOffID(offId);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void setOffStartTime(EditAddOffRequest editAddOffRequest, Date startTime) {
        editAddOffRequest.setStartTime(startTime);
    }

    public void setOffEndTime(EditAddOffRequest editAddOffRequest, Date endTime) {
        editAddOffRequest.setEndTime(endTime);
    }

    public void setOffAmount(EditAddOffRequest editAddOffRequest, String offAmount) throws InputMismatchException{
        if (offAmount.matches("\\d+")) {
            editAddOffRequest.setOffAmount(Integer.parseInt(offAmount));
        }
        else {
            throw new InputMismatchException();
        }
    }

    public void setOffProduct(EditAddOffRequest editAddOffRequest, String productID) throws NullPointerException, IllegalArgumentException {
        Product product = Product.getProductByID(productID);
        if (product == null) {
            throw new NullPointerException();
        }
        else if (!(seller.getProducts().contains(product))) {
            throw new IllegalArgumentException();
        }
        else {
            editAddOffRequest.addOffProduct(product);
        }
    }

    public void removeProductInOffRequest(EditOffRequest editOffRequest, String productID) throws NullPointerException, IllegalArgumentException {
        Product product = Product.getProductByID(productID);
        if (product == null) {
            throw new NullPointerException();
        }
        else if (editOffRequest.getOffProductIDs().contains(product)) {
            editOffRequest.removeProduct(product);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isInputInOffFields(String input) {
        for (int i = 0; i < Off.getOffFields().size(); i++) {
            if (Off.getOffFields().get(i).equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInputInProductFields(String input) {
        for (int i = 0; i < Product.getProductFields().size(); i++) {
            if (Product.getProductFields().get(i).equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidInputForOffID (String ID) {
        for (String offID : Off.getAllOffIds()) {
            if (offID.equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public HashMap<String, String> getOffsAmountAndID() {
        HashMap<String, String> offsAmountAndID = new HashMap<>();
        for (int i = 0; i < seller.getOffs().size(); i ++) {
            offsAmountAndID.put(String.valueOf(seller.getOffs().get(i).getOffAmount()), seller.getOffs().get(i).getOffID());
        }
        return offsAmountAndID;
    }

    public HashMap<String, String> getSellerProductsNameAndID() {
        HashMap<String, String> SellerProductsNameAndID = new HashMap<>();
        for (int i = 0; i < seller.getProducts().size(); i ++) {
            SellerProductsNameAndID.put(String.valueOf(seller.getProducts().get(i).getProductName()), seller.getProducts().get(i).getProductId());
        }
        return SellerProductsNameAndID;
    }

    public static Off getOffByID(String offID) {
        return Off.getOffById(offID);
    }

    public int viewBalance(Account account) {
        return 0;
    }

    public String getCompanyName() {
        return this.seller.getNameOfCompany();
    }

    public TableView getSellerOffs(TableView offsTable) {
        TableColumn<String, Off> column = new TableColumn<>("Off ID");
        column.setCellValueFactory(new PropertyValueFactory<>("offID"));

        offsTable.getColumns().add(column);
        for (Off sellerOff : this.seller.getOffs()) {
            offsTable.getItems().add(sellerOff);
        }
        offsTable.setPlaceholder(new Label("No Data To Display"));
        return offsTable;
    }
}
