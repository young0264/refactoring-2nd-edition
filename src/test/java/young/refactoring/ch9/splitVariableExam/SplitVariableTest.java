package young.refactoring.ch9.splitVariableExam;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitVariableTest {

    @Test
    @DisplayName("기본 힘(Primary Force)만 적용된 경우 거리 계산")
    void testDistanceTravelled_PrimaryForceOnly() {
        // mass=10, delay=5, primaryForce=20, secondaryForce=0
        Scenario scenario = new Scenario(10, 5, 20, 0);
        Haggis haggis = new Haggis();

        double distance = haggis.distanceTravelled(scenario, 5);
        assertEquals(25.0, distance, 0.01, "기본 힘만 적용된 경우 거리가 올바르게 계산되어야 합니다.");
    }

    @Test
    @DisplayName("지연 시간 후 보조 힘(Secondary Force)이 적용된 경우 거리 계산")
    void testDistanceTravelled_WithSecondaryForce() {
        Scenario scenario = new Scenario(10, 5, 20, 10); // mass=10, delay=5, primaryForce=20, secondaryForce=10
        Haggis haggis = new Haggis();

        double distance = haggis.distanceTravelled(scenario, 10);
        assertEquals(112.5, distance, 0.01, "보조 힘이 추가된 경우 거리가 올바르게 계산되어야 합니다.");
    }

    @Test
    @DisplayName("시간이 지연시간보다 짧은 경우")
    void testDistanceTravelled_BeforeDelay() {
        Scenario scenario = new Scenario(10, 5, 20, 10);
        Haggis haggis = new Haggis();

        double distance = haggis.distanceTravelled(scenario, 3);
        assertEquals(9.0, distance, 0.01, "지연 시간 이전 거리 계산이 올바르게 이루어져야 합니다.");
    }

    @Test
    @DisplayName("힘이 없는 경우 거리 계산")
    void testDistanceTravelled_NoForce() {
        Scenario scenario = new Scenario(10, 5, 0, 0); // mass=10, delay=5, primaryForce=0, secondaryForce=0
        Haggis haggis = new Haggis();

        double distance = haggis.distanceTravelled(scenario, 10);
        assertEquals(0.0, distance, 0.01, "힘이 없을 경우 거리는 0이어야 합니다.");
    }
}