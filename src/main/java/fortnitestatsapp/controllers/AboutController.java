package fortnitestatsapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class AboutController {

    private BootController bootController;

    @FXML
    private StackPane stackPane;

    @FXML
    public void initialize() {
        setBackgroundImage();
    }

    @FXML
    public void openMenu() {
        bootController.loadMenuView();
    }

    public void setBootController(BootController bootController) {
        this.bootController = bootController;
    }

    private void setBackgroundImage() {
        Image image = new Image("/img/aboutBG.jpg");
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        stackPane.setBackground(new Background(myBI));
    }
}
