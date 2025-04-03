package young.refactoring.ch11.replaceQueryWithParameter;

public class HeatingPlan {

    private int min;
    private int max;

    public int targetTemperature() {
        if (Thermostat.selectedTemperature > this.max) {
            return this.max;
        }
        if (Thermostat.selectedTemperature < this.min) {
            return this.min;
        }
        return Thermostat.selectedTemperature;
    }

}
