package young.refactoring.ch10.replaceConditionalWithPolymorphism.birdExam;

// 유럽제비
public class EuropeanSwallow extends Bird {
    @Override
    public String plumage() {
        return "보통이다.";
    }

    @Override
    public int airSpeedVelocity() {
        return 35;
    }
}
