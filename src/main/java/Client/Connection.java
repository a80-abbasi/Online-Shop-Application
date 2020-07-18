package Client;

import java.io.*;
import java.net.Socket;

public class Connection {
    private static final int serverPort = 8080;

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

    public static String receiveFromServer(){
        if (!clientSocket.isConnected() || clientSocket.isClosed()){
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
}
