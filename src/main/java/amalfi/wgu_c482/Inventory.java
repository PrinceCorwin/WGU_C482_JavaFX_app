package amalfi.wgu_c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static boolean testDataAdded = false;
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

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
        addPart(new InHouse(UniqueID.getUniquePartID(), "LED Strip",6.00,3, 2, 40, 34));
        addPart(new Outsourced(UniqueID.getUniquePartID(),"44 watt bulb",3.00,30, 10, 60, "ABC"));
        addPart(new Outsourced(UniqueID.getUniquePartID(),"20 watt bulb",2.03,4, 3, 44, "XYZ"));
        addProduct(new Product(UniqueID.getUniqueProdID(),"Garage Light (LED)",30.06,10, 2, 60));
        addProduct(new Product(UniqueID.getUniqueProdID(),"Ceiling Light (LED)",85.33,5, 2, 20));
        addProduct(new Product(UniqueID.getUniqueProdID(),"Work Light",15.22,1, 1, 40));
        Part testPart = new InHouse(UniqueID.getUniquePartID(),"Switch",8.66,2, 2, 45, 2222);
        Product testProd = new Product(UniqueID.getUniqueProdID(),"Chandelier",200.00,2, 1, 4);
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

    public static Part lookupPart(int partId) {
        for(Part part : allParts) {
            if(part.getId() == partId) {
                return part;
            }
        }
        return null;
    }
    public static Product lookupProduct(int productId) {
        for(Product prod : allProducts) {
            if(prod.getId() == productId) {
                return prod;
            }
        }
        return null;
    }
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        for(Part part : allParts) {
            if (part.getName().contains(partName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        for(Product product : allProducts) {
            if (product.getName().contains(productName)) {
                namedProducts.add(product);
            }
        }
        return namedProducts;
    }
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }
    public static boolean deletePart(Part selectedPart) {
        UniqueID.addToAvailablePartIDs(selectedPart.getId());
        allParts.remove(selectedPart);
        return true;
    }
    public static boolean deleteProduct(Product selectedProduct) {
        if (selectedProduct.getAllAssociatedParts().size() == 0) {
            UniqueID.addToAvailableProdIDs(selectedProduct.getId());
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }
}
