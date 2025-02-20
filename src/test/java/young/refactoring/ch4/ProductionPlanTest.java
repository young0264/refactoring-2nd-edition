package young.refactoring.ch4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import young.refactoring.ch4.model.Province;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductionPlanTest {

    @Test
    @DisplayName("수요량 계산")
    void circDemandCostTest() {
        Province sampleProvinceData = ProductionSampleData.getSampleProvinceData();
        int demandCost = sampleProvinceData.getDemandCost();
        assertEquals(demandCost, 270);
    }

    @Test
    @DisplayName("생산 부족분 계산")
    void shortfallTest() {
        //given
        Province sampleProvinceData = ProductionSampleData.getSampleProvinceData();
        int answer = 5;

        //when
        int shortfall = sampleProvinceData.getShortfall();

        //then
        assertEquals(shortfall, answer);
    }

}
