package amalfi.wgu_c482;

/** This class creates the InHouse part which inherits the Part class.
 * @author Steve Corwin Amalfitano.
 */
public class InHouse extends Part{
    private int machineId;

    /** Initializes new InHouse part object
     *
     * @param id id of this part.
     * @param name name of this part.
     * @param price price of this part
     * @param stock the number of this part in stock
     * @param min the minimum allowed number of this part in stock
     * @param max the maximum allowed number of this part in stock
     * @param machineId the machine id for this part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
        this.setMin(min);
        this.setMax(max);
        this.setMachineId(machineId);
    }

    /**
     * @param machineId the machine id to set for the part
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * @return the machine id of the part
     */
    public int getMachineId() {
        return machineId;
    }
}
