package young.refactoring.ch11.preserveWholeObject;

public class HeatingPlan {

    public Range temperatureRange;

    public boolean withinRange(int roomLow, int roomHigh) {
        return (temperatureRange.low < roomLow && temperatureRange.high > roomHigh);
    }
}
