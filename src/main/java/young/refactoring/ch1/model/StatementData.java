package young.refactoring.ch1.model;

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

}
