package fortnitestatsapp.controllers;

import fortnitestatsapp.model.UserData;
import fortnitestatsapp.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class StatsController {

    @FXML
    private Pane pane;

    private Service service;

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
        this.service = new Service();
        choiceBox.getItems().addAll("PC","PSN", "XBL");
        choiceBox.getSelectionModel().selectFirst();
        setAllLabelsEmpty();

    }

    @FXML
    public void showStats(){
        if(checkTextField()) {
            UserData user = service.getUserData(choiceBox.getValue().toLowerCase(), nameTextField.getText());
            if(checkUserData(user)) {
                setAllLabels(user);
            }
            else{
                setAllLabelsEmpty();
            }
        }
    }
    private boolean checkUserData(UserData user) {
        if(user.getAccountID() == null){
            return false;
        }
        return true;
    }

    private boolean checkTextField(){
        if(nameTextField.getText() == null || nameTextField.getText().trim().isEmpty()){
            return false;
        }
        return true;
    }

    private void setAllLabelsEmpty() {
        accountIDLabel.setText("-");
        epicNameLabel.setText("Nie znaleziono");
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
