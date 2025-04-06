package young.refactoring.ch11.replaceCommandWithFunction;

public class Exam {

    public void client(Customer customer, int usage, Provider provider) {
        charge(customer, usage, provider);
    }

    private void charge(Customer customer, int usage, Provider provider) {
        double monthCharge = new ChargeCalculator(customer, usage, provider).charge();
    }
}
