package young.refactoring.ch10.introduceAssertion;

public class Customer {

    double discountRate;

    // 할인율이 항상 양수라는 가정.
    // 하지만 음수가 가능한 메서드(책 기준 코드)
    public double applyDiscountAllowingOver100Percent(int aNumber) {
        return (this.discountRate > 0) // 1.0, 100% 미만이어야함.
                ? aNumber - this.discountRate * aNumber 
                : aNumber;
    }

    // 할인율 음수가 불가능한 메서드
    public double applyDiscountUnder100Percent(int aNumber) {
        return (this.discountRate > 0 && this.discountRate <= 1) // 1.0, 100% 미만이어야함.
                ? aNumber - this.discountRate * aNumber
                : aNumber;
    }

    // 금액과 할인율 모두 음수가 불가능한 메서드
    public double applyDiscountWithNonNegativeInputs(int aNumber) {
        if (aNumber < 0) {
            throw new IllegalArgumentException("할인 대상 금액은 음수가 될 수 없습니다.");
        }
        return (this.discountRate > 0 && this.discountRate <= 1) // 1.0, 100% 미만이어야함.
                ? aNumber - this.discountRate * aNumber
                : aNumber;
    }

    // setter
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

}
