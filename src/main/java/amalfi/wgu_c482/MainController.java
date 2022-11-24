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

    public TableView partsTable;
    public TableColumn partIDCol;
    public TableColumn partNameCol;
    public TableColumn partStockCol;
    public TableColumn partPriceCol;
    public TableView productsTable;
    public TableColumn prodIDCol;
    public TableColumn prodNameCol;
    public TableColumn prodStockCol;
    public TableColumn prodPriceCol;
    public Button addPart;
    public Button modPart;
    public Button delPart;
    public Button addProd;
    public Button modProd;
    public Button delProd;
    public Button exit;

    private ObservableList<Part> parts = FXCollections.observableArrayList();
    private ObservableList<Product> products = FXCollections.observableArrayList();

    @FXML
    private TextField searchParts;
    @FXML
    private TextField searchProds;

    private ObservableList<Part> searchByPartName(String partialName, ObservableList list) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = list;
        for(Part part : allParts) {
            if (part.getName().contains(partialName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }
    private ObservableList<Product> searchByProductName(String partialName, ObservableList list) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = list;
        for(Product product : allProducts) {
            if (product.getName().contains(partialName)) {
                namedProducts.add(product);
            }

        }
        return namedProducts;
    }
    private Part searchByPartId(int id, ObservableList list) {
        ObservableList<Part> allParts = list;
        for(Part part : allParts) {
            if (part.getId() == id) {
                return part;
            }
        }
        return null;
    }
    private Product searchByProductId(int id, ObservableList list) {
        ObservableList<Product> allProducts = list;
        for(Product product : allProducts) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        parts.add(new Part(12,"Joe",34,1));
//        parts.add(new Part(33,"Steve",3,2));
//        parts.add(new Part(44,"Rudy",34,3));
//        parts.add(new Part(13,"Susan",3,4));
        products.add(new Product(23,"Jennifer",2,2, 1, 4));
        products.add(new Product(48,"Bob",3,10, 2, 60));
        products.add(new Product(5,"Robert",4,5, 2, 20));
        products.add(new Product(32,"Jose",1,1, 1, 40));

        partsTable.setItems(parts);
        productsTable.setItems(products);

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        prodIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        searchParts.textProperty().addListener((obs, oldText, newText) -> {
            ObservableList newParts = searchByPartName(newText, parts);
            if (newParts.size() == 0) {
                try {
                    int id = Integer.parseInt((newText));
                    newParts.add(searchByPartId(id, parts));
                }
                catch (NumberFormatException e) {
                    //ignore exception
                }
            }
            partsTable.setItems(newParts);
        });

        searchProds.textProperty().addListener((obs, oldText, newText) -> {
            ObservableList newProducts = searchByProductName(newText, products);
            if (newProducts.size() == 0) {
                try {
                    int id = Integer.parseInt((newText));
                    newProducts.add(searchByProductId(id, products));
                }
                catch (NumberFormatException e) {
                    //ignore exception
                }
            }
            productsTable.setItems(newProducts);
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