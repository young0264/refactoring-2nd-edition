package young.refactoring.ch6.extractVariable;

public class Order {
    protected int quantity;
    protected int itemPrice;

    public Order(int quantity, int itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }
}
