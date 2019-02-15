package fortnitestatsapp.controllers;

import fortnitestatsapp.annotations.StatsLabel;
import fortnitestatsapp.dialogs.DialogUtils;
import fortnitestatsapp.exceptions.BothPlayersNotFoundException;
import fortnitestatsapp.exceptions.FirstPlayerNotFoundException;
import fortnitestatsapp.exceptions.PlayerNotFoundException;
import fortnitestatsapp.exceptions.SecondPlayerNotFoundException;
import fortnitestatsapp.model.UserData;
import fortnitestatsapp.service.UserDataService;
import fortnitestatsapp.service.UserDataServiceImpl;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComparisonController {

    private UserData player1;
    private UserData player2;

    private static final String EMPTY = "-";
    private static final String[] CHOICE_BOX_ELEMENTS = {"PC", "PSN", "XBL"};
    private static final String COLOR_RED = "-fx-text-fill: red";
    private static final String COLOR_GREEN = "-fx-text-fill: green";
    private static final String COLOR_BLUE = "-fx-text-fill: blue;";

    private UserDataService userDataService;

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

    @StatsLabel(player = "p1", label = "nameLabel")
    @FXML
    private Label p1nameLabel;

    @StatsLabel(player = "p2", label = "nameLabel")
    @FXML
    private Label p2nameLabel;

    @StatsLabel(player = "p1", label = "LifeTimeKD")
    @FXML
    private Label p1LifeTimeKD;

    @StatsLabel(player = "p1", label = "LifeTimeWins")
    @FXML
    private Label p1LifetimeWins;

    @StatsLabel(player = "p1", label = "LifeTimeMatches")
    @FXML
    private Label p1LifetimeMatches;

    @StatsLabel(player = "p1", label = "LifeTimeWinPercentage")
    @FXML
    private Label p1LifetimeWinPercentage;

    @StatsLabel(player = "p1", label = "LifeTimeKills")
    @FXML
    private Label p1LifetimeKills;

    @StatsLabel(player = "p2", label = "LifeTimeKD")
    @FXML
    private Label p2LifeTimeKD;

    @StatsLabel(player = "p2", label = "LifeTimeWins")
    @FXML
    private Label p2LifetimeWins;

    @StatsLabel(player = "p2", label = "LifeTimeMatches")
    @FXML
    private Label p2LifetimeMatches;

    @StatsLabel(player = "p2", label = "LifeTimeWinPercentage")
    @FXML
    private Label p2LifetimeWinPercentage;

    @StatsLabel(player = "p2", label = "LifeTimeKills")
    @FXML
    private Label p2LifetimeKills;

    @StatsLabel(player = "p1", label = "SoloKD")
    @FXML
    private Label p1SoloKD;

    @StatsLabel(player = "p1", label = "SoloWins")
    @FXML
    private Label p1SoloWins;

    @StatsLabel(player = "p1", label = "SoloMatches")
    @FXML
    private Label p1SoloMatches;

    @StatsLabel(player = "p1", label = "SoloWinPercentage")
    @FXML
    private Label p1SoloWinPercentage;

    @StatsLabel(player = "p1", label = "SoloKills")
    @FXML
    private Label p1SoloKills;

    @StatsLabel(player = "p2", label = "SoloKD")
    @FXML
    private Label p2SoloKD;

    @StatsLabel(player = "p2", label = "SoloWins")
    @FXML
    private Label p2SoloWins;

    @StatsLabel(player = "p2", label = "SoloMatches")
    @FXML
    private Label p2SoloMatches;

    @StatsLabel(player = "p2", label = "SoloWinPercentage")
    @FXML
    private Label p2SoloWinPercentage;

    @StatsLabel(player = "p2", label = "SoloKills")
    @FXML
    private Label p2SoloKills;

    @StatsLabel(player = "p1", label = "DuoKD")
    @FXML
    private Label p1DuoKD;

    @StatsLabel(player = "p1", label = "DuoWins")
    @FXML
    private Label p1DuoWins;

    @StatsLabel(player = "p1", label = "DuoMatches")
    @FXML
    private Label p1DuoMatches;

    @StatsLabel(player = "p1", label = "DuoWinPercentage")
    @FXML
    private Label p1DuoWinPercentage;

    @StatsLabel(player = "p1", label = "DuoKills")
    @FXML
    private Label p1DuoKills;

    @StatsLabel(player = "p2", label = "DuoKD")
    @FXML
    private Label p2DuoKD;

    @StatsLabel(player = "p2", label = "DuoWins")
    @FXML
    private Label p2DuoWins;

    @StatsLabel(player = "p2", label = "DuoMatches")
    @FXML
    private Label p2DuoMatches;

    @StatsLabel(player = "p2", label = "DuoWinPercentage")
    @FXML
    private Label p2DuoWinPercentage;

    @StatsLabel(player = "p2", label = "DuoKills")
    @FXML
    private Label p2DuoKills;

    @StatsLabel(player = "p1", label = "SquadsKD")
    @FXML
    private Label p1SquadsKD;

    @StatsLabel(player = "p1", label = "SquadsWins")
    @FXML
    private Label p1SquadsWins;

    @StatsLabel(player = "p1", label = "SquadsMatches")
    @FXML
    private Label p1SquadsMatches;

    @StatsLabel(player = "p1", label = "SquadsWinPercentage")
    @FXML
    private Label p1SquadsWinPercentage;

    @StatsLabel(player = "p1", label = "SquadsKills")
    @FXML
    private Label p1SquadsKills;

    @StatsLabel(player = "p2", label = "SquadsKD")
    @FXML
    private Label p2SquadsKD;

    @StatsLabel(player = "p2", label = "SquadsWins")
    @FXML
    private Label p2SquadsWins;

    @StatsLabel(player = "p2", label = "SquadsMatches")
    @FXML
    private Label p2SquadsMatches;

    @StatsLabel(player = "p2", label = "SquadsWinPercentage")
    @FXML
    private Label p2SquadsWinPercentage;

    @StatsLabel(player = "p2", label = "SquadsKills")
    @FXML
    private Label p2SquadsKills;

    @FXML
    private Label statusLabel;


    @FXML
    public void initialize() {
        userDataService = new UserDataServiceImpl();
        choiceBox1.getItems().addAll(CHOICE_BOX_ELEMENTS);
        choiceBox2.getItems().addAll(CHOICE_BOX_ELEMENTS);
        setLabelText(EMPTY, getPlayerStatsLabelList());
        choiceBox1.getSelectionModel().selectFirst();
        choiceBox2.getSelectionModel().selectFirst();
        compareButton.setDefaultButton(true);
        Platform.runLater(() ->
                p1TextField.requestFocus()
        );

        BooleanBinding booleanBind = p1TextField.textProperty().isEmpty()
                .or(p2TextField.textProperty().isEmpty());

        compareButton.disableProperty().bind(booleanBind);
    }

    @FXML
    public void compare() {
        if (textFieldsCheck()) {
            if (playersCheck()) {
                try {
                    searchForPlayers();
                } catch (PlayerNotFoundException e) {
                    setErrorStatus(e);
                    setLabelText(EMPTY, getPlayerStatsLabelList());
                    if (e instanceof BothPlayersNotFoundException) {
                        return;
                    } else if (e instanceof FirstPlayerNotFoundException) {
                        this.player2 = userDataService.getUser2();
                        setPlayerStatsLabels("p2", player2);
                        return;
                    } else if (e instanceof SecondPlayerNotFoundException) {
                        this.player1 = userDataService.getUser1();
                        setPlayerStatsLabels("p1", player1);
                        return;
                    }
                    return;
                }

                setPlayer1(userDataService.getUser1());
                setPlayer2(userDataService.getUser2());
                setPlayerStatsLabels("p1", player1);
                setPlayerStatsLabels("p2", player2);
                setStatus("OK", COLOR_GREEN);
                colorAllLabels();
            } else {
                setStatus("Don't test me please :)", COLOR_RED);
            }
        } else {
            setStatus("Enter proper names!", COLOR_RED);
        }
    }

    @FXML
    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    private void colorLabelPair(String labelName) {
        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<Integer, Label> mapOfLabelPair = new HashMap<>();
        int i = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(StatsLabel.class)) {
                StatsLabel an = field.getDeclaredAnnotation(StatsLabel.class);
                if (labelName.equalsIgnoreCase(an.label())) {
                    Label label = null;
                    try {
                        label = (Label) field.get(this);
                        mapOfLabelPair.put(i++, label);
                    } catch (IllegalAccessException e) {
                        DialogUtils.errorDialog(e);
                    }
                }
            }
        }
        if (!mapOfLabelPair.isEmpty()) {
            setColor(mapOfLabelPair);
        }
    }

    private void setColor(Map<Integer, Label> map) {
        String st1 = map.get(0).getText().replaceAll("\\D+","");
        String st2 = map.get(1).getText().replaceAll("\\D+","");
        Long value1 = Long.valueOf(st1);
        Long value2 = Long.valueOf(st2);
        if (value1 > value2) {
            map.get(0).setStyle(COLOR_GREEN);
            map.get(1).setStyle(COLOR_RED);
        }
        else if(value1 < value2){
            map.get(0).setStyle(COLOR_RED);
            map.get(1).setStyle(COLOR_GREEN);
        }
        else
            for (Map.Entry<Integer, Label> entry : map.entrySet()) {
                entry.getValue().setStyle(COLOR_BLUE);
            }
    }

    private void searchForPlayers() {
        String sP1name = p1TextField.getText();
        String sP2name = p2TextField.getText();
        String sP1pl = choiceBox1.getValue().toLowerCase();
        String sP2pl = choiceBox2.getValue().toLowerCase();

        userDataService.checkBothUsers(sP1pl, sP1name, sP2pl, sP2name);
    }

    private boolean textFieldsCheck() {
        TextField tf1 = this.p1TextField;
        TextField tf2 = this.p2TextField;
        return checkIfTextFieldsAreFilled(tf1, tf2);

    }

    private boolean checkIfTextFieldsAreFilled(TextField... textFields) {
        for (TextField field : textFields) {
            if (isTextFieldEmpty(field)) {
                return false;
            }
        }
        return true;
    }

    private boolean isTextFieldEmpty(TextField tf) {
        if (tf.getText() == null || tf.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean playersCheck() {
        String cb1 = this.choiceBox1.getValue();
        String cb2 = this.choiceBox2.getValue();
        String tf1 = this.p1TextField.getText();
        String tf2 = this.p2TextField.getText();
        return checkIfSearchedPlayersAreNotSame(tf1, cb1, tf2, cb2);


    }

    private boolean checkIfSearchedPlayersAreNotSame(String player1, String platform1, String player2, String platform2) {
        String p1Name = player1.toLowerCase().trim();
        String p2Name = player2.toLowerCase().trim();

        if (p1Name.equals(p2Name)) {
            if (platform1.equals(platform2)) {
                return false;
            }
        }
        return true;
    }

    private void setErrorStatus(Throwable e) {
        statusLabel.setText(e.getMessage());
        statusLabel.setStyle(COLOR_RED);
    }

    private List<Label> getPlayerStatsLabelList() {
        List<Label> list = new ArrayList<>();
        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(StatsLabel.class)) {
                try {
                    Label tmp = (Label) field.get(this);
                    list.add(tmp);
                } catch (IllegalAccessException e) {
                    //e.printStackTrace();
                    DialogUtils.errorDialog(e);
                }
            }
            field.setAccessible(false);
        }
        return list;
    }

    private void setStatus(String status, String color) {
        this.statusLabel.setText(status);
        this.statusLabel.setStyle(color);
    }

    private void setLabelText(String e, List<Label> list) {
        for (Label label : list) {
            label.setText(e);
        }
    }

    private void colorAllLabels(){
        colorLabelPair("lifetimekd");
        colorLabelPair("lifetimewins");
        colorLabelPair("lifetimematches");
        colorLabelPair("lifetimewinpercentage");
        colorLabelPair("lifetimekills");
        colorLabelPair("solokd");
        colorLabelPair("solowins");
        colorLabelPair("solomatches");
        colorLabelPair("solowinpercentage");
        colorLabelPair("solokills");
        colorLabelPair("duokd");
        colorLabelPair("duowins");
        colorLabelPair("duomatches");
        colorLabelPair("duowinpercentage");
        colorLabelPair("duokills");
        colorLabelPair("squadskd");
        colorLabelPair("squadswins");
        colorLabelPair("squadsmatches");
        colorLabelPair("squadswinpercentage");
        colorLabelPair("squadskills");
    }
    public void setPlayerStatsLabels(final String playerNumber, UserData user) {
        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(StatsLabel.class)) {
                StatsLabel an = field.getDeclaredAnnotation(StatsLabel.class);
                if (playerNumber.equals(an.player())) {
                    Label label = null;
                    try {
                        label = (Label) field.get(this);
                    } catch (IllegalAccessException e) {
                        DialogUtils.errorDialog(e);
                    }
                    switch (an.label().toLowerCase()) {
                        case "namelabel":
                            label.setText(user.getEpicName());
                            break;
                        case "lifetimekd":
                            label.setText(user.getTotalKD());
                            break;
                        case "lifetimewins":
                            label.setText(user.getTotalWins());
                            break;
                        case "lifetimematches":
                            label.setText(user.getTotalMatchesPlayed());
                            break;
                        case "lifetimewinpercentage":
                            label.setText(user.getTotalWinPercentage());
                            break;
                        case "lifetimekills":
                            label.setText(user.getTotalKills());
                            break;
                        case "solokd":
                            label.setText(user.getSoloKD());
                            break;
                        case "solowins":
                            label.setText(user.getSoloWins());
                            break;
                        case "solomatches":
                            label.setText(user.getSoloGamesPlayed());
                            break;
                        case "solowinpercentage":
                            label.setText(user.getSoloWinPercentage());
                            break;
                        case "solokills":
                            label.setText(user.getSoloKills());
                            break;
                        case "duokd":
                            label.setText(user.getDuoKD());
                            break;
                        case "duowins":
                            label.setText(user.getDuoWins());
                            break;
                        case "duomatches":
                            label.setText(user.getDuoGamesPlayed());
                            break;
                        case "duowinpercentage":
                            label.setText(user.getDuoWinPercentage());
                            break;
                        case "duokills":
                            label.setText(user.getDuoKills());
                            break;
                        case "squadskd":
                            label.setText(user.getTeamKD());
                            break;
                        case "squadswins":
                            label.setText(user.getTeamWins());
                            break;
                        case "squadsmatches":
                            label.setText(user.getTeamGamesPlayed());
                            break;
                        case "squadswinpercentage":
                            label.setText(user.getTeamWinPercentage());
                            break;
                        case "squadskills":
                            label.setText(user.getTeamKills());
                            break;
                        default:
                    }
                }
            }
            field.setAccessible(false);
        }
    }

    public ChoiceBox<String> getChoiceBox1() {
        return choiceBox1;
    }


    public TextField getP1TextField() {
        return p1TextField;
    }

    public TextField getP2TextField() {
        return p2TextField;
    }

    protected void setPlayer1(UserData player1) {
        this.player1 = player1;
    }

    protected void setPlayer2(UserData player2) {
        this.player2 = player2;
    }
}
