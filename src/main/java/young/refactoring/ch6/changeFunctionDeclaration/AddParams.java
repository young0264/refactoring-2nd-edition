package young.refactoring.ch6.changeFunctionDeclaration;

import java.util.ArrayDeque;
import java.util.Queue;

public class AddParams {
    protected Queue<Customer> reservations = new ArrayDeque<>();

    public void addReservation(Customer customer) {
        this.reservations.add(customer);
    }

}
