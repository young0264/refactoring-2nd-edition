package young.refactoring.ch10.replaceConditionalWithPolymorphism.birdExam;

public class Bird {
    String type;
    int numberOfCoconuts;
    int voltage;
    boolean isNailed;

    public String plumage() {
        return "알 수 없다.";
    }

    public int airSpeedVelocity() {
        return 0;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumberOfCoconuts(int numberOfCoconuts) {
        this.numberOfCoconuts = numberOfCoconuts;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public void setNailed(boolean nailed) {
        isNailed = nailed;
    }
}
