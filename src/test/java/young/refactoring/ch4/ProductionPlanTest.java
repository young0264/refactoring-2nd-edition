package young.refactoring.ch4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import young.refactoring.ch4.model.Province;

public class ProductionPlanTest {

    @Test
    void circDemandCostTest() {
        Province sampleProvinceData = ProductionSampleData.getSampleProvinceData();
        int demandCost = sampleProvinceData.getDemandCost();
        Assertions.assertEquals(demandCost, 270);
    }

}
