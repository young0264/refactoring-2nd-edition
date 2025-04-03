package young.refactoring.ch11.preserveWholeObject;

public class HeatingPlan {

    public Range temperatureRange;

    public HeatingPlan(Range temperatureRange) {
        this.temperatureRange = temperatureRange;
    }

    public boolean nexWithinRange(Range range) {
        return withinRange(range.low, range.high);
    }

    public boolean withinRange(int roomLow, int roomHigh) {
        return (temperatureRange.low < roomLow && temperatureRange.high > roomHigh);
    }

}
