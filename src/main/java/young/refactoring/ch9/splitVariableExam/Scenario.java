package young.refactoring.ch9.splitVariableExam;

public class Scenario {
    int mass;               // 질량
    int delay;              // 힘이 적용되기 까지 지연 시간
    int primaryForce;       // 1차 힘(N)
    int secondaryForce;     // 2차 힘(N)

    public Scenario(int mass, int delay, int primaryForce, int secondaryForce) {
        this.mass = mass;
        this.delay = delay;
        this.primaryForce = primaryForce;
        this.secondaryForce = secondaryForce;
    }
}
