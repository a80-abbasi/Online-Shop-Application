package graphics;

import Model.Account.Off;
import Model.Account.Seller;
import Model.Product.Comment;
import Model.Product.Product;
import Model.Product.ProductStatus;
import Model.Product.Score;
import View.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("productsMenu").load());
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

        /*Product product = new Product("1", ProductStatus.CONFIRMED, "name", "Samsung", 1000, new Seller("a", "a", "a", "a", "A", "A", "a0", 10), 2, null, null);
        product.setImage(new Image("file:src\\main\\resources\\Images\\phone.jpg"));
        product.getAllScores().add(new Score(null, product, 4));*/
        /*for (int i = 0; i < 16; i++) {
            addProduct("product" + i);
        }*/
        launch();
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
        Comment comment = new Comment(seller, product, "kheili khoobeee", "mobile");
        comment.setBought(true);
        Comment comment1 = new Comment(seller, product, "Aree", "mobile");
        comment1.setBought(true);
        Comment comment2 = new Comment(seller, product, "kheili khoobeee kheili", "mobile");
        Comment comment3 = new Comment(seller, product, "kheili khoobeee kheili", "mobile");
        Comment comment4 = new Comment(seller, product, "kheili khoobeee\nkheili", "mobile");
        product.setExplanations("Salam hatman bekharid:)))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))");
        product.setSpecialFeatures(new HashMap<>());
        product.getSpecialFeatures().put("Ram", 6);
    }

}