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
    public TableView partsTable;
    public TableColumn partID;
    public TableColumn partName;
    public TableColumn partInvent;
    public TableColumn partPrice;
    public TableView productsTable;
    public TableColumn prodID;
    public TableColumn prodName;
    public TableColumn prodInvent;
    public TableColumn prodPrice;
    public Button addPart;
    public Button modPart;
    public Button delPart;
    public Button addProd;
    public Button modProd;
    public Button delProd;
    public Button exit;

    private ObservableList<Player> parts = FXCollections.observableArrayList();
    private ObservableList<Player> products = FXCollections.observableArrayList();

    @FXML
    private TextField searchParts;
    @FXML
    private TextField searchProds;

    private ObservableList<Part> searchByName(String partialName, ObservableList list) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = list;
        for(Part part : allParts) {
            if (part.getName().contains(partialName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }
    private Part searchByJersey(int jersey, ObservableList list) {
        ObservableList<Part> allParts = list;
        for(Part part : allParts) {
            if (part.getId() == jersey) {
                return part;
            }
        }
        return null;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.add(new Part(12,"Joe",34,"team1"));
        partsTable.add(new Part(33,"Steve",3,"team2"));
        partsTable.add(new Part(44,"Rudy",34,"team1"));
        partsTable.add(new Part(13,"Susan",3,"team2"));
        productsTable.add(new Part(23,"Jennifer",34,"team3"));
        productsTable.add(new Part(48,"Bob",3,"team2"));
        productsTable.add(new Part(5,"Robert",34,"team1"));
        productsTable.add(new Part(32,"Jose",3,"team2"));

        parts.setItems(partsTable);
        products.setItems(productsTable);

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
            products.setItems(newPlayers);
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