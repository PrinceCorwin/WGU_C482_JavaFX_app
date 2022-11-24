package amalfi.wgu_c482;

public class Player {
    private int id;
    private String name;
    private int invent;
    private String price;

    public Player(int id, String name, int invent, String price) {
        this.id = id;
        this.name = name;
        this.invent = invent;
        this.price = price;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInvent() {
        return invent;
    }

    public void setInvent(int invent) {
        this.invent = invent;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
