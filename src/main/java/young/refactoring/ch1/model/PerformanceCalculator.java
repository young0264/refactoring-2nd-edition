package young.refactoring.ch1.model;

import young.refactoring.ch1.enums.PlayType;

public class PerformanceCalculator {

    private Performance performance;
    private Play play;

    public PerformanceCalculator(Performance performance, Play play) {
        this.play = play;
        this.performance = performance;
    }

    public PerformanceCalculator(Performance performance) {
        this.performance = performance;
    }

    public int amountFor() throws Exception {
        int result = 0;
        switch (play.getType()) {
            case TRAGEDY:
                result = 40000;
                if (performance.getAudience() > 30) {
                    result += 1000 * (performance.getAudience() - 30);
                }
                break;
            case COMEDY: //희극
                result = 30000;
                if (performance.getAudience() > 20) {
                    result += 10000 + 500 * (performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
                break;
            default:
                throw new Exception("알 수 없는 장르 : " + play);
        }
        return result;
    }

    public int volumeCreditFor() {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);

        //희극 관객 5명 추가마다 추가 포인트 제공.
        if (PlayType.COMEDY.equals(this.play.getType())) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }
}
