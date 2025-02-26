package young.refactoring.ch6.extractFunction;

public class Order {

    private Record record;
    private int quantity;
    private int itemPrice;

    public Order(Record record) {
        this.record = record;
    }

    public Record getRecord() {
        return record;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getPrice() {
        return (int)(this.quantity * this.itemPrice
                - Math.max(0, this.quantity - 500) * this.itemPrice * 0.05
                + Math.min(this.quantity * this.itemPrice * 0.1, 100));
    }


}
