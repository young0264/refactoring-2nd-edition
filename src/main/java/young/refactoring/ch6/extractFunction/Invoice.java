package young.refactoring.ch6.extractFunction;

import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }
}
