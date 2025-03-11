package young.refactoring.ch8.moveField;

import java.time.LocalDateTime;

public class Customer {
    protected String name;
    protected double discountRate;
    protected CustomerContract customerContract;

    public Customer(String name, double discountRate) {
        this.name = name;
        this.discountRate = discountRate;
        this.customerContract = new CustomerContract(LocalDateTime.now());
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void becomePreferred() {
        discountRate += 0.3;
        // do something
    }

    public int applyAmount(int amount) {
        return Math.subtractExact(amount, (int) (amount * discountRate));
    }
}