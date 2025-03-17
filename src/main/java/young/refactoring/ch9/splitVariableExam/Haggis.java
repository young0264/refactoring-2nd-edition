package young.refactoring.ch9.splitVariableExam;

public class Haggis {
    public double distanceTravelled(Scenario scenario, int time) {
        double result;
        int acc = scenario.primaryForce / scenario.mass; // 가속도 = 힘 * 질량
        int primaryTime = Math.min(time, scenario.delay);
        result = 0.5 * acc * primaryTime * primaryTime; // 전파된 거리
        int secondaryTime = time - scenario.delay;
        if (secondaryTime > 0) {
            // 두 번째 힘을 반영해 다시 계산
            int primaryVelocity = acc * scenario.delay;
            acc = (scenario.primaryForce + scenario.secondaryForce) / scenario.mass;
            result += primaryVelocity * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime;
        }
        return result;
    }
}
