package young.refactoring.ch6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import young.refactoring.ch6.changeFunctionDeclaration.ChangeFunctionDeclarationExam;

public class ChangeFunctionDeclarationExamTest {

    @Test
    void testCircum() {
        ChangeFunctionDeclarationExam exam = new ChangeFunctionDeclarationExam();
        int radius = 5;
        double expected = 2 * Math.PI * radius;
        Assertions.assertEquals(expected, exam.circum(radius), 0.0001);
    }

}
