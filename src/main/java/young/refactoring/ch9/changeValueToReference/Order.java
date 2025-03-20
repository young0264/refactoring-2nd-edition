package young.refactoring.ch9.changeValueToReference;

public class Order {
    int number;
    Customer customer;

    public Order(int number, Customer customer) {
        this.number = number;
        this.customer = customer;
    }
}
