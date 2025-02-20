package young.refactoring.ch4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import young.refactoring.ch4.model.Producer;
import young.refactoring.ch4.model.Province;

import java.util.List;

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

    @Test
    @DisplayName("픽스처 수정")
    void changeProductionTest() {
        //given
        int shortFall = -6;
        int profit = 600;

        //when
        sampleProvinceData.getProducerList().get(0).setProduction(20);
        int actualShortFall = sampleProvinceData.getShortfall();
        int actualProfit = sampleProvinceData.getProfit();

        //then
        assertEquals(shortFall, actualShortFall);
        assertEquals(profit, actualProfit);
    }

}
