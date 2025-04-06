package young.refactoring.ch11.replaceCommandWithFunction;

public class ChargeCalculator {
    Customer customer;
    int usage;
    Provider provider;

    public ChargeCalculator(Customer customer, int usage, Provider provider) {
        this.customer = customer;
        this.usage = usage;
        this.provider = provider;
    }

    public double getBaseCharge() {
        return customer.baseRate * usage;
    }

    public double charge() {
        return getBaseCharge() + provider.connectionCharge;
    }
}