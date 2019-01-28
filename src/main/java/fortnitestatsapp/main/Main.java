package fortnitestatsapp.main;

import fortnitestatsapp.model.UserData;
import fortnitestatsapp.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        launch(args);

        //Service service = new Service();
        //UserData user = service.getUserData("pc","Yoghurtt");
        //System.out.println(user);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/BootView.fxml"));
        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane);

        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/img/icon.png")));

        //primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fortnite stats");
        primaryStage.show();
    }
}
