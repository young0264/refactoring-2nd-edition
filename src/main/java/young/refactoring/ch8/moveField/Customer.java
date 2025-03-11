package young.refactoring.ch8.moveField;

import java.time.LocalDateTime;

public class Customer {
    protected String name;
    protected CustomerContract customerContract;

    public Customer(String name, double discountRate) {
        this.name = name;
        this.customerContract = new CustomerContract(LocalDateTime.now(), discountRate);
    }

    private void setDiscountRate(double discountRate) {
        this.customerContract.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return customerContract.discountRate;
    }

    public void becomePreferred() {
        setDiscountRate(getDiscountRate() + 0.3);
        // 다른 멋진 일들
    }

    public int applyAmount(int amount) {
        return Math.subtractExact(amount, (int) (amount * getDiscountRate()));
    }

}