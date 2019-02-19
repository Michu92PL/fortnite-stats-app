package fortnitestatsapp.controllers;

import fortnitestatsapp.dialogs.DialogUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class MenuController {

    private BootController bootController;

    @FXML
    public void openStats() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/StatsView.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }
        catch (Exception e){
            //e.printStackTrace();
            DialogUtils.errorDialog(e);
        }
        StatsController statsController = loader.getController();
        statsController.setBootController(bootController);
        bootController.setScreen(pane);
    }

    @FXML
    public void openAbout(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AboutView.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }
        catch (Exception e){
            //e.printStackTrace();
            DialogUtils.errorDialog(e);
        }
        AboutController aboutController = loader.getController();
        aboutController.setBootController(bootController);
        bootController.setScreen(pane);
    }

    @FXML
    public void openComparison() {
        bootController.openComparison();
    }

    public void exit() {
        Platform.exit();
        System.exit(0);
    }

    public void setBootController(BootController bootController) {
        this.bootController = bootController;
    }
}
