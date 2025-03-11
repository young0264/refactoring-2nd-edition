package young.refactoring.ch8.moveField;

import java.time.LocalDateTime;

public class CustomerContract {
    protected LocalDateTime startDate;
    protected double discountRate;

    public CustomerContract(LocalDateTime startDate, double discountRate) {
        this.startDate = startDate;
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
}