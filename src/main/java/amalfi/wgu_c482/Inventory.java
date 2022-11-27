package amalfi.wgu_c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static boolean testDataAdded = false;
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);

    }

    public static void addTestData() {
        addPart(new InHouse(UniqueID.getUniqueID(), "LED Strip",6,3, 2, 40, 34));
        addPart(new Outsourced(UniqueID.getUniqueID(),"44 watt bulb",3,30, 10, 60, "ABC"));
        addPart(new Outsourced(UniqueID.getUniqueID(),"20 watt bulb",2,4, 3, 44, "XYZ"));
        addProduct(new Product(48,"Garage Light (LED)",30,10, 2, 60));
        addProduct(new Product(5,"Ceiling Light (LED)",85,5, 2, 20));
        addProduct(new Product(32,"Work Light",15,1, 1, 40));
        Part testPart = new InHouse(UniqueID.getUniqueID(),"Switch",8,2, 2, 45, 2222);
        Product testProd = new Product(23,"Chandelier",200,2, 1, 4);
        testProd.addAssociatePart(testPart);
        addPart(testPart);
        addProduct(testProd);
        setTestDataAdded(true);
    }

    public static boolean isTestDataAdded() {
        return testDataAdded;
    }

    public static void setTestDataAdded(boolean testDataAdded) {
        Inventory.testDataAdded = testDataAdded;
    }

    public Part lookupPart(int partId) {
        return null;
    }
    public Product lookupProduct(int productId) {
        return null;
    }
    public ObservableList<Part> lookupPart(String partName) {
        return null;
    }
    public ObservableList<Product> lookupProduct(String productName) {
        return null;
    }
    public void updatePart(int index, Part selectedPart) {

    }
    public void updateProduct(int index, Product newProduct) {

    }
    public static boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        return true;
    }
    public static boolean deleteProduct(Product selectedProduct) {
        if (selectedProduct.getAllAssociatedPart().size() == 0) {
        allProducts.remove(selectedProduct);
        return true;
        } else {
            return false;
        }
    }
}
