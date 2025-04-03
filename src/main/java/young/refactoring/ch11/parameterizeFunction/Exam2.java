package young.refactoring.ch11.parameterizeFunction;

public class Exam2 {

    public int baseCharge(int usage) {
        if (usage < 0) return 0;

        int amount =
                (int) (bottomBand(usage) * 0.03
                        + middleBand(usage) * 0.05
                        + topBand(usage) * 0.07);
        return usd(amount);
    }

    private int usd(int amount) {
        return amount;
    }

    private double topBand(int usage) {
        return usage > 200 ? usage - 200 : 0;
    }

    private double middleBand(int usage) {
        return usage > 100 ? Math.min(usage, 200) - 100 : 0;
    }

    private int bottomBand(int usage) {
        return Math.min(usage, 0);
    }

    // middle 개념 재정의 -> top, bottom 입력
    private int withinBand(int usage, int bottom, int top) {
        return usage > bottom ? Math.min(usage, top) - bottom : 0;
    }
}
