package Client;

import Controller.AdminProfileManager;
import Controller.ProductsManager;
import graphics.AlertBox;
import graphics.App;
import graphics.LoginAndRegister.CreateAdminAccount;
import graphics.products.ProductPageController;
import graphics.products.ProductsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {

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
        stage.show();
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
        ProductPageController.productsManager = new ProductsManager();
        ProductsController.productsManager = new ProductsManager();
        launch();
    }
}
