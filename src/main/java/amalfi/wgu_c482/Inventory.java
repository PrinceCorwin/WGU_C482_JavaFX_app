package amalfi.wgu_c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates Observable list collections of parts and products
 * @author Steve Corwin Amalfitano
 */
public class Inventory {
    private static boolean testDataAdded = false;
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * @return list of all products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * @return list of all parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     *
     * @param newPart the part to add to the list
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * @param newProduct the product to add to the list
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Adds a predefined set of test data to Inventory
     */
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

    /**
     * @return if test data has been added (boolean)
     */
    public static boolean isTestDataAdded() {
        return testDataAdded;
    }

    /**
     * @param testDataAdded the boolean to set whether test data has been added
     */
    public static void setTestDataAdded(boolean testDataAdded) {
        Inventory.testDataAdded = testDataAdded;
    }

    /**
     * lookup part in Inventory by its id
     * @param partId the id to lookup
     * @return the part object
     */
    public static Part lookupPart(int partId) {
        for(Part part : allParts) {
            if(part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * lookup product by its id
     * @param productId the id to lookup
     * @return the product object
     */
    public static Product lookupProduct(int productId) {
        for(Product prod : allProducts) {
            if(prod.getId() == productId) {
                return prod;
            }
        }
        return null;
    }

    /**
     * lookup part by its name using a partial string
     * @param partName string to lookup
     * @return the list of part(s)
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        for(Part part : allParts) {
            if (part.getName().contains(partName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }

    /**
     * lookup product by its name using a partial string
     * @param productName string to lookup
     * @return the list of product(s)
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        for(Product product : allProducts) {
            if (product.getName().contains(productName)) {
                namedProducts.add(product);
            }
        }
        return namedProducts;
    }

    /**
     * Replace a part in inventory with a new part
     * @param index index of the part to replace
     * @param selectedPart the part that will replace the existing part
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Replace a product in inventory with a new product
     * @param index the index of the product to be replaced
     * @param newProduct the product that will replace existing product
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Delete a selected part from inventory
     * @param selectedPart the part to delete
     * @return true upon delete
     */
    public static boolean deletePart(Part selectedPart) {
        UniqueID.addToAvailablePartIDs(selectedPart.getId());
        allParts.remove(selectedPart);
        return true;
    }

    /**
     * Delete selected product from inventory
     * @param selectedProduct the product to delete
     * @return true if product has no associated parts and is deleted,
     * otherwise returns false with no delete
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (selectedProduct.getAllAssociatedParts().size() == 0) {
            allProducts.remove(selectedProduct);
            UniqueID.addToAvailableProdIDs(selectedProduct.getId());
            return true;
        }
        else {
            return false;
        }
    }
}
