package amalfi.wgu_c482;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class ModProductController {
    public TextField prodNameField;
    public TextField prodStockField;
    public TextField prodPriceField;
    public TextField prodMaxField;
    public TextField prodMinField;
    public Label exceptMinMax;
    public Label exceptProdName;
    public Label exceptPriceDouble;
    public Label exceptMaxStockInt;
    public Label exceptStockInt;
    public TextField searchParts;
    public TableColumn partIdSearchCol;
    public TableColumn partNameSearchCol;
    public TableColumn partStockSearchCol;
    public TableColumn partPriceSearchCol;
    public Button AddToProd;
    public TableColumn partIdAssocCol;
    public TableColumn partNameAssocCol;
    public TableColumn partStockAssocCol;
    public TableColumn partPriceAssocCol;
    public Button removeAssocPart;
    public Button modProdSave;
    public Button modProdCancel;

    public void onAddToProd(ActionEvent actionEvent) {
    }

    public void onRemoveAssocPart(ActionEvent actionEvent) {
    }

    public void onModProdSave(ActionEvent actionEvent) {
    }

    public void onModProdCancel(ActionEvent actionEvent) {
    }
}
