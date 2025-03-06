package young.refactoring.ch7.encapsulateRecord;

public class ComparisonResult {
    private final double laterAmount;
    private final double change;

    public ComparisonResult(double laterAmount, double change) {
        this.laterAmount = laterAmount;
        this.change = change;
    }

    public double getLaterAmount() {
        return laterAmount;
    }

    public double getChange() {
        return change;
    }

}