package fortnitestatsapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BootController {

    @FXML
    StackPane bootStackPane;

    @FXML
    public void initialize() {
        loadMenuView();
    }

    private void loadMenuView() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MenuView.fxml"));
        StackPane stackPane = null;
        try {
            stackPane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MenuController menuController = loader.getController();
        menuController.setBootController(this);
        setScreen(stackPane);
    }

    public void setScreen(Pane pane) {
        bootStackPane.getChildren().clear();
        bootStackPane.getChildren().add(pane);
    }
}
