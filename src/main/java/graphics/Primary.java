package graphics;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class Primary {
    public Button primaryButton;

    public void switchToSecondary(ActionEvent actionEvent) throws IOException {
        App.setRoot("secondary");
    }
}
