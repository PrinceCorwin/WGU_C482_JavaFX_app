package amalfi.wgu_c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/** This is my first javadoc comment */
public class MainController implements Initializable {

    public Label TheLabel;
    public TableView leftSide;
    public TableColumn leftJerseyCol;
    public TableColumn leftNameCol;
    public TableColumn leftPointsCol;
    public TableColumn leftTeamCol;
    public TableView rightSide;
    public TableColumn rightJerseyCol;
    public TableColumn rightNameCol;
    public TableColumn rightPointsCol;
    public TableColumn rightTeamCol;
    public Button goRight;
    public Button goLeft;

    private ObservableList<Player> leftPlayers = FXCollections.observableArrayList();
    private ObservableList<Player> rightPlayers = FXCollections.observableArrayList();

    public void onGoRight(ActionEvent actionEvent) {
        Player SP = (Player)leftSide.getSelectionModel().getSelectedItem();
        if (SP == null) {
            return;
        } else {
            leftPlayers.remove(SP);
            rightPlayers.add(SP);
        }
    }

    public void onGoLeft(ActionEvent actionEvent) {
        Player SP = (Player)rightSide.getSelectionModel().getSelectedItem();
        if (SP == null) {
            return;
        } else {
            rightPlayers.remove(SP);
            leftPlayers.add(SP);
        }
    }
    @FXML
    private TextField searchParts;
    @FXML
    private TextField searchProds;

    private ObservableList<Player> searchByName(String partialName, ObservableList list) {
        ObservableList<Player> namedPlayers = FXCollections.observableArrayList();
        ObservableList<Player> allPlayers = list;
        for(Player player : allPlayers) {
            if (player.getName().contains(partialName)) {
                namedPlayers.add(player);
            }
        }
        return namedPlayers;
    }
    private Player searchByJersey(int jersey, ObservableList list) {
        ObservableList<Player> allPlayers = list;
        for(Player player : allPlayers) {
            if (player.getJersey() == jersey) {
                return player;
            }
        }
        return null;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leftPlayers.add(new Player(12,"Joe",34,"team1"));
        leftPlayers.add(new Player(33,"Steve",3,"team2"));
        leftPlayers.add(new Player(44,"Rudy",34,"team1"));
        leftPlayers.add(new Player(13,"Susan",3,"team2"));
        rightPlayers.add(new Player(23,"Jennifer",34,"team3"));
        rightPlayers.add(new Player(48,"Bob",3,"team2"));
        rightPlayers.add(new Player(5,"Robert",34,"team1"));
        rightPlayers.add(new Player(32,"Jose",3,"team2"));

        leftSide.setItems(leftPlayers);
        rightSide.setItems(rightPlayers);

        leftJerseyCol.setCellValueFactory(new PropertyValueFactory<>("jersey"));
        leftNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        leftTeamCol.setCellValueFactory(new PropertyValueFactory<>("team"));
        leftPointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        rightJerseyCol.setCellValueFactory(new PropertyValueFactory<>("jersey"));
        rightNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        rightTeamCol.setCellValueFactory(new PropertyValueFactory<>("team"));
        rightPointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));

        searchParts.textProperty().addListener((obs, oldText, newText) -> {
            ObservableList newPlayers = searchByName(newText, leftPlayers);

            if (newPlayers.size() == 0) {
                try {
                    int jersey = Integer.parseInt((newText));
                    newPlayers.add(searchByJersey(jersey, leftPlayers));
                }
                catch (NumberFormatException e) {
                    //ignore exception
                }
            }
            leftSide.setItems(newPlayers);
        });

        searchProds.textProperty().addListener((obs, oldText, newText) -> {
            ObservableList newPlayers = searchByName(newText, rightPlayers);
            if (newPlayers.size() == 0) {
                try {
                    int jersey = Integer.parseInt((newText));
                    newPlayers.add(searchByJersey(jersey, rightPlayers));
                }
                catch (NumberFormatException e) {
                    //ignore exception
                }
            }
            rightSide.setItems(newPlayers);
        });

    }

    public void onAddPart(ActionEvent actionEvent) {
    }

    public void onModPart(ActionEvent actionEvent) {
    }

    public void onDelPart(ActionEvent actionEvent) {
    }

    public void onExit(ActionEvent actionEvent) {
    }

    public void onAddProd(ActionEvent actionEvent) {
    }

    public void onModProd(ActionEvent actionEvent) {
    }

    public void onDelProd(ActionEvent actionEvent) {
    }
}