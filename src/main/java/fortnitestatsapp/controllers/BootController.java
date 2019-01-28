package fortnitestatsapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class BootController {

    @FXML
    StackPane bootStackPane;

    @FXML
    public void initialize() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainView.fxml"));
        StackPane stackPane = null;
        try {
            stackPane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        bootStackPane.getChildren().add(stackPane);
    }
}
