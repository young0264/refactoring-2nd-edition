package young.refactoring.ch12.pullUpConstrucBody;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PartyTest {

    @Test
    @DisplayName("Employee는 기본적으로 privileged가 아님")
    void testEmployeeIsNotPrivileged() {
        Employee employee = new Employee("John");
        assertFalse(employee.isPrivileged());
    }

    @Test
    @DisplayName("Manager의 grade가 5 이상이면 privileged")
    void testManagerIsPrivileged() {
        Grade highGrade = new Grade(5);
        Manager manager = new Manager("Alice", highGrade, true);

        assertTrue(manager.isPrivileged());
    }

    @Test
    @DisplayName("Manager의 grade가 4 이하이면 privileged가 아님")
    void testManagerIsNotPrivileged() {
        Grade lowGrade = new Grade(3);
        Manager manager = new Manager("Bob", lowGrade, false);

        assertFalse(manager.isPrivileged());
    }

    @Test
    @DisplayName("Department 생성 시 이름과 staff가 올바르게 설정되는지")
    void testDepartmentCreation() {
        Staff staff = new Staff("Kim");
        Department department = new Department("HR", staff);

        assertEquals("HR", department.name);
        assertEquals("Kim", department.staff.name);
    }
}