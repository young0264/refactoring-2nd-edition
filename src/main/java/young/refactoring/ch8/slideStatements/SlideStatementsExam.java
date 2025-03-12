package young.refactoring.ch8.slideStatements;

public class SlideStatementsExam {
    public void slideStatements() {
        PricingPlan pricingPlan = retrievePricingPlan();
        Order order = retrieveOrder();
        int baseCharge = pricingPlan.base;
        int charge;
        int chargePerUnit = pricingPlan.unit;
        int units = order.units;
        int discount;
        charge = baseCharge + units * chargePerUnit;
        int discountableUnits = Math.max(units - pricingPlan.discountThreshold, 0);
        discount = (int) (discountableUnits * pricingPlan.discountFactor);
        if (order.isRepeat) discount += 20;
        charge = charge - discount;
        chargeOrder(charge);
    }

    private static void chargeOrder(int charge) {
    }

    public PricingPlan retrievePricingPlan() {
        return null;
    }

    public Order retrieveOrder() {
        return null;
    }
}
