package amalfi.wgu_c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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
import java.util.Objects;

/** Controls the behavior of the mainScreen.fxml scene. Provides functionality to either add
 * new part or modify existing part, based on user input into the displayed form.
 * @author Steve Corwin Amalfitano
 */
public class AddPartController {
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
    private static Part modifiedPart = null;

    public Label titleLabel;

    /**
     * sets the modifiedPart variable to a selected part from the mainScreen controller
     * @param part the part to set
     */
    public static void setModifiedPart(Part part) {
        modifiedPart = part;
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
        exceptMinMaxPane.setManaged(false);
        exceptMinMaxLabel.setVisible(false);
        exceptCompNameLabel.setVisible(false);
        exceptCompNamePane.setManaged(false);
        exceptMachineIntLabel.setVisible(false);
        exceptMachineIntPane.setManaged(false);
        exceptPartNameLabel.setVisible(false);
        exceptPartNamePane.setManaged(false);
    }

    /**
     * Changes the specTagLabel to "Machine ID" if InHouse radio btn is selected
     */
    public void onInHouseRadio() {
        specTagLabel.setText("Machine ID");
    }

    /**
     * Changes the specTagLabel to "Company Name" if Outsourced radio btn is selected
     */
    public void onOutsourcedRadio() {
        specTagLabel.setText("Company Name");
    }

    /**
     * After validating user input, new part is added to inventory or
     * modified part is updated to inventory
     * @param actionEvent the action event
     * @throws IOException Catches any exceptions thrown during data input / output
     */
    public void onPartSave(ActionEvent actionEvent) throws IOException {
        hideErrors();
        String name = "";
        boolean noErrors = true;
        int stock = 0;
        int max = 0;
        int min = 0;
        double price = 0.00;
        int machineId = 0;
        String compName = "";

        if (checkForInt(partStockField.getText())) {
            stock = Integer.parseInt(partStockField.getText());
        }
        else {
            exceptStockIntLabel.setVisible(true);
            exceptStockIntPane.setManaged(true);
            noErrors = false;
        }
        if (checkForInt(partMinField.getText())) {
            min = Integer.parseInt(partMinField.getText());
        }
        else {
            exceptMinStockIntLabel.setVisible(true);
            exceptMinStockIntPane.setManaged(true);
            noErrors = false;
        }
        if (checkForInt(partMaxField.getText())) {
            max = Integer.parseInt((partMaxField.getText()));
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
        if (inHouseRadio.isSelected()) {
            if (checkForInt(specTagField.getText())) {
                machineId = Integer.parseInt((specTagField.getText()));
            }
            else {
                exceptMachineIntLabel.setVisible(true);
                exceptMachineIntPane.setManaged(true);
                noErrors = false;
            }
        }

        if (outSourcedRadio.isSelected()) {
            if (specTagField.getText().isBlank()) {
                exceptCompNameLabel.setVisible(true);
                exceptCompNamePane.setManaged(true);
                noErrors = false;
            }
            else {
                compName = specTagField.getText();
            }
        }

        try {
            price = Double.parseDouble(partPriceField.getText());
        }
        catch(NumberFormatException e) {
            exceptPriceDoublePane.setManaged(true);
            exceptPriceDoubleLabel.setVisible(true);
            noErrors = false;
        }
        if (partNameField.getText().isBlank()) {
            exceptPartNameLabel.setVisible(true);
            exceptPartNamePane.setManaged(true);
            noErrors = false;
        }
        else {
            name = partNameField.getText();
        }
        if (noErrors) {
            Part newPart;
            if (modifiedPart != null) {
                if (inHouseRadio.isSelected()) {
                    newPart = new InHouse(modifiedPart.getId(), name, price, stock, min, max, machineId);
                }
                else {
                    newPart = new Outsourced(modifiedPart.getId(), name, price, stock, min, max, compName);
                }

                int index = Inventory.getAllParts().indexOf(modifiedPart);
                Inventory.updatePart(index, newPart);
            }
            else {
                if (inHouseRadio.isSelected()) {
                    newPart = new InHouse(UniqueID.getUniquePartID(), name, price, stock, min, max, machineId);
                }
                else {
                    newPart = new Outsourced(UniqueID.getUniquePartID(), name, price, stock, min, max, compName);
                }
                Inventory.addPart(newPart);

            }
            modifiedPart = null;
            backToMain(actionEvent);
        }
    }

    /**
     * Upon button click, sets modifiedPart back to null and replaces current scene with mainScreen scene
     * @param actionEvent the action event
     * @throws IOException Catches any exceptions thrown during data input / output
     */
    public void onPartCancel(ActionEvent actionEvent) throws IOException {
        modifiedPart = null;
        backToMain(actionEvent);
    }

    /** RUNTIME ERROR - Initializes the form with existing data if part has been selected and modify button clicked on mainScreen.
     * Also initializes the real-time functionality of the search field
     * Being that both the Outsourced and InHouse parts are saved to inventory as the Part class,
     * I was not able to prefill the input fields of the machineID and companyName attributes through the Part superclass methods
     * when modifying an existing part.
     * I had to convert the Part objects back to their subclasses first by using the getClass().getSimpleName().equals() methods to
     * retrieve the subclass names.
     * Based on the classname found I could convert the Part object back to an Outsourced or InHouse object using
     * Outsourced newModPart = ((Outsourced)modifiedPart) or InHouse newModPart = ((InHouse)modifiedPart).
     * Then I could access the machineId or companyName to prefill the input fields
     */
    public void initialize() {
        if (modifiedPart != null) {
            titleLabel.setText("Modify Part");
            partNameField.setText(modifiedPart.getName());
            partStockField.setText(String.valueOf(modifiedPart.getStock()));
            partMinField.setText(String.valueOf(modifiedPart.getMin()));
            partMaxField.setText(String.valueOf(modifiedPart.getMax()));
            partPriceField.setText(String.valueOf(modifiedPart.getPrice()));
            if (modifiedPart.getClass().getSimpleName().equals("InHouse")) {
                InHouse newModPart = ((InHouse)modifiedPart);
                inHouseRadio.setSelected(true);
                specTagLabel.setText("Machine ID");
                specTagField.setText(String.valueOf(newModPart.getMachineId()));
            } else {
                Outsourced newModPart = ((Outsourced)modifiedPart);
                outSourcedRadio.setSelected(true);
                specTagLabel.setText("Company Name");
                specTagField.setText(String.valueOf(newModPart.getCompanyName()));
            }
        }
    }
}
