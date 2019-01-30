package fortnitestatsapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

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

        Image image = new Image("/img/menuBottomv2Final.png", 800, 600, false, true);

        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        stackPane.setBackground(new Background(myBI));

        MenuController menuController = loader.getController();
        menuController.setBootController(this);
        setScreen(stackPane);
    }

    public void setScreen(Pane pane) {
        bootStackPane.getChildren().clear();
        bootStackPane.getChildren().add(pane);
    }
}
