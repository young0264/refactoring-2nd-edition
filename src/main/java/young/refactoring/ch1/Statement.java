package young.refactoring.ch1;

import young.refactoring.ch1.enums.PlayType;
import young.refactoring.ch1.model.Invoice;
import young.refactoring.ch1.model.Performance;
import young.refactoring.ch1.model.Play;
import young.refactoring.ch1.model.Plays;

public class Statement {

    public String statement(Invoice invoice, Plays plays) throws Exception {
        int totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder(String.format("청구내역 (고객명: %s)\n", invoice.getCustomer()));

        for (Performance performance : invoice.getPerformanceList()) {
            Play play = plays.get(performance.getPlayID());
            int thisAmount = 0;

            switch (play.getType()) {
                case TRAGEDY:
                    thisAmount = 40000;
                    if (performance.getAudience() > 30) {
                        thisAmount += 1000 * (performance.getAudience() - 30);
                    }
                    break;
                case COMEDY: //희극
                    thisAmount = 30000;
                    if (performance.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (performance.getAudience() - 20);
                    }
                    thisAmount += 300 * performance.getAudience();
                    break;
                default:
                    throw new Exception("알 수 없는 장르 : " + play.getType());
            }

            //포인트를 적립.
            volumeCredits += Math.max(performance.getAudience() - 30, 0);

            //희극 관객 5명 추가마다 추가 포인트 제공.
            if (PlayType.COMEDY.equals(play.getType())) {
                volumeCredits += Math.floor(performance.getAudience() / 5);
            }

            //청구 내역 출력
            result.append(String.format("%s: %d %d석\n", play.getName(), thisAmount / 100, performance.getAudience()));
            totalAmount += thisAmount;
        }

        result.append(String.format("총액: %d\n", totalAmount / 100));
        result.append(String.format("적립 포인트: %d점\n", volumeCredits));
        return result.toString();

    }


}
