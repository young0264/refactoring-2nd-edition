package young.refactoring.ch6.extractVariable;

public class Order {
    private int quantity;
    private int itemPrice;

    public Order(int quantity, int itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }
}
