package fortnitestatsapp.controllers;

import fortnitestatsapp.dialogs.DialogUtils;
import fortnitestatsapp.model.UserData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class BootController {

    @FXML
    StackPane bootStackPane;

    @FXML
    public void initialize() {
        loadMenuView();
    }

    public void openComparison(UserData player1) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ComparisonView.fxml"));
        StackPane stackPane = null;
        try {
            stackPane = loader.load();
        }
        catch (IOException e) {
            DialogUtils.errorDialog(e);
        }

        ComparisonController controller = loader.getController();
        controller.setPlayer1(player1);
        controller.setPlayerStatsLabels("p1", player1);
        controller.getChoiceBox1().getSelectionModel().select(player1.getPlatform());
        controller.getP1TextField().setText(player1.getEpicName());

        Platform.runLater(() ->
                controller.getP2TextField().requestFocus()
        );

        Stage stage = new Stage();

        Scene scene = new Scene(stackPane);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/img/icon.png")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Comparison mode");
        stage.show();
    }

    public void loadMenuView() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MenuView.fxml"));
        StackPane stackPane = null;
        try {
            stackPane = loader.load();
        }
        catch (Exception e) {
            DialogUtils.errorDialog(e);
        }

        Image image = new Image("/img/menubottomBG.png", 800, 600, false, true);

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

    public void openComparison() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ComparisonView.fxml"));
        StackPane stackPane = null;
        try {
            stackPane = loader.load();
        }
        catch (IOException e) {
            DialogUtils.errorDialog(e);
        }

        Stage stage = new Stage();

        Scene scene = new Scene(stackPane);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/img/icon.png")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Comparison mode");
        stage.show();
    }
}
