package amalfi.wgu_c482;

import javafx.collections.FXCollections;
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
 * Controls the behavior of the addProduct.fxml scene. Provides functionality to either
 * add new product or modify existing product, based on user input to the displayed form.
 * @author Steve Corwin Amalfitano
 */
public class AddProductController {
    public StackPane exceptStockIntPane;
    public Label exceptStockIntLabel;
    public StackPane exceptBetweenMinMaxPane;
    public Label exceptBetweenMinMaxLabel;
    public StackPane exceptMinMaxPane;
    public Label exceptMinMaxLabel;
    public StackPane exceptProdNamePane;
    public Label exceptProdNameLabel;
    public StackPane exceptPriceDoublePane;
    public Label exceptPriceDoubleLabel;
    public StackPane exceptMaxStockIntPane;
    public Label exceptMaxStockIntLabel;
    public StackPane exceptMinStockIntPane;
    public Label exceptMinStockIntLabel;
    public Button prodSave;
    public Button prodCancel;
    public TextField prodNameField;
    public TextField prodStockField;
    public TextField prodPriceField;
    public TextField prodMaxField;
    public TextField prodMinField;
    public TableView<Part> partsTableSearch;
    public TableView<Part> partsTableAssoc;
    public TableColumn<Part, Integer> partIdSearchCol;
    public TableColumn<Part, String> partNameSearchCol;
    public TableColumn<Part, Integer> partStockSearchCol;
    public TableColumn<Part, Double> partPriceSearchCol;
    public Button AddToProd;
    public TableColumn<Part, Integer> partIdAssocCol;
    public TableColumn<Part, String> partNameAssocCol;
    public TableColumn<Part, Integer> partStockAssocCol;
    public TableColumn<Part, Double> partPriceAssocCol;
    public Button removeAssocPart;
    public StackPane exceptNoPartsPane;
    public Label exceptNoPartsLabel;
    private final ObservableList<Part> parts = Inventory.getAllParts();
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private static Product modifiedProd = null;
    public Label titleLabel;

    /**
     * Sets modifiedProd variable to selected product to be modified from the mainScreen controller.
     * @param prod the product to set
     */
    public static void setModifiedProd(Product prod) {
        modifiedProd = prod;
    }

    @FXML
    private TextField searchParts;

    /**
     * Associates selected part to the current product and adds to the associated parts table
     */
    public void associatePart() {
        exceptNoPartsLabel.setVisible(false);
        try {
            Part addedPart = partsTableSearch.getSelectionModel().getSelectedItem();
            if (!associatedParts.contains(addedPart) && addedPart != null) {
                associatedParts.add(addedPart);
                partsTableAssoc.setItems(associatedParts);
            }
            else {
                exceptNoPartsLabel.setText("No part selected or part already associated");
                exceptNoPartsLabel.setVisible(true);
            }
        }
        catch (NullPointerException e) {
            exceptNoPartsLabel.setText("Part must be selected before adding");
            exceptNoPartsLabel.setVisible(true);
        }
    }

    /**
     * Removes selected part from being associated with the current product and removes from the
     * associated parts table
     */
    public void onRemoveAssocPart() {
        exceptNoPartsLabel.setVisible(false);
        boolean nullPointer = true;
        Part deletedPart = partsTableAssoc.getSelectionModel().getSelectedItem();
        try {
            String thisName = deletedPart.getName();
            nullPointer = false;
        } catch (NullPointerException e) {
            exceptNoPartsLabel.setText("Part must be selected before removing");
            exceptNoPartsLabel.setVisible(true);
        }
        if (!nullPointer) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Remove");
            alert.setHeaderText("Remove " + deletedPart.getName());
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                associatedParts.remove(deletedPart);
                partsTableAssoc.setItems(associatedParts);
            }
        }
    }


    /**
     * replaces current scene with the mainScreen.fxml scene
     * @param e the action event
     * @throws IOException Catches any exceptions thrown during data input / output
     */
    private void backToMain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/mainScreen.fxml")));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Checks a string to conversion to an integer
     * @param str the string to check
     * @return true if string is converted, false if not
     */
    private boolean checkForInt(String str) {
        try {
           Integer.parseInt(str);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * hide all error messages
     */
    private void hideErrors() {
        exceptStockIntLabel.setVisible(false);
        exceptStockIntPane.setManaged(false);
        exceptMinStockIntLabel.setVisible(false);
        exceptMinStockIntPane.setManaged(false);
        exceptMaxStockIntLabel.setVisible(false);
        exceptMaxStockIntPane.setManaged(false);
        exceptBetweenMinMaxLabel.setVisible(false);
        exceptBetweenMinMaxPane.setManaged(false);
        exceptPriceDoublePane.setManaged(false);
        exceptPriceDoubleLabel.setVisible(false);
        exceptProdNameLabel.setVisible(false);
        exceptProdNamePane.setManaged(false);
        exceptMinMaxPane.setManaged(false);
        exceptMinMaxLabel.setVisible(false);
    }

    /**
     * Checks all fields for valid data and either saves a new product to inventory
     * or updates a modified part to inventory
     * @param actionEvent the action event
     * @throws IOException Catches any exceptions thrown during data input / outputCatches any exceptions thrown during data input / output
     */
    public void onProdSave(ActionEvent actionEvent) throws IOException {
        hideErrors();
        String name = "";
        boolean noErrors = true;
        int stock = 0;
        int max = 0;
        int min = 0;
        double price = 0.00;

        if (checkForInt(prodStockField.getText())) {
            stock = Integer.parseInt(prodStockField.getText());
        }
        else {
            exceptStockIntLabel.setVisible(true);
            exceptStockIntPane.setManaged(true);
            noErrors = false;
        }

        if (checkForInt(prodMinField.getText())) {
            min = Integer.parseInt(prodMinField.getText());
        }
        else {
            exceptMinStockIntLabel.setVisible(true);
            exceptMinStockIntPane.setManaged(true);
            noErrors = false;
        }

        if (checkForInt(prodMaxField.getText())) {
            max = Integer.parseInt((prodMaxField.getText()));
        }
        else {
            exceptMaxStockIntLabel.setVisible(true);
            exceptMaxStockIntPane.setManaged(true);
            noErrors = false;
        }

        if (noErrors) {
            if (stock > max || stock < min) {
                exceptBetweenMinMaxLabel.setVisible(true);
                exceptBetweenMinMaxPane.setManaged(true);
                noErrors = false;
            }
            if (min >= max) {
                exceptMinMaxPane.setManaged(true);
                exceptMinMaxLabel.setVisible(true);
                noErrors = false;
            }
        }
        try {
            price = Double.parseDouble(prodPriceField.getText());
        }
        catch(NumberFormatException e) {
            exceptPriceDoublePane.setManaged(true);
            exceptPriceDoubleLabel.setVisible(true);
            noErrors = false;
        }

        if (prodNameField.getText().isBlank()) {
            exceptProdNameLabel.setVisible(true);
            exceptProdNamePane.setManaged(true);
            noErrors = false;
        }
        else {
            name = prodNameField.getText();
        }
        if (noErrors) {
            Product newProd;
            if (modifiedProd != null) {
                modifiedProd.setName(name);
                modifiedProd.setPrice(price);
                modifiedProd.setMin(min);
                modifiedProd.setMax(max);
                modifiedProd.setStock(stock);
                ObservableList<Part> currentParts = modifiedProd.getAllAssociatedParts();
                if (currentParts.size() != 0) {
                    for (int i = 0; i < currentParts.size(); i++) {
                        Part part = currentParts.get(i);
                        if (!associatedParts.contains(part)) {
                            modifiedProd.deleteAssociatePart(part);
                        }
                    }
                }
                if (associatedParts.size() != 0) {
                    for (int i = 0; i < associatedParts.size(); i++) {
                        Part part = associatedParts.get(i);
                        if(!currentParts.contains(part)) {
                            modifiedProd.addAssociatePart(part);
                        }
                    }
                }
                int index = Inventory.getAllProducts().indexOf(modifiedProd);
                Inventory.updateProduct(index, modifiedProd);
            }
            else {
                newProd = new Product(UniqueID.getUniqueProdID(), name, price, stock, min, max);
                Inventory.addProduct(newProd);
                for (Part item : associatedParts) {
                    newProd.addAssociatePart(item);
                }
            }
            modifiedProd = null;
            backToMain(actionEvent);
        }
    }

    /**
     * Upon button click, sets modifiedProd back to null and replaces current scene with mainScreen scene
     * @param actionEvent the action event
     * @throws IOException Catches any exceptions thrown during data input / output
     */
    public void onProdCancel(ActionEvent actionEvent) throws IOException {
        modifiedProd = null;
        backToMain(actionEvent);

    }

    /**
     * Initializes the form with existing data if product has been selected and modify button clicked on mainScreen.
     * Also initializes the search real-time search functionality of the search field
     */
    public void initialize() {
        partIdSearchCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameSearchCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockSearchCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceSearchCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partIdAssocCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameAssocCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockAssocCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceAssocCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partIdSearchCol.prefWidthProperty().bind(partsTableSearch.widthProperty().divide(4));
        partNameSearchCol.prefWidthProperty().bind(partsTableSearch.widthProperty().divide(4));
        partStockSearchCol.prefWidthProperty().bind(partsTableSearch.widthProperty().divide(4));
        partPriceSearchCol.prefWidthProperty().bind(partsTableSearch.widthProperty().divide(4));

        partIdAssocCol.prefWidthProperty().bind(partsTableAssoc.widthProperty().divide(4));
        partNameAssocCol.prefWidthProperty().bind(partsTableAssoc.widthProperty().divide(4));
        partStockAssocCol.prefWidthProperty().bind(partsTableAssoc.widthProperty().divide(4));
        partPriceAssocCol.prefWidthProperty().bind(partsTableAssoc.widthProperty().divide(4));

        if(modifiedProd != null) {
            titleLabel.setText("Modify Product");
            prodNameField.setText(modifiedProd.getName());
            prodStockField.setText(String.valueOf(modifiedProd.getStock()));
            prodMinField.setText(String.valueOf(modifiedProd.getMin()));
            prodMaxField.setText(String.valueOf(modifiedProd.getMax()));
            prodPriceField.setText(String.valueOf(modifiedProd.getPrice()));

            ObservableList<Part> modAssocParts = modifiedProd.getAllAssociatedParts();
            associatedParts.addAll(modAssocParts);
        }

        searchParts.textProperty().addListener((obs, oldText, newText) -> {
            exceptNoPartsLabel.setText("No parts found. Change search input");
            exceptNoPartsLabel.setVisible(false);
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
            }
            partsTableSearch.setItems(newParts);
        });
        partsTableSearch.setItems(parts);
        partsTableAssoc.setItems(associatedParts);
    }

}
