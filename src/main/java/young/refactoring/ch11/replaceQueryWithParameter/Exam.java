package young.refactoring.ch11.replaceQueryWithParameter;

/** 질의 함수 매개변수로 바꾸기 */
public class Exam {
    
    private HeatingPlan heatingPlan;

    // 클라이언트 
    public void client() {
        if (heatingPlan.targetTemperature() > Thermostat.currentTemperature) setToHeat();
        else if (heatingPlan.targetTemperature() < Thermostat.currentTemperature) setToCool();
        else setOff();
    }

    private void setToCool() {
    }

    private void setOff() {
    }

    private void setToHeat() {
    }
}
