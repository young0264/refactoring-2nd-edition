package young.refactoring.ch6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import young.refactoring.ch6.changeFunctionDeclaration.AddParams;
import young.refactoring.ch6.changeFunctionDeclaration.Customer;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddParamTest {
    AddParams addParams;

    @BeforeEach
    void setUp() {
        addParams = new AddParams();
    }

    @Test
    void addReservation_ShouldAddCustomerToQueue() {
        Customer firstCustomer = new Customer("eui young");
        Customer secondCustomer = new Customer("ye eun");

        addParams.addReservation(firstCustomer);
        addParams.addReservation(secondCustomer);

        Queue<Customer> reservations = addParams.getReservations();
        assertFalse(reservations.isEmpty(), "Queue should not be empty after adding a reservation");
        assertEquals(firstCustomer, reservations.poll(), "First element in queue should be the added customer");
        assertEquals(secondCustomer, reservations.poll(), "Second element in queue should be the added customer");
    }

}
