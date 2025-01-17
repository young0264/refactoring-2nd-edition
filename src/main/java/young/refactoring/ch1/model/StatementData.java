package young.refactoring.ch1.model;

import young.refactoring.ch1.enums.PlayType;

import java.util.List;

public class StatementData {
    private final Invoice invoice;
    private final Plays plays;

    public StatementData(Invoice invoice, Plays plays) {
        this.invoice = invoice;
        this.plays = plays;
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

    // For 이하 대상에 대한 금액
    // todo:
    //      연극 장르에 따라 계산 방법이 달라짐
    //      -> 함수 옮기기 기법
    public int amountFor(Performance performance) throws Exception {
        return new PerformanceCalculator(performance, playFor(performance)).amountFor();
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
    public int totalVolumeCredits(StatementData statementData) {
        int result = 0;
        for (Performance performance : statementData.getInvoice().getPerformanceList()) {
            //포인트를 적립
            result += volumeCreditFor(performance);
        }
        return result;
    }

    // credit 계산
    private int volumeCreditFor(Performance performance) {
        return new PerformanceCalculator(performance).volumeCreditFor();
    }

}
