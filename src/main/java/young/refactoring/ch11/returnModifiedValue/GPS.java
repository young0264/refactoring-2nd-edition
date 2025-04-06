package young.refactoring.ch11.returnModifiedValue;

import java.util.ArrayList;
import java.util.List;

public class GPS {
    List<Point> points = new ArrayList<>();
    int totalAscent;
    int totalTime;
    int totalDistance;

    public void calculate() {
        totalAscent = calculateAscent();
        totalTime = calculateTime();
        totalDistance = calculateDistance();
        if (totalDistance == 0) {
            return;
        }
        int pace = totalTime / 60 / totalDistance;
    }

    private int calculateAscent() {
        int result = 0;
        for (int i = 1; i < points.size(); i++) {
            int verticalChange = points.get(i).elevation - points.get(i - 1).elevation;
            result += Math.max(verticalChange, 0); // 음수는 계산 X
        }
        return result;
    }

    private int calculateTime() {
        return 0;
    }

    private int calculateDistance() {
        return 0;
    }

}