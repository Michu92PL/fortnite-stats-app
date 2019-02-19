package fortnitestatsapp.controllers;

import fortnitestatsapp.exceptions.PlayerNotFoundException;
import fortnitestatsapp.model.UserData;
import fortnitestatsapp.service.UserDataService;
import fortnitestatsapp.service.UserDataServiceImpl;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class StatsController {

    private UserData currentUser;

    @FXML
    private Button showStatsButton;

    @FXML
    private Button comparisonButton;

    private BooleanProperty userExist;

    @FXML
    private Pane pane;

    private UserDataService userDataService;

    private BootController bootController;

    @FXML
    private Label accountIDLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label epicNameLabel;

    @FXML
    private Label totalWinsLabel;

    @FXML
    private Label totalScoreLabel;

    @FXML
    private Label totalMatchesPlayedLabel;

    @FXML
    private Label totalWinPercentageLabel;

    @FXML
    private Label totalKillsLabel;

    @FXML
    private Label totalKDLabel;


    @FXML
    private Label soloWinsLabel;

    @FXML
    private Label soloKDLabel;

    @FXML
    private Label soloWinPercentageLabel;

    @FXML
    private Label soloKillsLabel;

    @FXML
    private Label soloMatchesPlayedLabel;

    @FXML
    private Label duoWinsLabel;

    @FXML
    private Label duoKDLabel;

    @FXML
    private Label duoWinPercentageLabel;

    @FXML
    private Label duoKillsLabel;

    @FXML
    private Label duoMatchesPlayedLabel;


    @FXML
    private Label teamWinsLabel;

    @FXML
    private Label teamKDLabel;

    @FXML
    private Label teamWinPercentageLabel;

    @FXML
    private Label teamKillsLabel;

    @FXML
    private Label teamMatchesPlayedLabel;


    @FXML
    private Label currentSoloWinsLabel;

    @FXML
    private Label currentSoloKDLabel;

    @FXML
    private Label currentSoloWinPercentageLabel;

    @FXML
    private Label currentSoloKillsLabel;

    @FXML
    private Label currentSoloMatchesPlayedLabel;


    @FXML
    private Label currentDuoWinsLabel;

    @FXML
    private Label currentDuoKDLabel;

    @FXML
    private Label currentDuoWinPercentageLabel;

    @FXML
    private Label currentDuoKillsLabel;

    @FXML
    private Label currentDuoMatchesPlayedLabel;


    @FXML
    private Label currentTeamWinsLabel;

    @FXML
    private Label currentTeamKDLabel;

    @FXML
    private Label currentTeamWinPercentageLabel;

    @FXML
    private Label currentTeamKillsLabel;

    @FXML
    private Label currentTeamMatchesPlayedLabel;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    public void initialize() {

        this.userDataService = new UserDataServiceImpl();
        choiceBox.getItems().addAll("PC", "PSN", "XBL");
        choiceBox.getSelectionModel().selectFirst();
        setAllLabelsEmpty();
        setBackgroundImage();
        //nameTextField.requestFocus();
        showStatsButton.setDefaultButton(true);

        userExist = new SimpleBooleanProperty(false);

        showStatsButton.disableProperty().bind(
                Bindings.isEmpty(nameTextField.textProperty()));

        comparisonButton.disableProperty().bind(userExist.not());

        Platform.runLater(() ->
                nameTextField.requestFocus()
        );
    }



    @FXML
    public void showStats() {
        if (checkTextField()) {
            try {
                UserData user = userDataService.getUser(choiceBox.getValue().toLowerCase(), nameTextField.getText());
                this.currentUser = user;
                setUserExist(true);
                user.setPlatform(choiceBox.getValue());
                setAllLabels(user);

            }
            catch (PlayerNotFoundException e){
                setUserExist(false);
                setAllLabelsEmpty();
            }
        }
    }

    private boolean checkTextField() {
        if (nameTextField.getText() == null || nameTextField.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    private void setBackgroundImage() {
        Image image = new Image("/img/statsBG2.jpg");
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
    }

    @FXML
    public void openMenu(){
        bootController.loadMenuView();
    }

    @FXML
    public void openComparison(){
        bootController.openComparison(currentUser);
    }

    private void setAllLabelsEmpty() {
        accountIDLabel.setText("-");
        epicNameLabel.setText("Not Found");
        totalWinsLabel.setText("-");
        totalScoreLabel.setText("-");
        totalMatchesPlayedLabel.setText("-");
        totalWinPercentageLabel.setText("-");
        totalKillsLabel.setText("-");
        totalKDLabel.setText("-");
        soloWinsLabel.setText("-");
        soloKDLabel.setText("-");
        soloWinPercentageLabel.setText("-");
        soloKillsLabel.setText("-");
        soloMatchesPlayedLabel.setText("-");
        duoWinsLabel.setText("-");
        duoKDLabel.setText("-");
        duoWinPercentageLabel.setText("-");
        duoKillsLabel.setText("-");
        duoMatchesPlayedLabel.setText("-");
        teamWinsLabel.setText("-");
        teamKDLabel.setText("-");
        teamWinPercentageLabel.setText("-");
        teamKillsLabel.setText("-");
        teamMatchesPlayedLabel.setText("-");
        currentSoloWinsLabel.setText("-");
        currentSoloKDLabel.setText("-");
        currentSoloWinPercentageLabel.setText("-");
        currentSoloKillsLabel.setText("-");
        currentSoloMatchesPlayedLabel.setText("-");
        currentDuoWinsLabel.setText("-");
        currentDuoKDLabel.setText("-");
        currentDuoWinPercentageLabel.setText("-");
        currentDuoKillsLabel.setText("-");
        currentDuoMatchesPlayedLabel.setText("-");
        currentTeamWinsLabel.setText("-");
        currentTeamKDLabel.setText("-");
        currentTeamWinPercentageLabel.setText("-");
        currentTeamKillsLabel.setText("-");
        currentTeamMatchesPlayedLabel.setText("-");
    }

    public void setBootController(BootController bootController) {
        this.bootController = bootController;
    }

    public void setCurrentUser(UserData currentUser) {
        this.currentUser = currentUser;
    }

    public void setUserExist(boolean userExist) {
        this.userExist.set(userExist);
    }

    private void setAllLabels(UserData user) {
        accountIDLabel.setText(user.getAccountID());
        epicNameLabel.setText(user.getEpicName());
        totalWinsLabel.setText(user.getTotalWins());
        totalScoreLabel.setText(user.getTotalScore());
        totalMatchesPlayedLabel.setText(user.getTotalMatchesPlayed());
        totalWinPercentageLabel.setText(user.getTotalWinPercentage());
        totalKillsLabel.setText(user.getTotalKills());
        totalKDLabel.setText(user.getTotalKD());
        soloWinsLabel.setText(user.getSoloWins());
        soloKDLabel.setText(user.getSoloKD());
        soloWinPercentageLabel.setText(user.getSoloWinPercentage());
        soloKillsLabel.setText(user.getSoloKills());
        soloMatchesPlayedLabel.setText(user.getSoloGamesPlayed());
        duoWinsLabel.setText(user.getDuoWins());
        duoKDLabel.setText(user.getDuoKD());
        duoWinPercentageLabel.setText(user.getDuoWinPercentage());
        duoKillsLabel.setText(user.getDuoKills());
        duoMatchesPlayedLabel.setText(user.getDuoGamesPlayed());
        teamWinsLabel.setText(user.getTeamWins());
        teamKDLabel.setText(user.getTeamKD());
        teamWinPercentageLabel.setText(user.getTeamWinPercentage());
        teamKillsLabel.setText(user.getTeamKills());
        teamMatchesPlayedLabel.setText(user.getTeamGamesPlayed());
        currentSoloWinsLabel.setText(user.getCurrentSoloWins());
        currentSoloKDLabel.setText(user.getCurrentSoloKD());
        currentSoloWinPercentageLabel.setText(user.getCurrentSoloWinPercentage());
        currentSoloKillsLabel.setText(user.getCurrentSoloKills());
        currentSoloMatchesPlayedLabel.setText(user.getCurrentSoloGamesPlayed());
        currentDuoWinsLabel.setText(user.getCurrentDuoWins());
        currentDuoKDLabel.setText(user.getCurrentDuoKD());
        currentDuoWinPercentageLabel.setText(user.getCurrentDuoWinPercentage());
        currentDuoKillsLabel.setText(user.getCurrentDuoKills());
        currentDuoMatchesPlayedLabel.setText(user.getCurrentDuoGamesPlayed());
        currentTeamWinsLabel.setText(user.getCurrentTeamWins());
        currentTeamKDLabel.setText(user.getCurrentTeamKD());
        currentTeamWinPercentageLabel.setText(user.getCurrentTeamWinPercentage());
        currentTeamKillsLabel.setText(user.getCurrentTeamKills());
        currentTeamMatchesPlayedLabel.setText(user.getCurrentTeamGamesPlayed());
    }
}
