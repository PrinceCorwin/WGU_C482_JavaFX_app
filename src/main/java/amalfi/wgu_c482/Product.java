package amalfi.wgu_c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class creates the product and its attributes.
 * @author Steve Corwin Amalfitano.
 */

public class Product {
    private final ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** Initializes new Product object
     *
     * @param id id of this product.
     * @param name name of this product.
     * @param price price of this product
     * @param stock the number of this product in stock
     * @param min the minimum allowed number of this product in stock
     * @param max the maximum allowed number of this product in stock
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = FXCollections.observableArrayList();
    }

    /**
     * @return the id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set for the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set for the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set for the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock for the product
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set for the product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min for the product
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set for the product
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max for the product
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set for the product
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @param part the part to add to the associatedParts list for the product
     */
    public void addAssociatePart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * @param selectedAssociatedPart the part to remove from the associatedParts list for the product
     */
    public boolean deleteAssociatePart(Part selectedAssociatedPart) {
        this.associatedParts.remove(selectedAssociatedPart);
        return true;
    }

    /**
     * @return the associatedParts list for the product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return this.associatedParts;
    }

}