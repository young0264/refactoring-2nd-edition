package young.refactoring.ch9.changeValueToReference;

public class Customer {
    Long id;

    public Long getId() {
        return id;
    }

    public Customer(Long id) {
        this.id = id;
    }
}
