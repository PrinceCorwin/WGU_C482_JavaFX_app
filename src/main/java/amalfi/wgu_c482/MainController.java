package amalfi.wgu_c482;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Inventory.addPart(new InHouse(12,"LED Strip",6,3, 2, 40, 34));
//        Inventory.addPart(new InHouse(33,"Switch",8,2, 2, 45, 2222));
//        Inventory.addPart(new Outsourced(44,"40 watt bulb",3,30, 10, 60, "ABC"));
//        Inventory.addPart(new Outsourced(13,"20 watt bulb",2,4, 3, 44, "XYZ"));
//        Inventory.addProduct(new Product(23,"Chandelier",200,2, 1, 4));
//        Inventory.addProduct(new Product(48,"Garage Light (LED)",30,10, 2, 60));
//        Inventory.addProduct(new Product(5,"Ceiling Light (LED)",85,5, 2, 20));
//        Inventory.addProduct(new Product(32,"Work Light",15,1, 1, 40));
        Inventory.addTestData();
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
            ObservableList<Part> newParts = searchByPartName(newText, parts);
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
            ObservableList<Product> newProducts = searchByProductName(newText, products);
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

    public TableView<Part> partsTable;
    public TableColumn<Part, Integer> partIDCol;
    public TableColumn<Part, String> partNameCol;
    public TableColumn<Part, Integer> partStockCol;
    public TableColumn<Part, Double> partPriceCol;
    public TableView<Product> productsTable;
    public TableColumn<Part, Integer> prodIDCol;
    public TableColumn<Part, String> prodNameCol;
    public TableColumn<Part, Integer> prodStockCol;
    public TableColumn<Part, Double> prodPriceCol;
    public Button addPart;
    public Button modPart;
    public Button delPart;
    public Button addProd;
    public Button modProd;
    public Button delProd;
    public Button exit;

    private ObservableList<Part> parts = Inventory.getAllParts();
    private ObservableList<Product> products = Inventory.getAllProducts();

    @FXML
    private TextField searchParts;
    @FXML
    private TextField searchProds;

    private ObservableList<Part> searchByPartName(String partialName, ObservableList<Part> list) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        for(Part part : list) {
            if (part.getName().contains(partialName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }
    private ObservableList<Product> searchByProductName(String partialName, ObservableList<Product> list) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        for(Product product : list) {
            if (product.getName().contains(partialName)) {
                namedProducts.add(product);
            }

        }
        return namedProducts;
    }
    private Part searchByPartId(int id, ObservableList<Part> list) {
        for(Part part : list) {
            if (part.getId() == id) {
                return part;
            }
        }
        return null;
    }
    private Product searchByProductId(int id, ObservableList<Product> list) {
       for(Product product : list) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void onAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/addPart.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void onModPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/modPart.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void onDelPart() {
        Part deletedPart = partsTable.getSelectionModel().getSelectedItem();
        if (Inventory.deletePart(deletedPart)) {
            parts = Inventory.getAllParts();
            partsTable.setItems(parts);
        }
    }

    public void onDelProd() {
        Product deletedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (Inventory.deleteProduct(deletedProduct)) {
            products = Inventory.getAllProducts();
            productsTable.setItems(products);
        }
        else {
//            turn on exception about not deleting products with associated parts
        }
    }

    public void onExit() {
        Platform.exit();
    }

    public void onAddProd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/addProduct.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void onModProd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/modProduct.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();
    }






}