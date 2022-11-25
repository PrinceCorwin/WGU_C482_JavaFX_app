package amalfi.wgu_c482;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {
    public RadioButton inHouseRadio;
    public RadioButton addOutSourcedRadio;
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

    public void onAddInHouseRadio(ActionEvent actionEvent) {
    }

    public void onAddOutsourcedRadio(ActionEvent actionEvent) {
    }

    public void onAddPartSave(ActionEvent actionEvent) {
    }

    public void onAddPartCancel(ActionEvent actionEvent) {
    }
}
