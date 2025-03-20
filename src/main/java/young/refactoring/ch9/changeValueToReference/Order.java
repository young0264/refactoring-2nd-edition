package young.refactoring.ch9.changeValueToReference;

public class Order {
    int number;
    Customer customer;

    public Order(int number, Long customerId) {
        this.number = number;
        this.customer = new Customer(customerId);
    }
}
