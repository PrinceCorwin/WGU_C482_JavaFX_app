package amalfi.wgu_c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public TextField prodNameField;
    public TextField prodStockField;
    public TextField prodPriceField;
    public TextField prodMaxField;
    public TextField prodMinField;
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
            int num = Integer.parseInt(str);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }


    public void onProdSave(ActionEvent actionEvent) throws IOException {
        boolean noErrors = true;
        int stock;
        int max;
        int min;
        double price;
        if (prodNameField.getText().isBlank()) {
            exceptProdNameLabel.setVisible(true);
            exceptProdNamePane.setManaged(true);
            noErrors = false;
        }

        if (!checkForInt(prodStockField.getText())) {
            exceptStockIntLabel.setVisible(true);
            exceptStockIntPane.setManaged(true);
            noErrors = false;
        }
        else {
            stock = Integer.parseInt(prodStockField.getText());
        }

        if (!checkForInt(prodMinField.getText())) {
            exceptMinStockIntLabel.setVisible(true);
            exceptMinStockIntPane.setManaged(true);
            noErrors = false;
        }
        else {
            min = Integer.parseInt(prodMinField.getText());
        }

        if (!checkForInt(prodMaxField.getText())) {
            exceptMaxStockIntLabel.setVisible(true);
            exceptMaxStockIntPane.setManaged(true);
            noErrors = false;

        }
        else {
            max = Integer.parseInt((prodMaxField.getText()));
        }

        if (!checkForInt(prodPriceField.getText())) {
            exceptPriceDoubleLabel.setVisible(true);
            exceptPriceDoublePane.setManaged(true);
            noErrors = false;
        }


        backToMain(actionEvent);

    }

    private void saveProd() {
    }


    public void onProdCancel(ActionEvent actionEvent) throws IOException {
        backToMain(actionEvent);

    }
}
