package young.refactoring.ch6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import young.refactoring.ch6.introduceParameterObject.IntroduceParamObjExam;
import young.refactoring.ch6.introduceParameterObject.Reading;
import young.refactoring.ch6.introduceParameterObject.Station;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntroduceParamObjExamTest {
    IntroduceParamObjExam introduceParamObjExam;
    Station station;

    @BeforeEach
    void setUp() {
        introduceParamObjExam = new IntroduceParamObjExam();
        station = new Station("ZB1",List.of(
                new Reading(10),  // 범위 내
                new Reading(20),  // 범위 내
                new Reading(5),   // 범위 밖 (min 미만)
                new Reading(25)   // 범위 밖 (max 초과)
        ));
    }

    @Test
    void readingOutsideRange_ShouldReturnReadingsOutsideGivenRange() {
        List<Reading> result = introduceParamObjExam.readingOutsideRange(station, 10, 20);

        assertEquals(2, result.size(), "범위를 벗어난 데이터가 2개여야 합니다.");
        assertTrue(result.contains(new Reading(5)), "5도인 Reading이 결과에 포함되어야 합니다.");
        assertTrue(result.contains(new Reading(25)), "25도인 Reading이 결과에 포함되어야 합니다.");
    }

    @Test
    void readingOutsideRange_ShouldReturnEmptyList_WhenAllReadingsAreWithinRange() {
        Station stationWithinRange = new Station("ZB1",List.of(
                new Reading(10),
                new Reading(15),
                new Reading(20)
        ));

        List<Reading> result = introduceParamObjExam.readingOutsideRange(stationWithinRange, 10, 20);
        assertTrue(result.isEmpty(), "모든 Reading이 범위 내에 있으므로 결과는 비어 있어야 합니다.");
    }

}
