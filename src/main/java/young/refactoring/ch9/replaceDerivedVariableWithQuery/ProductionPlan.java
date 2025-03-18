package young.refactoring.ch9.replaceDerivedVariableWithQuery;

import java.util.ArrayList;
import java.util.List;

public class ProductionPlan {
    List<Adjustment> adjustments = new ArrayList<>();

    public int getProduction() {
        return getCalculatedProduction();
    }

    private Integer getCalculatedProduction() {
        return this.adjustments.stream()
                .map(a -> a.amount)
                .reduce(0, Integer::sum);
    }

    public void applyAdjustment(Adjustment adjustment) {
        this.adjustments.add(adjustment);
    }

}
