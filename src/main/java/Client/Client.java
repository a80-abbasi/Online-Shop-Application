package Client;

import Controller.AdminProfileManager;
import Controller.ProductsManager;
import Model.Account.Supporter;
import Server.Server;
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
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
            if (Connection.getLoggedInAccount() != null) {
                Connection.sendToServer("logout");
            }
            stage.close();
        });
        stage.show();
    }

    static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    static Runnable connectSupporetrs = new Runnable() {
        @Override
        public void run() {
            ArrayList<Supporter> removeFromQueue = new ArrayList<>();
            for (Supporter supporter:  Server.getCustomersInQueue().keySet()) {
                if (Connection.getLoggedInAccount().getUsername().equals(supporter.getUsername())) {
                    try {
                        ChatClient.main(Integer.parseInt(Server.getCustomersInQueue().get(supporter)));
                        removeFromQueue.add(supporter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            for (Supporter supporter : removeFromQueue) {
                Server.getCustomersInQueue().remove(supporter);
            }
        }
    };



    public static Object setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = loadFXML(fxml);
        scene.setRoot(fxmlLoader.load());
        return fxmlLoader.getController();
    }

    private static FXMLLoader loadFXML(String fxml){
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    public static void main(String[] args) {
        executor.scheduleAtFixedRate(connectSupporetrs, 5, 1, TimeUnit.SECONDS);
        ProductPageController.productsManager = new ProductsManager();
        ProductsController.productsManager = new ProductsManager();
        launch();
    }
}
