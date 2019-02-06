package fortnitestatsapp.controllers;

import fortnitestatsapp.exceptions.BothPlayersNotFoundException;
import fortnitestatsapp.exceptions.FirstPlayerNotFoundException;
import fortnitestatsapp.exceptions.PlayerNotFoundException;
import fortnitestatsapp.exceptions.SecondPlayerNotFoundException;
import fortnitestatsapp.model.UserData;
import fortnitestatsapp.service.Service;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ComparisonController {

    private UserData player1;
    private UserData player2;

    private Service service;

    private static final String EMPTY_STRING = "-";
    private static final String[] CHOICE_BOX_ELEMENTS = {"PC", "PSN", "XBL"};

    @FXML
    private ChoiceBox<String> choiceBox1;

    @FXML
    private ChoiceBox<String> choiceBox2;

    @FXML
    private TextField p1TextField;

    @FXML
    private TextField p2TextField;

    @FXML
    private Button compareButton;

    @FXML
    private Label p1nameLabel;

    @FXML
    private Label p2nameLabel;

    @FXML
    private Label p1LifeTimeKD;

    @FXML
    private Label p1LifetimeWins;

    @FXML
    private Label p1LifetimeMatches;

    @FXML
    private Label p1LifetimeWinPercentage;

    @FXML
    private Label p1LifetimeKills;

    @FXML
    private Label p2LifeTimeKD;

    @FXML
    private Label p2LifetimeWins;

    @FXML
    private Label p2LifetimeMatches;

    @FXML
    private Label p2LifetimeWinPercentage;

    @FXML
    private Label p2LifetimeKills;

    @FXML
    private Label p1SoloKD;

    @FXML
    private Label p1SoloWins;

    @FXML
    private Label p1SoloMatches;

    @FXML
    private Label p1SoloWinPercentage;

    @FXML
    private Label p1SoloKills;

    @FXML
    private Label p2SoloKD;

    @FXML
    private Label p2SoloWins;

    @FXML
    private Label p2SoloMatches;

    @FXML
    private Label p2SoloWinPercentage;

    @FXML
    private Label p2SoloKills;

    @FXML
    private Label p1DuoKD;

    @FXML
    private Label p1DuoWins;

    @FXML
    private Label p1DuoMatches;

    @FXML
    private Label p1DuoWinPercentage;

    @FXML
    private Label p1DuoKills;

    @FXML
    private Label p2DuoKD;

    @FXML
    private Label p2DuoWins;

    @FXML
    private Label p2DuoMatches;

    @FXML
    private Label p2DuoWinPercentage;

    @FXML
    private Label p2DuoKills;

    @FXML
    private Label p1SquadsKD;

    @FXML
    private Label p1SquadsWins;

    @FXML
    private Label p1SquadsMatches;

    @FXML
    private Label p1SquadsWinPercentage;

    @FXML
    private Label p1SquadsKills;

    @FXML
    private Label p2SquadsKD;

    @FXML
    private Label p2SquadsWins;

    @FXML
    private Label p2SquadsMatches;

    @FXML
    private Label p2SquadsWinPercentage;

    @FXML
    private Label p2SquadsKills;

    @FXML
    private Label statusLabel;


    @FXML
    public void initialize(){
        service = new Service();

        choiceBox1.getItems().addAll(CHOICE_BOX_ELEMENTS);
        choiceBox2.getItems().addAll(CHOICE_BOX_ELEMENTS);
        setAllLabelsEmpty();
        choiceBox1.getSelectionModel().selectFirst();
        choiceBox2.getSelectionModel().selectFirst();

        Platform.runLater(() ->
                p1TextField.requestFocus()
        );

        BooleanBinding booleanBind = p1TextField.textProperty().isEmpty()
                .or(p2TextField.textProperty().isEmpty());

        compareButton.disableProperty().bind(booleanBind);
    }

    @FXML
    public void compare(){
        if (checkTextField(p1TextField) && checkTextField(p2TextField)) {

            if(!(checkIfTwoPlayersAreSame(p1TextField.getText(), choiceBox1.getValue(), p2TextField.getText(), choiceBox2.getValue()))) {
                UserData player1 = service.getUserData(choiceBox1.getValue().toLowerCase(), p1TextField.getText());

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                UserData player2 = service.getUserData(choiceBox2.getValue().toLowerCase(), p2TextField.getText());

                this.player1 = player1;
                this.player2 = player2;

                try {
                    compare(player1, player2);
                    p1nameLabel.setText(player1.getEpicName());
                    p2nameLabel.setText(player2.getEpicName());
                    setP1StatsLabels();
                    setP2StatsLabels();
                    statusLabel.setText("OK");
                } catch (PlayerNotFoundException e) {
                    statusLabel.setText(e.getMessage());
                    if(e instanceof BothPlayersNotFoundException){
                        setAllLabelsEmpty();
                    }
                    else if(e instanceof FirstPlayerNotFoundException){
                        setP2StatsLabels();
                        setAllP1LabelsEmpty();
                    }
                    else if(e instanceof SecondPlayerNotFoundException){
                        setP1StatsLabels();
                        setAllP2LabelsEmpty();
                    }
                }
            }
            else {
                setBothPlayersAreTheSameStatus();
            }
        }
        else{
            setEmptyFieldsStatus();
        }
    }

    private void setEmptyFieldsStatus() {
        statusLabel.setText("Enter proper names!");
    }

    private void compare(UserData player1, UserData player2){
            checkUserData(player1, player2);
    }

    private void checkUserData(UserData player1, UserData player2) {
        int errorCode = 0;

        if (player1.getAccountID() == null) {
            errorCode =+ 1;
        }
        if(player2.getAccountID() == null){
            errorCode +=2;
        }
        PlayerNotFoundExThrower(errorCode);


    }

    private void PlayerNotFoundExThrower(int errorCode){
        switch(errorCode){
            case 3:
                throw new BothPlayersNotFoundException("Both players not found!");
            case 2:
                throw new SecondPlayerNotFoundException("Player 2 not found!");
            case 1:
                throw new FirstPlayerNotFoundException("Player 1 not found!");
            default:
        }
    }

    public void setPlayer1(UserData player1) {
        this.player1 = player1;
    }

    public void setPlayer2(UserData player2) {
        this.player2 = player2;
    }

    private void setAllLabelsEmpty(){
        setAllP1LabelsEmpty();
        setAllP2LabelsEmpty();
    }

    private boolean checkTextField(TextField tf) {
        if (tf.getText() == null || tf.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean checkIfTwoPlayersAreSame(String player1, String platform1, String player2, String platform2 ){
        String p1Name = player1.toLowerCase().trim();
        String p2Name = player2.toLowerCase().trim();

        if(p1Name.equals(p2Name)){
            if(platform1.equals(platform2)){
                return true;
            }
        }
        return false;
    }

    private void setBothPlayersAreTheSameStatus(){
        statusLabel.setText("Don't test me please :)");
    }

    private void setAllP1LabelsEmpty(){
        String e = EMPTY_STRING;
        p1LifeTimeKD.setText(e);
        p1LifetimeWins.setText(e);
        p1LifetimeMatches.setText(e);
        p1LifetimeWinPercentage.setText(e);
        p1LifetimeKills.setText(e);

        p1SoloKD.setText(e);
        p1SoloWins.setText(e);
        p1SoloMatches.setText(e);
        p1SoloWinPercentage.setText(e);
        p1SoloKills.setText(e);

        p1DuoKD.setText(e);
        p1DuoWins.setText(e);
        p1DuoMatches.setText(e);
        p1DuoWinPercentage.setText(e);
        p1DuoKills.setText(e);

        p1SquadsKD.setText(e);
        p1SquadsWins.setText(e);
        p1SquadsMatches.setText(e);
        p1SquadsWinPercentage.setText(e);
        p1SquadsKills.setText(e);
    }

    private void setAllP2LabelsEmpty(){
        String e = EMPTY_STRING;

        p2LifeTimeKD.setText(e);
        p2LifetimeWins.setText(e);
        p2LifetimeMatches.setText(e);
        p2LifetimeWinPercentage.setText(e);
        p2LifetimeKills.setText(e);

        p2SoloKD.setText(e);
        p2SoloWins.setText(e);
        p2SoloMatches.setText(e);
        p2SoloWinPercentage.setText(e);
        p2SoloKills.setText(e);

        p2DuoKD.setText(e);
        p2DuoWins.setText(e);
        p2DuoMatches.setText(e);
        p2DuoWinPercentage.setText(e);
        p2DuoKills.setText(e);

        p2SquadsKD.setText(e);
        p2SquadsWins.setText(e);
        p2SquadsMatches.setText(e);
        p2SquadsWinPercentage.setText(e);
        p2SquadsKills.setText(e);
    }

    public void setP1StatsLabels(){

        p1LifeTimeKD.setText(player1.getTotalKD());
        p1LifetimeWins.setText(player1.getTotalWins());
        p1LifetimeMatches.setText(player1.getTotalMatchesPlayed());
        p1LifetimeWinPercentage.setText(player1.getTotalWinPercentage());
        p1LifetimeKills.setText(player1.getTotalKills());

        p1SoloKD.setText(player1.getSoloKD());
        p1SoloWins.setText(player1.getSoloWins());
        p1SoloMatches.setText(player1.getSoloGamesPlayed());
        p1SoloWinPercentage.setText(player1.getSoloWinPercentage());
        p1SoloKills.setText(player1.getSoloKills());

        p1DuoKD.setText(player1.getDuoKD());
        p1DuoWins.setText(player1.getDuoWins());
        p1DuoMatches.setText(player1.getDuoGamesPlayed());
        p1DuoWinPercentage.setText(player1.getDuoWinPercentage());
        p1DuoKills.setText(player1.getDuoKills());

        p1SquadsKD.setText(player1.getTeamKD());
        p1SquadsWins.setText(player1.getTeamWins());
        p1SquadsMatches.setText(player1.getTeamGamesPlayed());
        p1SquadsWinPercentage.setText(player1.getTeamWinPercentage());
        p1SquadsKills.setText(player1.getTeamWins());
    }

    public void setP2StatsLabels(){

        p2LifeTimeKD.setText(player2.getTotalKD());
        p2LifetimeWins.setText(player2.getTotalWins());
        p2LifetimeMatches.setText(player2.getTotalMatchesPlayed());
        p2LifetimeWinPercentage.setText(player2.getTotalWinPercentage());
        p2LifetimeKills.setText(player2.getTotalKills());

        p2SoloKD.setText(player2.getSoloKD());
        p2SoloWins.setText(player2.getSoloWins());
        p2SoloMatches.setText(player2.getSoloGamesPlayed());
        p2SoloWinPercentage.setText(player2.getSoloWinPercentage());
        p2SoloKills.setText(player2.getSoloKills());

        p2DuoKD.setText(player2.getDuoKD());
        p2DuoWins.setText(player2.getDuoWins());
        p2DuoMatches.setText(player2.getDuoGamesPlayed());
        p2DuoWinPercentage.setText(player2.getDuoWinPercentage());
        p2DuoKills.setText(player2.getDuoKills());

        p2SquadsKD.setText(player2.getTeamKD());
        p2SquadsWins.setText(player2.getTeamWins());
        p2SquadsMatches.setText(player2.getTeamGamesPlayed());
        p2SquadsWinPercentage.setText(player2.getTeamWinPercentage());
        p2SquadsKills.setText(player2.getTeamWins());
    }

    public ChoiceBox<String> getChoiceBox1() {
        return choiceBox1;
    }

    public void setChoiceBox1(ChoiceBox<String> choiceBox1) {
        this.choiceBox1 = choiceBox1;
    }

    public ChoiceBox<String> getChoiceBox2() {
        return choiceBox2;
    }

    public void setChoiceBox2(ChoiceBox<String> choiceBox2) {
        this.choiceBox2 = choiceBox2;
    }

    public TextField getP1TextField() {
        return p1TextField;
    }

    public void setP1TextField(TextField p1TextField) {
        this.p1TextField = p1TextField;
    }

    public TextField getP2TextField() {
        return p2TextField;
    }

    public void setP2TextField(TextField p2TextField) {
        this.p2TextField = p2TextField;
    }

    public Label getP1nameLabel() {
        return p1nameLabel;
    }

    public void setP1nameLabel(Label p1nameLabel) {
        this.p1nameLabel = p1nameLabel;
    }

    public Label getP2nameLabel() {
        return p2nameLabel;
    }

    public void setP2nameLabel(Label p2NameLabel) {
        this.p2nameLabel = p2NameLabel;
    }

    public Label getP1LifeTimeKD() {
        return p1LifeTimeKD;
    }

    public void setP1LifeTimeKD(Label p1LifeTimeKD) {
        this.p1LifeTimeKD = p1LifeTimeKD;
    }

    public Label getP1LifetimeWins() {
        return p1LifetimeWins;
    }

    public void setP1LifetimeWins(Label p1LifetimeWins) {
        this.p1LifetimeWins = p1LifetimeWins;
    }

    public Label getP1LifetimeMatches() {
        return p1LifetimeMatches;
    }

    public void setP1LifetimeMatches(Label p1LifetimeMatches) {
        this.p1LifetimeMatches = p1LifetimeMatches;
    }

    public Label getP1LifetimeWinPercentage() {
        return p1LifetimeWinPercentage;
    }

    public void setP1LifetimeWinPercentage(Label p1LifetimeWinPercentage) {
        this.p1LifetimeWinPercentage = p1LifetimeWinPercentage;
    }

    public Label getP1LifetimeKills() {
        return p1LifetimeKills;
    }

    public void setP1LifetimeKills(Label p1LifetimeKills) {
        this.p1LifetimeKills = p1LifetimeKills;
    }

    public Label getP2LifeTimeKD() {
        return p2LifeTimeKD;
    }

    public void setP2LifeTimeKD(Label p2LifeTimeKD) {
        this.p2LifeTimeKD = p2LifeTimeKD;
    }

    public Label getP2LifetimeWins() {
        return p2LifetimeWins;
    }

    public void setP2LifetimeWins(Label p2LifetimeWins) {
        this.p2LifetimeWins = p2LifetimeWins;
    }

    public Label getP2LifetimeMatches() {
        return p2LifetimeMatches;
    }

    public void setP2LifetimeMatches(Label p2LifetimeMatches) {
        this.p2LifetimeMatches = p2LifetimeMatches;
    }

    public Label getP2LifetimeWinPercentage() {
        return p2LifetimeWinPercentage;
    }

    public void setP2LifetimeWinPercentage(Label p2LifetimeWinPercentage) {
        this.p2LifetimeWinPercentage = p2LifetimeWinPercentage;
    }

    public Label getP2LifetimeKills() {
        return p2LifetimeKills;
    }

    public void setP2LifetimeKills(Label p2LifetimeKills) {
        this.p2LifetimeKills = p2LifetimeKills;
    }

    public Label getP1SoloKD() {
        return p1SoloKD;
    }

    public void setP1SoloKD(Label p1SoloKD) {
        this.p1SoloKD = p1SoloKD;
    }

    public Label getP1SoloWins() {
        return p1SoloWins;
    }

    public void setP1SoloWins(Label p1SoloWins) {
        this.p1SoloWins = p1SoloWins;
    }

    public Label getP1SoloMatches() {
        return p1SoloMatches;
    }

    public void setP1SoloMatches(Label p1SoloMatches) {
        this.p1SoloMatches = p1SoloMatches;
    }

    public Label getP1SoloWinPercentage() {
        return p1SoloWinPercentage;
    }

    public void setP1SoloWinPercentage(Label p1SoloWinPercentage) {
        this.p1SoloWinPercentage = p1SoloWinPercentage;
    }

    public Label getP1SoloKills() {
        return p1SoloKills;
    }

    public void setP1SoloKills(Label p1SoloKills) {
        this.p1SoloKills = p1SoloKills;
    }

    public Label getP2SoloKD() {
        return p2SoloKD;
    }

    public void setP2SoloKD(Label p2SoloKD) {
        this.p2SoloKD = p2SoloKD;
    }

    public Label getP2SoloWins() {
        return p2SoloWins;
    }

    public void setP2SoloWins(Label p2SoloWins) {
        this.p2SoloWins = p2SoloWins;
    }

    public Label getP2SoloMatches() {
        return p2SoloMatches;
    }

    public void setP2SoloMatches(Label p2SoloMatches) {
        this.p2SoloMatches = p2SoloMatches;
    }

    public Label getP2SoloWinPercentage() {
        return p2SoloWinPercentage;
    }

    public void setP2SoloWinPercentage(Label p2SoloWinPercentage) {
        this.p2SoloWinPercentage = p2SoloWinPercentage;
    }

    public Label getP2SoloKills() {
        return p2SoloKills;
    }

    public void setP2SoloKills(Label p2SoloKills) {
        this.p2SoloKills = p2SoloKills;
    }

    public Label getP1DuoKD() {
        return p1DuoKD;
    }

    public void setP1DuoKD(Label p1DuoKD) {
        this.p1DuoKD = p1DuoKD;
    }

    public Label getP1DuoWins() {
        return p1DuoWins;
    }

    public void setP1DuoWins(Label p1DuoWins) {
        this.p1DuoWins = p1DuoWins;
    }

    public Label getP1DuoMatches() {
        return p1DuoMatches;
    }

    public void setP1DuoMatches(Label p1DuoMatches) {
        this.p1DuoMatches = p1DuoMatches;
    }

    public Label getP1DuoWinPercentage() {
        return p1DuoWinPercentage;
    }

    public void setP1DuoWinPercentage(Label p1DuoWinPercentage) {
        this.p1DuoWinPercentage = p1DuoWinPercentage;
    }

    public Label getP1DuoKills() {
        return p1DuoKills;
    }

    public void setP1DuoKills(Label p1DuoKills) {
        this.p1DuoKills = p1DuoKills;
    }

    public Label getP2DuoKD() {
        return p2DuoKD;
    }

    public void setP2DuoKD(Label p2DuoKD) {
        this.p2DuoKD = p2DuoKD;
    }

    public Label getP2DuoWins() {
        return p2DuoWins;
    }

    public void setP2DuoWins(Label p2DuoWins) {
        this.p2DuoWins = p2DuoWins;
    }

    public Label getP2DuoMatches() {
        return p2DuoMatches;
    }

    public void setP2DuoMatches(Label p2DuoMatches) {
        this.p2DuoMatches = p2DuoMatches;
    }

    public Label getP2DuoWinPercentage() {
        return p2DuoWinPercentage;
    }

    public void setP2DuoWinPercentage(Label p2DuoWinPercentage) {
        this.p2DuoWinPercentage = p2DuoWinPercentage;
    }

    public Label getP2DuoKills() {
        return p2DuoKills;
    }

    public void setP2DuoKills(Label p2DuoKills) {
        this.p2DuoKills = p2DuoKills;
    }

    public Label getP1SquadsKD() {
        return p1SquadsKD;
    }

    public void setP1SquadsKD(Label p1SquadsKD) {
        this.p1SquadsKD = p1SquadsKD;
    }

    public Label getP1SquadsWins() {
        return p1SquadsWins;
    }

    public void setP1SquadsWins(Label p1SquadsWins) {
        this.p1SquadsWins = p1SquadsWins;
    }

    public Label getP1SquadsMatches() {
        return p1SquadsMatches;
    }

    public void setP1SquadsMatches(Label p1SquadsMatches) {
        this.p1SquadsMatches = p1SquadsMatches;
    }

    public Label getP1SquadsWinPercentage() {
        return p1SquadsWinPercentage;
    }

    public void setP1SquadsWinPercentage(Label p1SquadsWinPercentage) {
        this.p1SquadsWinPercentage = p1SquadsWinPercentage;
    }

    public Label getP1SquadsKills() {
        return p1SquadsKills;
    }

    public void setP1SquadsKills(Label p1SquadsKills) {
        this.p1SquadsKills = p1SquadsKills;
    }

    public Label getP2SquadsKD() {
        return p2SquadsKD;
    }

    public void setP2SquadsKD(Label p2SquadsKD) {
        this.p2SquadsKD = p2SquadsKD;
    }

    public Label getP2SquadsWins() {
        return p2SquadsWins;
    }

    public void setP2SquadsWins(Label p2SquadsWins) {
        this.p2SquadsWins = p2SquadsWins;
    }

    public Label getP2SquadsMatches() {
        return p2SquadsMatches;
    }

    public void setP2SquadsMatches(Label p2SquadsMatches) {
        this.p2SquadsMatches = p2SquadsMatches;
    }

    public Label getP2SquadsWinPercentage() {
        return p2SquadsWinPercentage;
    }

    public void setP2SquadsWinPercentage(Label p2SquadsWinPercentage) {
        this.p2SquadsWinPercentage = p2SquadsWinPercentage;
    }

    public Label getP2SquadsKills() {
        return p2SquadsKills;
    }

    public void setP2SquadsKills(Label p2SquadsKills) {
        this.p2SquadsKills = p2SquadsKills;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(Label statusLabel) {
        this.statusLabel = statusLabel;
    }
}
