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
//    public TextField searchParts;
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
    private ObservableList<Part> parts = Inventory.getAllParts();


    @FXML
    private TextField searchParts;
    private ObservableList<Part> searchByPartName(String partialName, ObservableList<Part> list) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        for(Part part : list) {
            if (part.getName().contains(partialName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }
    private Part searchByPartId(int id, ObservableList<Part> list, ObservableList<Part> currentList) {
        for(Part part : list) {
            if (part.getId() == id && !currentList.contains(part)) {
                return part;
            }
        }
        return null;
    }
    public void onAddToProd(ActionEvent actionEvent) throws IOException {
    }

    public void onRemoveAssocPart(ActionEvent actionEvent) throws IOException {
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
            Inventory.addProduct(new Product(UniqueID.getUniqueProdID(), name, price, stock, min, max));

            backToMain(actionEvent);

        }

    }

    public void onProdCancel(ActionEvent actionEvent) throws IOException {
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

        searchParts.textProperty().addListener((obs, oldText, newText) -> {
            exceptNoPartsLabel.setVisible(false);
//            exceptNoPartsPane.setManaged(false);
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
//                exceptNoPartsPane.setManaged(true);
            }
            partsTableSearch.setItems(newParts);
        });
        partsTableSearch.setItems(Inventory.getAllParts());
    }

}
