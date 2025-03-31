package young.refactoring.ch10.introduceAssertion;
import org.springframework.util.Assert;

public class Customer {

    double discountRate;

    // [setter] discountRate가 처음 할당되는 곳
    public void setDiscountRate(double discountRate) {
        Assert.isTrue(discountRate > 0 && discountRate <= 1
                , "할인율은 0보다 크고 1보다 작아야 합니다.");
        this.discountRate = discountRate;
    }

    // 할인율이 항상 양수라는 가정 _ 할인률이 100프로가 넘으면 음수가 가능한 메서드(책 기준 코드)
    public double applyDiscountAllowingOver100Percent(int aNumber) {
        if (this.discountRate > 0)
            return aNumber - this.discountRate * aNumber;
        return aNumber;
    }

    // 할인율 음수가 불가능한 메서드
    public double applyDiscountUnder100Percent(int aNumber) {
        if (this.discountRate > 0 && this.discountRate <= 1)
            return aNumber - this.discountRate * aNumber;
        return aNumber;
    }

    // 금액과 할인율 모두 음수가 불가능한 메서드
    public double applyDiscountWithNonNegativeInputs(int aNumber) {
        if (aNumber < 0) throw new IllegalArgumentException("할인 대상 금액은 음수가 될 수 없습니다.");
        if (this.discountRate > 0 && this.discountRate <= 1)
            return aNumber - this.discountRate * aNumber;
        return aNumber;
    }

}
