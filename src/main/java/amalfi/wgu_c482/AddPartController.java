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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {
    public TextField partNameField;
    public TextField partStockField;
    public TextField partPriceField;
    public TextField partMaxField;
    public TextField partMinField;
    public Button partSave;
    public Button partCancel;
    public StackPane exceptCompNamePane;
    public Label exceptCompNameLabel;
    public StackPane exceptBetweenMinMaxPane;
    public Label exceptBetweenMinMaxLabel;
    public StackPane exceptMinMaxPane;
    public Label exceptMinMaxLabel;
    public StackPane exceptPartNamePane;
    public Label exceptPartNameLabel;
    public StackPane exceptStockIntPane;
    public Label exceptStockIntLabel;
    public StackPane exceptPriceDoublePane;
    public Label exceptPriceDoubleLabel;
    public StackPane exceptMaxStockIntPane;
    public Label exceptMaxStockIntLabel;
    public StackPane exceptMachineIntPane;
    public Label exceptMachineIntLabel;
    public StackPane exceptMinStockIntPane;
    public Label exceptMinStockIntLabel;
    public RadioButton inHouseRadio;
    public RadioButton outSourcedRadio;
    public TextField specTagField;
    public Label specTagLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onPartSave(ActionEvent actionEvent) throws IOException {
        backToMain(actionEvent);

    }

    public void onPartCancel(ActionEvent actionEvent) throws IOException {
        backToMain(actionEvent);
    }
    private void backToMain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/amalfi/wgu_c482/mainScreen.fxml")));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void onInHouseRadio() {
        specTagLabel.setText("Machine ID");
    }

    public void onOutsourcedRadio() {
        specTagLabel.setText("Company Name");
    }
}
