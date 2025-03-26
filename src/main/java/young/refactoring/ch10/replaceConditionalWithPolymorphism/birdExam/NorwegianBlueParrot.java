package young.refactoring.ch10.replaceConditionalWithPolymorphism.birdExam;

public class NorwegianBlueParrot extends Bird {

    @Override
    public String plumage() {
        return this.voltage > 100 ? "그을렸다." : "예쁘다";
    }

    @Override
    public int airSpeedVelocity() {
        return this.isNailed ? 0 : 10 + this.voltage / 10;
    }

}
