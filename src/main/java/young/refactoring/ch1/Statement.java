package young.refactoring.ch1;

import young.refactoring.ch1.enums.PlayType;
import young.refactoring.ch1.model.*;

public class Statement {

    public String statement(Invoice invoice, Plays plays) throws Exception {
        return renderPlainText(invoice, plays);
    }

    private String renderPlainText(Invoice invoice, Plays plays) throws Exception {
        StringBuilder result = new StringBuilder(String.format("청구내역 (고객명: %s)\n", invoice.getCustomer()));

        for (Performance performance : invoice.getPerformanceList()) {
            result.append(String.format("%s: %d %d석\n"
                    , playFor(performance, plays).getName(), amountFor(performance, plays) / 100, performance.getAudience()));
        }
        result.append(String.format("총액: %d\n", totalAmount(invoice, plays)));
        result.append(String.format("적립 포인트: %d점\n", totalVolumeCredits(invoice, plays)));
        return result.toString();
    }

    private int totalAmount(Invoice invoice, Plays plays) throws Exception {
        int totalAmount = 0;
        for (Performance performance : invoice.getPerformanceList()) {
            //청구 내역 출력
            totalAmount += amountFor(performance, plays);
        }
        return totalAmount / 100;
    }

    // 전체 관객수 기반 credit 계산
    private int totalVolumeCredits(Invoice invoice, Plays plays) {
        int result = 0;
        for (Performance performance : invoice.getPerformanceList()) {
            //포인트를 적립
            result += volumeCreditFor(plays, performance);
        }
        return result;
    }

    // credit 계산
    private int volumeCreditFor(Plays plays, Performance performance) {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);

        //희극 관객 5명 추가마다 추가 포인트 제공.
        if (PlayType.COMEDY.equals(playFor(performance, plays).getType())) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }

    // For 이하 대상에 대한 금액
    private int amountFor(Performance performance, Plays plays) throws Exception {
        int result = 0;
        switch (playFor(performance, plays).getType()) {
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
                throw new Exception("알 수 없는 장르 : " + playFor(performance, plays));
        }
        return result;
    }

    // 임시 변수 질의 함수로 바꾸기
    private Play playFor(Performance performance, Plays plays) {
        return plays.get(performance);
    }

}
