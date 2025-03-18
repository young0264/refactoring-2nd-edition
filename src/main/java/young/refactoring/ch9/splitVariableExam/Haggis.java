package young.refactoring.ch9.splitVariableExam;

// 이 스코틀랜드 음식이 다른 지역으로 전파된 거리를 구하는 코드
public class Haggis {

    public double distanceTravelled(Scenario scenario, int time) {
        double result;
        // 초기힘 적용
        int acc = scenario.primaryForce / scenario.mass; // 가속도(a) = 힘(F)/질량(m)
        int primaryTime = Math.min(time, scenario.delay);
        result = 0.5 * acc * primaryTime * primaryTime; // 전파된 거리

        // 두번째 힘 적용
        double secondaryTime = time - scenario.delay;
        if (secondaryTime > 0) {
            // 두 번째 힘을 반영해 다시 계산
            int primaryVelocity = acc * scenario.delay;
            acc = (scenario.primaryForce + scenario.secondaryForce) / scenario.mass;
            result += primaryVelocity * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime;
        }
        return result;
    }
}
