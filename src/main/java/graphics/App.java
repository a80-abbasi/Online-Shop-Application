package graphics;

import Model.Account.Off;
import Model.Account.Seller;
import Model.Product.Product;
import Model.Product.ProductStatus;
import Model.Product.Score;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("productsMenu"));
        stage.setScene(scene);
        /*stage.setFullScreen(true);*/
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        Product product = new Product("1", ProductStatus.CONFIRMED, "name", "Samsung", 1000, null, 2, null, null);
        product.setImage(new Image("file:src\\main\\resources\\Images\\phone.jpg"));
        product.getAllScores().add(new Score(null, product, 4));

        for (int i = 0; i < 16; i++) {
            addProduct("product" + i);
        }
        launch();
    }

    private static void addProduct(String name) {
        Seller seller = new Seller();
        Product product = new Product("1", ProductStatus.CONFIRMED, name, "Samsung", 1000, seller, 0, null, null);
        product.setImage(new Image("file:src\\main\\resources\\Images\\phone.jpg"));
        product.getAllScores().add(new Score(null, product, 4));
        product.getAllScores().add(new Score(null, product, 3));
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        Off off = new Off("1", new Date(), new Date(new Date().getTime() + 1000000000), 20, products);
        product.setOff(off);
    }

}