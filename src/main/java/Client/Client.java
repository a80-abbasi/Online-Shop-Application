package Client;

import Controller.AdminProfileManager;
import Controller.ProductsManager;
import View.Main;
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
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        if (AdminProfileManager.isThereAdmin()) {
            scene = new Scene(loadFXML("MainMenu").load());
        }
        else {
            scene = new Scene(loadFXML("CreateAdminAccount").load());
            CreateAdminAccount.setParentMenu("MainMenu");
        }
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> {
            if (Connection.getAccountFromServer() != null) {
                Connection.sendToServer("logout");
            }
            stage.close();
        });
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
