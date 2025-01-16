package young.refactoring.ch1.model;

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

}
