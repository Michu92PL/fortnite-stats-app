package fortnitestatsapp.dialogs;

import javafx.scene.control.Alert;

public class DialogUtils {

    public static void errorDialog(Exception e){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Sorry, something went wrong!");
        //e.printStackTrace();
        //errorAlert.setContentText(e.getMessage());
        errorAlert.setContentText(e.getCause().toString());
        errorAlert.show();
    }
}
