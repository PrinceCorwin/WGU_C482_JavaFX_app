package amalfi.wgu_c482;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * Class to control the mainScreen scene behavior. Displays the parts and products tables, as well as
 * search fields for both. Controls behavior of add, modify and exit buttons.
 * @author Steve Corwin Amalfitano
 */
public class MainController {

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

    /**
     * Upon button click, scene is replaced by the addPart.fxml scene for a new part to be added
     * @param actionEvent the action event
     * @throws IOException Catches any exceptions thrown during data input / output
     */
    public void onAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/addPart.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 700);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Upon button click, scene is replaced by the addPart.fxml scene and the selected part is stored in the
     * addPartController to be modified
     * @param actionEvent the action event
     * @throws IOException Catches any exceptions thrown during data input / output
     */
    public void onModPart(ActionEvent actionEvent) throws IOException {
        exceptSelectPartsLabel.setVisible(false);
        exceptSelectPartsPane.setManaged(false);
        boolean nullPointer = true;
        Part modPart = partsTable.getSelectionModel().getSelectedItem();
        try {
            if (modPart.getName() != null) {
                nullPointer = false;
            }
//            String thisName = modPart.getName();
//            nullPointer = false;
        }
        catch (NullPointerException e) {
            exceptSelectPartsLabel.setText("Select a part to modify");
            exceptSelectPartsLabel.setVisible(true);
            exceptSelectPartsPane.setManaged(true);
        }

        if (!nullPointer) {
            Part part = partsTable.getSelectionModel().getSelectedItem();
            AddPartController.setModifiedPart(part);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/addPart.fxml")));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 700);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Deletes a selected part from Inventory and the parts table after verifying intent to delete from user
     */
    public void onDelPart() {
        exceptSelectPartsLabel.setVisible(false);
        exceptSelectPartsPane.setManaged(false);
        boolean nullPointer = true;
        Part deletedPart = partsTable.getSelectionModel().getSelectedItem();
        try {
                if (deletedPart.getName() != null) {
                    nullPointer = false;
                }
        }
        catch (NullPointerException e) {
            exceptSelectPartsLabel.setVisible(true);
            exceptSelectPartsPane.setManaged(true);
        }

//        Optional<ButtonType> result = alert.showAndWait();
        if (!nullPointer) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Delete " + deletedPart.getName());
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                if (Inventory.deletePart(deletedPart)) {
                    parts = Inventory.getAllParts();
                    partsTable.setItems(parts);
                }
            }
        }
    }

    /**
     * Upon button click, scene is replaced by the addProduct.fxml scene for a new Product to be added
     * @param actionEvent the action event
     * @throws IOException Catches any exceptions thrown during data input / output
     */
    public void onAddProd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/addProduct.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 500);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Upon button click, scene is replaced by the addProduct.fxml scene and the selected product is stored in the
     * addProductController to be modified
     * @param actionEvent the action event
     * @throws IOException Catches any exceptions thrown during data input / output
     */
    public void onModProd(ActionEvent actionEvent) throws IOException {
        exceptSelectProdLabel.setVisible(false);
        exceptSelectProdPane.setManaged(false);
        boolean nullPointer = true;
        Product modProd = productsTable.getSelectionModel().getSelectedItem();
        try {
            if (modProd.getName() != null) {
                nullPointer = false;
            }
        }
        catch (NullPointerException e) {
            exceptSelectProdLabel.setText("Select a product to modify");
            exceptSelectProdLabel.setVisible(true);
            exceptSelectProdPane.setManaged(true);
        }
        if (!nullPointer) {
            AddProductController.setModifiedProd(modProd);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/addProduct.fxml")));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 500);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Deletes a selected product from inventory and the products table after verifying user intent to delete with alert box
     */
    public void onDelProd() {
        exceptSelectProdLabel.setText("Select a product before clicking DELETE");
        exceptSelectProdLabel.setVisible(false);
        exceptSelectProdPane.setManaged(false);
        exceptRemoveAssocPartsLabel.setVisible(false);
        exceptRemoveAssocPartsPane.setManaged(false);
        boolean nullPointer = true;
        Product deletedProduct = productsTable.getSelectionModel().getSelectedItem();
        try {
            if (deletedProduct.getName() != null) {
                nullPointer = false;
            }
        }
        catch (NullPointerException e) {
            exceptSelectProdLabel.setVisible(true);
            exceptSelectProdPane.setManaged(true);
        }
        if (!nullPointer) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Delete " + deletedProduct.getName());
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                if (Inventory.deleteProduct(deletedProduct)) {
                    products = Inventory.getAllProducts();
                    productsTable.setItems(products);
                }
                else {
                    exceptRemoveAssocPartsLabel.setVisible(true);
                    exceptRemoveAssocPartsPane.setManaged(true);
                }
            }
        }
        else {
            exceptSelectProdLabel.setVisible(true);
            exceptSelectProdPane.setManaged(true);
        }
    }


    /**
     * Exits the program
     */
    public void onExit() {
        Platform.exit();
    }

    /**
     * Initializes the parts and products tables by adding testdata.
     * Real-time search functionality of the parts and products search fields are also initialized.
     */
    public void initialize() {

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