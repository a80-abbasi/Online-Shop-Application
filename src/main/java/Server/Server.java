package Server;

import Model.Product.Category;
import Model.Product.Product;
import View.Main;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application {
    private static final int serverPort = 8080;
    private static ServerSocket serverSocket;
    private static Socket clientSocket;

    @Override
    public void start(Stage stage) {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        Button button = new Button("Shut down server");
        VBox vBox = new VBox(button);
        vBox.setSpacing(500);
        vBox.setPadding(new Insets(100, 100, 100, 100));
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Server");
        stage.show();
        stage.setOnCloseRequest(e -> {
            stage.close();
            Main.serializeXML();
            System.exit(0);
        });
        button.setOnAction(e -> {
            stage.close();
            Main.serializeXML();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        Main.deserializeXML();
        Thread thread = new Thread(Server::run);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        launch();
    }

    private static void run(){
        System.out.println("Server is running...\n");
        try {
            serverSocket = new ServerSocket(serverPort);
            while (true){
                clientSocket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));

                String message = dataInputStream.readUTF();

                if (message.equals("getProducts")){
                    dataOutputStream.writeUTF(new Gson().toJson(Product.getAllProducts()));
                    dataOutputStream.flush();
                }
                else if (message.equals("getCategories")){
                    dataOutputStream.writeUTF(new Gson().toJson(Category.getAllCategories()));
                    dataOutputStream.flush();
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
