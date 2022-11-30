package amalfi.wgu_c482;

/** This class creates the Outsourced part which inherits the Part class.
 * @author Steve Corwin Amalfitano.
 */

public class Outsourced extends Part{
    private String companyName;

    /** Initializes new Outsourced part object.
     *
     * @param id id of this part.
     * @param name name of this part.
     * @param price price of this part
     * @param stock the number of this part in stock
     * @param min the minimum allowed number of this part in stock
     * @param max the maximum allowed number of this part in stock
     * @param companyName name of company that sells the part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
        this.setMin(min);
        this.setMax(max);
        this.setCompanyName(companyName);
    }

    /**
     * @param companyName the company name to set for the part
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the company name of the part
     */
    public String getCompanyName() {
        return companyName;
    }
}
