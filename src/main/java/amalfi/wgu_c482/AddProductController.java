package amalfi.wgu_c482;

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

public class AddProductController implements Initializable {


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

    public static void setModifiedProd(Product prod) {
        modifiedProd = prod;
    }

    @FXML
    private TextField searchParts;

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

    public void onRemoveAssocPart(){
        exceptNoPartsLabel.setVisible(false);
        try {
            Part deletedPart = partsTableAssoc.getSelectionModel().getSelectedItem();
            associatedParts.remove(deletedPart);
            partsTableAssoc.setItems(associatedParts);
        }
        catch (NullPointerException e) {
            exceptNoPartsLabel.setText("Part must be selected before removing");
            exceptNoPartsLabel.setVisible(true);
        }
    }

    private void backToMain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/mainScreen.fxml")));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 600);
        stage.setScene(scene);
        stage.show();
    }

    private boolean checkForInt(String str) {
        try {
           Integer.parseInt(str);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

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
                newProd = new Product(modifiedProd.getId(), name, price, stock, min, max);
                for (Part item : associatedParts) {
                    newProd.addAssociatePart(item);
                }
                int index = Inventory.getAllProducts().indexOf(modifiedProd);
                Inventory.updateProduct(index, newProd);
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

    public void onProdCancel(ActionEvent actionEvent) throws IOException {
        modifiedProd = null;
        backToMain(actionEvent);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partIdSearchCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameSearchCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockSearchCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceSearchCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partIdAssocCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameAssocCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockAssocCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceAssocCol.setCellValueFactory(new PropertyValueFactory<>("price"));

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
//            exceptNoPartsPane.setManaged(false);
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
//                exceptNoPartsPane.setManaged(true);
            }
            partsTableSearch.setItems(newParts);
        });
        partsTableSearch.setItems(parts);
        partsTableAssoc.setItems(associatedParts);
    }

}
