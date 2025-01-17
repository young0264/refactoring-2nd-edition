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

    public PerformanceCalculator() {
    }

    // 생성자를 팩터리 함수로 변환
    public PerformanceCalculator createPerformanceCalculator(Performance performance, Play play) throws Exception {
        switch (play.getType()) {
            case TRAGEDY:
                return new TragedyCalculator(performance, play);
            case COMEDY:
                return new ComedyCalculator(performance, play);
            default:
                throw new Exception("알 수 없는 타입입니다.");
        }
    }

    public int amountFor() throws Exception {
        int result = 0;
        switch (play.getType()) {
            case TRAGEDY:
                return new TragedyCalculator(performance, play).amountFor();
            case COMEDY: //희극
                return new ComedyCalculator(performance, play).amountFor();
            default:
                throw new Exception("알 수 없는 장르 : " + play);
        }
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
