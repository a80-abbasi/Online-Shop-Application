package graphics;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class Secondary {
    public Button secondaryButton;

    public void switchToPrimary(ActionEvent actionEvent) throws IOException {
        App.setRoot("primary");
    }
}
