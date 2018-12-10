package coe528.project;




/**
 * This class defines an item for purchase by customer of the bank account
 * application.
 */
public class Item {

    /* unique id of the item */
    private int id;

    /* name of the item */
    private String name;

    /* price of the item */
    private double price;

    /**
     * Class constructor that creates a new item.
     *
     * @param id
     * @param name name of the item
     * @param price price of the item
     */
    public Item(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Get the name of the item
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the item
     *
     * @param name name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the price of the item.
     *
     * @return price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of the item
     *
     * @param price price of the item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get the the id of the item
     *
     * @return the id of the item
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s ($%.2f)", name, price);
    }
}
