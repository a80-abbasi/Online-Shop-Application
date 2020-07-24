package Client;

import Model.Account.Account;
import Model.Account.Admin;
import Model.Account.Customer;
import Model.Account.Seller;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class Connection {
    private static final int serverPort = 8000;

    private static String token;
    private static Socket clientSocket;

    private Connection(){}

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Connection.token = token;
    }

    public static void sendToServer(String message){
        try {
            clientSocket = new Socket("localhost", serverPort);
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendToServerWithToken(String message){
        sendToServer(createJsonObject(token, message));
    }

    private static String createJsonObject(String token, String content){
        return "{\"token\":\"" + token + "\",\"content\":\"" + content + "\"}";
    }

    public static String receiveFromServer(){
        if (clientSocket == null || !clientSocket.isConnected() || clientSocket.isClosed()){
            throw new RuntimeException("clientSocket is not connected");
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            return dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Account getLoggedInAccount(){
        Connection.sendToServerWithToken("get logged in account");
        return getAccountFromServer();
    }

    public static Account getAccountFromServer(){
        Gson gson = new Gson();
        String loggedInInfo = Connection.receiveFromServer();
        if (loggedInInfo.startsWith("Admin: ")){
            return gson.fromJson(loggedInInfo.substring("Admin: ".length()), Admin.class);
        }
        else if (loggedInInfo.startsWith("Customer: ")){
            return gson.fromJson(loggedInInfo.substring("Customer: ".length()), Customer.class);
        }
        else {
            return gson.fromJson(loggedInInfo.substring("Seller: ".length()), Seller.class);
        }
    }
}
