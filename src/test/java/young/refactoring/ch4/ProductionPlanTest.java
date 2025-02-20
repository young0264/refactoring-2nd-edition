package young.refactoring.ch4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import young.refactoring.ch4.model.Province;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductionPlanTest {

    Province sampleProvinceData;

    @BeforeEach
    void setUp() {
        sampleProvinceData = ProductionSampleData.getSampleProvinceData();
    }

    @Test
    @DisplayName("수요량 계산")
    void circDemandCostTest() {
        int demandCost = sampleProvinceData.getDemandCost();
        assertEquals(demandCost, 270);
    }

    @Test
    @DisplayName("생산 부족분 계산")
    void shortfallTest() {
        //given
        int answer = 5;

        //when
        int shortfall = sampleProvinceData.getShortfall();

        //then
        assertEquals(shortfall, answer);
    }

}
