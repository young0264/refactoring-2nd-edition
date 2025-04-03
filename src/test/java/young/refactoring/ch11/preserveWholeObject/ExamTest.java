package young.refactoring.ch11.preserveWholeObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExamTest {
    private Exam exam;

    @BeforeEach
    void setUp() {
        exam = new Exam();

        // Room 설정
        Range roomRange = new Range(20, 25);  // 현재 방 온도 범위
        Room room = new Room(roomRange);
        exam.room = room;

        // HeatingPlan 설정 (허용 온도 범위)
        HeatingPlan heatingPlan = new HeatingPlan(new Range(18, 26));
        exam.heatingPlan = heatingPlan;
    }

    @Test
    void client_whenRoomTempWithinPlan_shouldNotThrow() {
        assertDoesNotThrow(() -> exam.client());
    }

    @Test
    void client_whenRoomTempOutsidePlan_shouldThrow() {
        // 범위밖의 room 낮은 온도 범위 설정
        exam.room = new Room(new Range(10, 15));
        Exception exception = assertThrows(Exception.class, () -> exam.client());
        assertEquals("방 온도가 지정 범위를 벗어났습니다.", exception.getMessage());
    }


}