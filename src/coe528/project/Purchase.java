package coe528.project;




/**
 * This class defines a purchase of an item.
 */
public class Purchase {

    /* item that has been purchased */
    private Item item;

    /* quantity of the item purchased */
    private int quantity;

    /* fee associated with the purchase */
    private double fee;

    private String itemName;

    private double itemPrice;

    private double totalCost;

    /**
     * Class constructor that creates a new instance of a Purchase.
     *
     * @param item item that has been purchased
     * @param quantity quantity of item purchased
     * @param fee
     */
    public Purchase(Item item, int quantity, double fee) {
        this.item = item;
        this.quantity = quantity;
        this.fee = fee;
        itemName = item.getName();
        itemPrice = item.getPrice();
        totalCost = itemPrice * quantity + fee;
    }

    /**
     *
     * @return
     */
    public Item getItem() {
        return item;
    }

    /**
     *
     * @param item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public double getFee() {
        return fee;
    }

    /**
     *
     * @return
     */
    public String getItemName() {
        return itemName;
    }

    /**
     *
     * @return
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     *
     * @return
     */
    public double getTotalCost() {
        return totalCost;
    }

}
