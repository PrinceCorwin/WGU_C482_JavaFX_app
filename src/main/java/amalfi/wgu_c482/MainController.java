package amalfi.wgu_c482;

import javafx.application.Platform;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/** This is my first javadoc comment */
public class MainController implements Initializable {

    public StackPane exceptNoPartsPane;
    public Label exceptNoPartsLabel;
    public StackPane exceptNoProdPane;
    public Label exceptNoProdLabel;
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
    public StackPane exceptRemoveAssocPartsPane;
    public Label exceptRemoveAssocPartsLabel;
    public StackPane exceptSelectProdPane;
    public Label exceptSelectProdLabel;
    public StackPane exceptSelectPartsPane;
    public Label exceptSelectPartsLabel;
    private ObservableList<Part> parts = Inventory.getAllParts();
    private ObservableList<Product> products = Inventory.getAllProducts();
    @FXML
    private TextField searchParts;
    @FXML
    private TextField searchProds;

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
        exceptSelectPartsLabel.setVisible(false);
        exceptSelectPartsPane.setManaged(false);
        try {
            Part deletedPart = partsTable.getSelectionModel().getSelectedItem();
            if (Inventory.deletePart(deletedPart)) {
                parts = Inventory.getAllParts();
                partsTable.setItems(parts);
            }
        }
        catch (NullPointerException e) {
            exceptSelectPartsLabel.setVisible(true);
            exceptSelectPartsPane.setManaged(true);
        }
    }
    public void onAddProd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/addProduct.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void onModProd(ActionEvent actionEvent) throws IOException {
        Product prod = productsTable.getSelectionModel().getSelectedItem();
        AddProductController.setModifiedProd(prod);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/addProduct.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();
    }
    public void onDelProd() {
        exceptSelectProdLabel.setVisible(false);
        exceptSelectProdPane.setManaged(false);
        exceptRemoveAssocPartsLabel.setVisible(false);
        exceptRemoveAssocPartsPane.setManaged(false);
        try {
            Product deletedProduct = productsTable.getSelectionModel().getSelectedItem();
            if (Inventory.deleteProduct(deletedProduct)) {
                products = Inventory.getAllProducts();
                productsTable.setItems(products);
            }
            else {
                exceptRemoveAssocPartsLabel.setVisible(true);
                exceptRemoveAssocPartsPane.setManaged(true);
            }
        }
        catch (NullPointerException e) {
            exceptSelectProdLabel.setVisible(true);
            exceptSelectProdPane.setManaged(true);
        }
    }
    public void onExit() {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        prodIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        searchParts.textProperty().addListener((obs, oldText, newText) -> {
            exceptNoPartsLabel.setVisible(false);
            exceptNoPartsPane.setManaged(false);
            ObservableList<Part> newParts = Inventory.lookupPart(newText);
                try {
                    int id = Integer.parseInt((newText));
                    Part part = Inventory.lookupPart(id);
                    if (!newParts.contains(part) && part != null) {
                        newParts.add(part);
                    }
                }
                catch (NumberFormatException e) {
                    //ignore exception
                }
            if (newParts.size() == 0) {
                exceptNoPartsLabel.setVisible(true);
                exceptNoPartsPane.setManaged(true);
            }
            partsTable.setItems(newParts);
        });

        searchProds.textProperty().addListener((obs, oldText, newText) -> {
            exceptNoProdLabel.setVisible(false);
            exceptNoProdPane.setManaged(false);
            ObservableList<Product> newProducts = Inventory.lookupProduct(newText);
                try {
                    int id = Integer.parseInt((newText));
                    Product prod = Inventory.lookupProduct(id);
                    if (!newProducts.contains(prod) && prod != null) {
                        newProducts.add(prod);
                    }
                }
                catch (NumberFormatException e) {
                    //ignore exception
                }
            if (newProducts.size() == 0) {
                exceptNoProdLabel.setVisible(true);
                exceptNoProdPane.setManaged(true);
            }
            productsTable.setItems(newProducts);
        });
        if (!Inventory.isTestDataAdded()) {
            Inventory.addTestData();
        }
        partsTable.setItems(parts);
        productsTable.setItems(products);
    }
}