package amalfi.wgu_c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public TextField prodNameField;
    public TextField prodStockField;
    public TextField prodPriceField;
    public TextField prodMaxField;
    public TextField prodMinField;
    public Label exceptBetweenMinMax;
    public Label exceptMinMax;
    public Label exceptProdName;
    public Label exceptPriceDouble;
    public Label exceptMaxStockInt;
    public Label exceptStockInt;
    public TextField searchParts;
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
    public Button addProdSave;
    public Button addProdCancel;

    public void onAddToProd(ActionEvent actionEvent) throws IOException {
    }

    public void onRemoveAssocPart(ActionEvent actionEvent) throws IOException {
    }

    public void onAddProdSave(ActionEvent actionEvent) throws IOException {
        backToMain(actionEvent);
    }

    public void onAddProdCancel(ActionEvent actionEvent) throws IOException {
        backToMain(actionEvent);
    }
    private void backToMain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/mainScreen.fxml")));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 600);
        stage.setScene(scene);
        stage.show();
    }


}
