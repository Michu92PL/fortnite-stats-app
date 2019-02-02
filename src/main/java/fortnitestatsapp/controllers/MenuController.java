package fortnitestatsapp.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class MenuController {

    private BootController bootController;


    public void openStats() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/StatsView.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        StatsController statsController = loader.getController();
        statsController.setBootController(bootController);
        bootController.setScreen(pane);

    }

    public void openComparison() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ComparisonView.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        bootController.setScreen(pane);

    }

    public void exit() {
        Platform.exit();
        System.exit(0);

    }

    public void setBootController(BootController bootController) {
        this.bootController = bootController;
    }
}
