package amalfi.wgu_c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ModPartController implements Initializable {
    public RadioButton modInHouseRadio;
    public RadioButton modOutSourcedRadio;
    public Label machineIdLabel;
    public TextField partNameField;
    public TextField partStockField;
    public TextField partPriceField;
    public TextField partMaxField;
    public TextField machineField;
    public TextField partMinField;
    public Label companyNameLabel;
    public TextField companyField;
    public Button AddPartSave;
    public Button addPartCancel;
    public Label exceptCompName;
    public Label exceptBetweenMinMax;
    public Label exceptMinMax;
    public Label exceptPartName;
    public Label exceptStockInt;
    public Label exceptPriceDouble;
    public Label exceptMaxStockInt;
    public Label exceptMachineInt;
    public Label exceptMinStockInt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onModInHouseRadio(ActionEvent actionEvent) throws IOException {
    }

    public void onModOutsourcedRadio(ActionEvent actionEvent) throws IOException {
    }

    public void onModPartSave(ActionEvent actionEvent) throws IOException {
        backToMain(actionEvent);

    }

    public void onModPartCancel(ActionEvent actionEvent) throws IOException {
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
