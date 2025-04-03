package young.refactoring.ch11.separateQueryFromModifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExamTest {
    private Exam exam;

    @BeforeEach
    void setUp() {
        exam = new Exam();
    }

    @Test
    @DisplayName("범죄자가 없으면 빈 문자열을 반환한다")
    void returnsEmptyWhenNoMiscreantFound() {
        exam.people = List.of(
                new Person("Batman"),
                new Person("Gandalf")
        );

        String result = exam.findForMiscreant();

        assertEquals("", result);
    }

    @Test
    @DisplayName("조커가 먼저 있으면 '조커'를 반환한다")
    void returnsJokerWhenJokerIsPresent() {
        exam.people = List.of(
                new Person("조커"),
                new Person("사루만")
        );

        String result = exam.findForMiscreant();

        assertEquals("조커", result);
    }

    @Test
    @DisplayName("사루만만 있으면 '사루만'을 반환한다")
    void returnsSarumanWhenOnlySarumanIsPresent() {
        exam.people = List.of(new Person("사루만"));

        String result = exam.findForMiscreant();

        assertEquals("사루만", result);
    }

}