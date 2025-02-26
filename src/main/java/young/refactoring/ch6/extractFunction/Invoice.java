package young.refactoring.ch6.extractFunction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private List<Order> orders = new ArrayList<>();
    protected LocalDateTime dueDate;
    protected String customer;

    public List<Order> getOrders() {
        return orders;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getCustomer() {
        return customer;
    }
}
