package Server;

import java.io.*;
import java.net.Socket;

public class BankConnection {
    public static final int PORT = 2222;
    public static final String IP = "127.0.0.1";

    private static DataOutputStream outputStream;
    private static DataInputStream inputStream;

    private static void connectToBank() {
        try {
            Socket socket = new Socket(IP, PORT);
            outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getMessageFromBank() {
        try {
            return inputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void sendMessageToBank(String msg) {
        try {
            outputStream.writeUTF(msg);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String creatAccount(String firstName, String lastName, String userName, String password){
        connectToBank();
        sendMessageToBank(String.format("%s %s %s %s %s %s", "create_account", firstName, lastName, userName, password, password));
        String message = getMessageFromBank();
        exit();
        return message;
    }

    public static String getToken(String userName, String password){
        connectToBank();
        sendMessageToBank(String.format("get_token %s %s", userName, password));
        String message = getMessageFromBank();
        exit();
        return message;
    }

    public static String deposit(String username, String password, int money, String destinationID) throws Exception {
        String token = getToken(username, password);
        if (token.equalsIgnoreCase("invalid username or password")){
            throw new Exception(token);
        }
        connectToBank();
        sendMessageToBank(String.format("create_receipt %s %s %d %s %s %s", token, "deposit", money, "-1", destinationID, ""));
        String receiptID = getMessageFromBank();
        sendMessageToBank(String.format("pay %s", receiptID));
        String message = getMessageFromBank();
        if (!message.equalsIgnoreCase("done successfully")){
            throw new Exception(message);
        }
        exit();
        return message;
    }

    public static String getBalance(String userName, String password) throws Exception {
        String token = getToken(userName, password);
        if (token.equalsIgnoreCase("invalid username or password")){
            throw new Exception(token);
        }
        connectToBank();
        sendMessageToBank(String.format("get_balance %s", token));
        String message = getMessageFromBank();
        exit();
        return message;
    }

    private static void exit(){
        sendMessageToBank("exit");
    }
}
