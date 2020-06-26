package graphics;

import Controller.AdminProfileManager;
import Model.Account.Off;
import Model.Account.Seller;
import Model.Product.*;
import View.Main;
import graphics.LoginAndRegister.CreateAdminAccount;
import graphics.products.ProductPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        if (AdminProfileManager.isThereAdmin()) {
            scene = new Scene(loadFXML("MainMenu").load());
        }
        else {
            AlertBox.showMessage("Register Admin", "You must first register admin");
            scene = new Scene(loadFXML("CreateAdminAccount").load());
            CreateAdminAccount.setParentMenu("MainMenu");
        }
        stage.setScene(scene);
        /*stage.setFullScreen(true);*/
        stage.show();
        stage.setOnCloseRequest(e -> {
            Main.serializeXML();
            System.exit(0);
        });
    }

    public static Object setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = loadFXML(fxml);
        scene.setRoot(fxmlLoader.load());
        return fxmlLoader.getController();
    }

    private static FXMLLoader loadFXML(String fxml){
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    public static void main(String[] args) {
        Main.deserializeXML();

        /*new Customer("ab", "ab", "ab", "ab", "ab@ab.ab", "9089090", 100);
        new Customer("ac", "ac", "ac", "ac", "ac@ac.ac", "9089090", 100);*/
        /*Product product = Product.getAllProducts().get(0);
        Comment comment1 = new Comment(Customer.getAllCustomers().get(0), product.getProductId(), "hatman bekharid <3", "hi");
        Comment comment2 = new Comment(Customer.getAllCustomers().get(0), product.getProductId(), "love this porduct", "phone");*/

        launch();
    }

    public static void setBackButton(ImageView back, String parentMenuAddress){
        ProductPageController.shadowOnMouseHover(back);
        back.setOnMouseClicked(e -> {
            try {
                App.setRoot(parentMenuAddress);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private static void addProduct(String name) {
        Seller seller = new Seller();
        seller.setName("Ali");
        Product product = new Product("1", ProductStatus.CONFIRMED, name, "Samsung", 1000, seller, 0, null, null);
        product.setImageAddress(new Image("file:src\\main\\resources\\Images\\phone.jpg"));
        product.getAllScores().add(new Score(null, product, 4));
        product.getAllScores().add(new Score(null, product, 3));
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        Off off = new Off("1", new Date(), new Date(new Date().getTime() + 1000000000), 20, products);
        product.setOff(off);
        Comment comment = new Comment(seller, product.getProductId(), "kheili khoobeee", "mobile");
        comment.setBought(true);
        Comment comment1 = new Comment(seller, product.getProductId(), "Aree", "mobile");
        comment1.setBought(true);
        Comment comment2 = new Comment(seller, product.getProductId(), "kheili khoobeee kheili", "mobile");
        Comment comment3 = new Comment(seller, product.getProductId(), "kheili khoobeee kheili", "mobile");
        Comment comment4 = new Comment(seller, product.getProductId(), "kheili khoobeee\nkheili", "mobile");
        product.setExplanations("Salam hatman bekharid:)))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))");
        product.setSpecialFeatures(new HashMap<>());
        product.getSpecialFeatures().put("Ram", 6);
    }

}