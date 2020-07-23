package graphics;

import javafx.scene.input.MouseEvent;

public class SupporterProfileMenu {
    public void ReadyForSupporting(MouseEvent mouseEvent) throws Exception {
        Server.ChatServer.main();
        Client.ChatClient.main();
    }
}
