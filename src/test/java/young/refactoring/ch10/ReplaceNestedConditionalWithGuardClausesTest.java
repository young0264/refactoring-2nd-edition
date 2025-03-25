package young.refactoring.ch10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import young.refactoring.ch10.replaceNestedConditionalWithGuardClauses.AmountReason;
import young.refactoring.ch10.replaceNestedConditionalWithGuardClauses.Employee;
import young.refactoring.ch10.replaceNestedConditionalWithGuardClauses.Exam;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReplaceNestedConditionalWithGuardClausesTest {

    @Test
    @DisplayName("퇴사한 직원인 경우")
    void testPayAmount_whenSeparated_thenReturnsSEP() {
        Employee separatedEmployee = new Employee(true, false);
        Exam exam = new Exam();

        AmountReason result = exam.payAmount(separatedEmployee);

        assertEquals(new AmountReason(0, "SEP"), result);
    }

    @Test
    @DisplayName("은퇴한 직원인 경우")
    void testPayAmount_whenRetired_thenReturnsRET() {
        Employee retiredEmployee = new Employee(false, true);
        Exam exam = new Exam();

        AmountReason result = exam.payAmount(retiredEmployee);

        assertEquals(new AmountReason(0, "RET"), result);
    }

    @Test
    @DisplayName("그 외 직원인 경우")
    void testPayAmount_whenNormalEmployee_thenReturnsELSE() {
        Employee normalEmployee = new Employee(false, false);
        Exam exam = new Exam();

        AmountReason result = exam.payAmount(normalEmployee);

        assertEquals(new AmountReason(0, "ELSE"), result);
    }

}
