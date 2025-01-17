package young.refactoring.ch1.model;

import java.util.List;

public class StatementData {
    private final Plays plays;
    private final Invoice invoice;
    private PerformanceCalculator performanceCalculator;

    public StatementData(Invoice invoice, Plays plays) {
        this.invoice = invoice;
        this.plays = plays;
        this.performanceCalculator = new PerformanceCalculator();
    }

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Plays getPlays() {
        return plays;
    }

    public List<Performance> getPerformances() {
        return invoice.getPerformanceList();
    }

    public Play playFor(Performance performance) {
        return plays.get(performance);
    }

    public int amountFor(Performance performance) throws Exception {
        return performanceCalculator.createPerformanceCalculator(performance, playFor(performance)).amountFor();
    }

    public int totalAmount(StatementData statementData) throws Exception {
        int totalAmount = 0;
        for (Performance performance : statementData.getInvoice().getPerformanceList()) {
            //청구 내역 출력
            totalAmount += amountFor(performance);
        }
        return totalAmount / 100;
    }

    // 전체 관객수 기반 credit 계산
    public int totalVolumeCredits(StatementData statementData) throws Exception {
        int result = 0;
        for (Performance performance : statementData.getInvoice().getPerformanceList()) {
            //포인트를 적립
            result += volumeCreditFor(performance);
        }
        return result;
    }

    // credit 계산
    private int volumeCreditFor(Performance performance) throws Exception {
        return performanceCalculator.createPerformanceCalculator(performance, playFor(performance)).volumeCreditFor();
    }

}
