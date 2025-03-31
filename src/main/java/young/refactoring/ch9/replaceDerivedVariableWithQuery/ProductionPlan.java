package young.refactoring.ch9.replaceDerivedVariableWithQuery;

import java.util.ArrayList;
import java.util.List;

public class ProductionPlan {
    int production;
    int initialProduction;
    int productionAccumulator;
    List<Adjustment> adjustments = new ArrayList<>();

    public int getProduction() {
        return this.initialProduction + this.productionAccumulator;
    }

    private Integer getCalculatedProduction() {
        return this.adjustments.stream()
                .map(a -> a.amount)
                .reduce(0, Integer::sum);
    }

    public void applyAdjustment(Adjustment adjustment) {
        this.adjustments.add(adjustment);
        this.productionAccumulator += adjustment.amount;
    }

    public ProductionPlan(int production) {
        this.initialProduction = production;
        this.productionAccumulator = 0;
        this.adjustments = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "ProductionPlan{" +
                "production=" + production +
                ", initialProduction=" + initialProduction +
                ", productionAccumulator=" + productionAccumulator +
                ", adjustments=" + adjustments +
                '}';
    }
}
