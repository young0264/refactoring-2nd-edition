package young.refactoring.ch10.replaceControlFlagWithBreak;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ExamTest {
    @Test
    void shouldReturnAlert_whenJokerIsPresent() {
        Exam exam = new Exam();
        People people = new People(List.of(
                new Person("해리"),
                new Person("조커"),
                new Person("톰")
        ));

        String result = exam.checkForMiscreants(people);
        assertEquals("찾았다는 알림을 보냅니다.", result);
    }

    @Test
    void shouldReturnAlert_whenSarumanIsPresent() {
        Exam exam = new Exam();
        People people = new People(List.of(
                new Person("간달프"),
                new Person("사루만")
        ));

        String result = exam.checkForMiscreants(people);
    }

    @Test
    void shouldReturnNull_whenNoMiscreants() {
        Exam exam = new Exam();
        People people = new People(List.of(
                new Person("해리"),
                new Person("톰"),
                new Person("메리")
        ));

        String result = exam.checkForMiscreants(people);
        assertEquals("신원불명", result);
    }
}