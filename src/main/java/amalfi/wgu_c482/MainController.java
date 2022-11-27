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
    private Part searchByPartId(int id, ObservableList<Part> list, ObservableList<Part> currentList) {
        for(Part part : list) {
            if (part.getId() == id && !currentList.contains(part)) {
                return part;
            }
        }
        return null;
    }
    private Product searchByProductId(int id, ObservableList<Product> list, ObservableList<Product> currentList) {
       for(Product product : list) {
            if (product.getId() == id && !currentList.contains(product)) {
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
        exceptRemoveAssocPartsLabel.setVisible(false);
        exceptRemoveAssocPartsPane.setManaged(false);
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

    private void populatePartsTable(TableView<Part> table, ObservableList<Part> list) {
        table.setItems(list);
    }
    private void populateProductTable(TableView<Product> table, ObservableList<Product> list) {
        table.setItems(list);

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
            ObservableList<Part> newParts = searchByPartName(newText, parts);
                try {
                    int id = Integer.parseInt((newText));
                    newParts.add(searchByPartId(id, parts, newParts));
                }
                catch (NumberFormatException e) {
                    //ignore exception
                }
            if (newParts.size() == 0) {
                exceptNoPartsLabel.setVisible(true);
                exceptNoPartsPane.setManaged(true);
            }
            populatePartsTable(partsTable, newParts);
        });

        searchProds.textProperty().addListener((obs, oldText, newText) -> {
            exceptNoProdLabel.setVisible(false);
            exceptNoProdPane.setManaged(false);
            ObservableList<Product> newProducts = searchByProductName(newText, products);
                try {
                    int id = Integer.parseInt((newText));
                    newProducts.add(searchByProductId(id, products, newProducts));
                }
                catch (NumberFormatException e) {
                    //ignore exception
                }
            if (newProducts.size() == 0) {
                exceptNoProdLabel.setVisible(true);
                exceptNoProdPane.setManaged(true);
            }
            populateProductTable(productsTable, newProducts);
        });

        Inventory.addTestData();
        populatePartsTable(partsTable, parts);
        populateProductTable(productsTable, products);

    }
}