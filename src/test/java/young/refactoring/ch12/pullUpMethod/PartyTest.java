package young.refactoring.ch12.pullUpMethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartyTest {

    @Test
    void testAnnualCostForEmployee() {
        Employee employee = new Employee();
        employee.monthlyCost = 300;

        assertEquals(3600, employee.annualCost());
    }

    @Test
    void testAnnualCostForDepartment() {
        Department department = new Department();
        department.monthlyCost = 1000;

        assertEquals(12000, department.annualCost());
    }

    @Test
    void testAnnualCostForParty() {
        Party party = new Party();
        party.monthlyCost = 500;

        assertEquals(6000, party.annualCost());
    }

}