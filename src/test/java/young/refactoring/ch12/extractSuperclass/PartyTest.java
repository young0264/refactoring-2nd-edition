package young.refactoring.ch12.extractSuperclass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartyTest {

    @Test
    @DisplayName("부서의 직원 수와 월간 총 인건비 계산")
    void testDepartmentTotalMonthlyCost() {
        Employee e1 = new Employee(1L, "Alice", 1000);
        Employee e2 = new Employee(2L, "Bob", 1500);
        Staff staff = new Staff();
        staff.length = 2;
        staff.employees = List.of(e1, e2);

        Department department = new Department("Engineering", staff);

        assertEquals(2, department.length());
        assertEquals(2500, department.totalMonthlyCost());
        assertEquals("Engineering", department.getName());
    }

    @Test
    @DisplayName("직원의 월간 비용과 연간 비용을 계산")
    void testEmployeeMonthlyAndAnnualCost() {
        Employee e = new Employee(1L, "Charlie", 2000);

        // Party에서 상속한 메서드 테스트
        assertEquals(2000, e.monthlyCost());
        assertEquals(2000 * 12, e.annualCost());

        assertEquals("Charlie", e.getName());
        assertEquals(1L, e.getId());
    }

    @Test
    @DisplayName("기본 Party 클래스의 기본 동작을 검증")
    void testPartyBaseMethodOverride() {
        Party party = new Party("Base");
        assertEquals("Base", party.getName());
        assertEquals(0, party.monthlyCost());
        assertEquals(0, party.annualCost());
    }
}