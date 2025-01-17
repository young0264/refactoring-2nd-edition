package young.refactoring.ch1.model;

import young.refactoring.ch1.enums.PlayType;

public class PerformanceCalculator {

    protected Performance performance;
    protected Play play;

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
        throw new Exception("서브 클래스에서 처리하도록 설계되었습니다.");
    }

    public int volumeCreditFor() throws Exception {
        throw new Exception("서브 클래스에서 처리하도록 설계되었습니다.");
    }

}
