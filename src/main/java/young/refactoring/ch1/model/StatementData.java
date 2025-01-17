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
    public int amountFor(Performance performance) throws Exception {
        int result = 0;
        switch (playFor(performance).getType()) {
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
                throw new Exception("알 수 없는 장르 : " + playFor(performance));
        }
        return result;
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
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);

        //희극 관객 5명 추가마다 추가 포인트 제공.
        if (PlayType.COMEDY.equals(playFor(performance).getType())) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }

}
