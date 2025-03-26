package young.refactoring.ch10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import young.refactoring.ch10.replaceConditionalWithPolymorphism.birdExam.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 조건부 로직을 다형성으로 바꾸기
public class ReplaceConditionalWithPolymorphismTest {
    Exam exam = new Exam();

    @Test
    @DisplayName("유럽 제비: 속도 35, 깃털 좋음")
    void testEuropeanSwallow() {
        Bird bird = exam.createBird("유럽 제비");
        assertEquals("보통이다.", exam.plumage(bird));
        assertEquals(35, exam.airSpeedVelocity(bird));
    }

    @Test
    @DisplayName("기본 Bird: 속도 0, 깃털 보통")
    void testDefaultBird() {
        Bird bird = exam.createBird("이상한 새");
        assertEquals("알 수 없다.", exam.plumage(bird));
        assertEquals(0, exam.airSpeedVelocity(bird));
    }

    @Test
    @DisplayName("[아프리카 제비]: 코코넛 1개 → 보통이다 / 속도 38")
    void testAfricanSwallow_normal() {
        AfricanSwallow bird = new AfricanSwallow();
        bird.setNumberOfCoconuts(1);

        assertEquals("보통이다.", exam.plumage(bird));
        assertEquals(38, exam.airSpeedVelocity(bird));
    }

    @Test
    @DisplayName("[아프리카 제비]: 코코넛 3개 → 지쳤다 / 속도 34")
    void testAfricanSwallow_tired() {
        AfricanSwallow bird = new AfricanSwallow();
        bird.setNumberOfCoconuts(3);

        assertEquals("지쳤다.", exam.plumage(bird));
        assertEquals(34, exam.airSpeedVelocity(bird));
    }

    @Test
    @DisplayName("[노르웨이 파랑 앵무]: 전압 50, 못박힘 false → 예쁘다 / 속도 15")
    void testNorwegianBlueParrot_normal() {
        NorwegianBlueParrot bird = new NorwegianBlueParrot();
        bird.setVoltage(50);
        bird.setNailed(false);

        assertEquals("예쁘다", exam.plumage(bird));
        assertEquals(15, exam.airSpeedVelocity(bird));
    }

    @Test
    @DisplayName("[노르웨이 파랑 앵무]: 전압 200, 못박힘 true → 그을렸다 / 속도 0")
    void testNorwegianBlueParrot_burned_and_nailed() {
        NorwegianBlueParrot bird = new NorwegianBlueParrot();
        bird.setVoltage(200);
        bird.setNailed(true);

        assertEquals("그을렸다.", exam.plumage(bird));
        assertEquals(0, exam.airSpeedVelocity(bird));
    }

}
