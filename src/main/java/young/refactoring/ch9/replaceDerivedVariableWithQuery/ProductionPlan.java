package young.refactoring.ch9.replaceDerivedVariableWithQuery;

import java.util.ArrayList;
import java.util.List;

public class ProductionPlan {
    int production;
    int calculatedProduction;
    List<Adjustment> adjustments = new ArrayList<>();

    public int getProduction() {
        return this.production;
    }

    public void applyAdjustment(Adjustment adjustment) {
        this.production += adjustment.amount;
    }

}
