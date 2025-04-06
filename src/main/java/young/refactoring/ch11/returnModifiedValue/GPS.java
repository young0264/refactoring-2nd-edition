package young.refactoring.ch11.returnModifiedValue;

import java.util.ArrayList;
import java.util.List;

public class GPS {
    List<Point> points = new ArrayList<>();
    int totalAscent;
    int totalTime;
    int totalDistance;

    public void calculate() {
        totalAscent = 0;
        totalTime = 0;
        totalDistance = 0;

        calculateAscent();
        calculateTime();
        calculateDistance();
        if (totalDistance == 0) {
            return;
        }
        int pace = totalTime / 60 / totalDistance;
    }

    private void calculateAscent() {
        for (int i = 1; i < points.size(); i++) {
            int verticalChange = points.get(i).elevation - points.get(i - 1).elevation;
            totalAscent += Math.max(verticalChange, 0); // 음수는 계산 X
        }
    }

    private int calculateTime() {
        return 0;
    }

    private int calculateDistance() {
        return 0;
    }

}