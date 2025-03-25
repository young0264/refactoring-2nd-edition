package young.refactoring.ch10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import young.refactoring.ch10.decomposeConditional.Exam;
import young.refactoring.ch10.decomposeConditional.Plan;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class decomposeConditionalTest {

    private Exam exam;
    private Plan plan;

    @BeforeEach
    void setUp() {
        exam = new Exam();
        exam.setQuantity(10);

        LocalDateTime summerStart = LocalDateTime.of(2024, 6, 1, 0, 0);
        LocalDateTime summerEnd = LocalDateTime.of(2024, 8, 31, 23, 59);

        double summerRate = 1.5;
        double regularRate = 2.0;
        double regularServiceCharge = 5.0;

        plan = new Plan(summerStart, summerEnd, summerRate, regularRate, regularServiceCharge);


    }

    @Test
    @DisplayName("여름시즌 rate 계산이 정상적으로 되는지")
    void calculatePayment_summerSeason_appliesSummerCharge() {
        LocalDateTime summerDate = LocalDateTime.of(2024, 7, 15, 0, 0);

        exam.calculatePayment(plan, summerDate);

        int expectedCharge = (int) (exam.getQuantity() * plan.summerRate());
        assertEquals(expectedCharge, exam.getCharge());
    }

    @Test
    @DisplayName("비 여름시즌 rate 계산이 정상적으로 되는지")
    void calculatePayment_nonSummerSeason_appliesRegularCharge() {
        LocalDateTime nonSummerDate = LocalDateTime.of(2024, 10, 1, 0, 0);

        exam.calculatePayment(plan, nonSummerDate);

        int expectedCharge = (int) (exam.getQuantity() * plan.regularRate() + plan.regularServiceCharge());
        assertEquals(expectedCharge, exam.getCharge());
    }
}
