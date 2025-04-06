package young.refactoring.ch11.returnModifiedValue;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GPSTest {

    @Test
    void testCalculateAscentOnly() {
        GPS gps = new GPS();
        gps.points = List.of(
                new Point(100),
                new Point(120), // +20
                new Point(110), // -10 → 무시
                new Point(130)  // +20
        );

        gps.calculate();

        assertEquals(40, gps.totalAscent);  // 20 + 20
        // 아래 값들은 현재 0으로 초기화된 채 calculateTime/Distance가 구현되지 않았으므로 다음과 같이 검증
        assertEquals(0, gps.totalTime);
        assertEquals(0, gps.totalDistance);
    }

}