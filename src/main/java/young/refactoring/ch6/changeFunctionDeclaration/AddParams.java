package young.refactoring.ch6.changeFunctionDeclaration;

import java.util.ArrayDeque;
import java.util.Queue;

public class AddParams {
    protected Queue<Customer> reservations = new ArrayDeque<>();

    public void addReservation(Customer customer) {
        zz_addReservation(customer, false);
    }

    private void zz_addReservation(Customer customer, boolean isPriority) {
        assert isPriority == true || isPriority == false;
        this.reservations.add(customer);
    }

}
