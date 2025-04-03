package young.refactoring.ch11.replaceQueryWithParameter;

public class HeatingPlan {

    private int min;
    private int max;

    public int newTargetTemperature(int selectedTemperature) {
        if (selectedTemperature > this.max) {
            return this.max;
        }
        if (selectedTemperature < this.min) {
            return this.min;
        }
        return selectedTemperature;
    }

}
