package young.refactoring.ch1.model;

public class TragedyCalculator extends PerformanceCalculator{

    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int amountFor() {
        int result = 40000;
        if (performance.getAudience() > 30) {
            result += 1000 * (performance.getAudience() - 30);
        }
        return result;
    }

    @Override
    public int volumeCreditFor() {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);
        return result;
    }

}