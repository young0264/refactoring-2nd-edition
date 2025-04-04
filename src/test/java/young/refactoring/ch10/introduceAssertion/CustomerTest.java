package young.refactoring.ch10.introduceAssertion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer customer ;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    @Test
    @DisplayName("[over 100] 할인율이 양수일 경우 할인 금액이 적용된다")
    void applyDiscount_AllowingOver100Percent_whenRateIsValid_returnsDiscountedAmount() {
        customer.setDiscountRate(0.2); // 20% 할인

        double result = customer.applyDiscountAllowingOver100Percent(100); // 100 - 20 = 80
        assertEquals(80.0, result, 0.0001);
    }

    @Test
    @DisplayName("[over 100] 할인율이 0일 경우 원래 금액이 반환된다")
    void applyDiscount_AllowingOver100Percent_whenRateIsZero_returnsOriginalAmount() {
        // 할인 없음
        assertThrows(IllegalArgumentException.class, () -> customer.setDiscountRate(0.0));

        double result = customer.applyDiscountAllowingOver100Percent(100);
        assertEquals(100.0, result, 0.0001);
    }

    @Test
    @DisplayName("[over 100] 할인율이 음수일 경우 할인되지 않고 원래 금액이 반환된다")
    void applyDiscount_AllowingOver100Percent_whenRateIsNegative_returnsOriginalAmount() {
        // 잘못된 값 -> 로직상 그대로 반환
        assertThrows(IllegalArgumentException.class, () -> customer.setDiscountRate(-0.1));

        double result = customer.applyDiscountAllowingOver100Percent(100);
        assertEquals(100.0, result, 0.0001);
    }

    @Test
    @DisplayName("[under 100] 할인율이 1.0 초과일 경우 금액이 음수가 될 수 없다")
    void applyDiscount_AllowingOver100Percent_whenRateIsAboveOne_returnsNegativeAmount() {
        // 120% 할인 -> 비정상 할인 -> 기본 값 return
        assertThrows(IllegalArgumentException.class, () -> customer.setDiscountRate(1.2));

        double result = customer.applyDiscountUnder100Percent(100);
        assertEquals(100, result, 0.0001);
    }

    @Test
    @DisplayName("[under 100] 할인율이 1.0일 경우 금액이 0이 되어야 한다")
    void applyDiscount_AllowingUnder100Percent_whenRateIsOne_returnsZero() {
        customer.setDiscountRate(1.0); // 100% 할인

        double result = customer.applyDiscountUnder100Percent(100);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    @DisplayName("[under 100] 할인율이 아주 작을 경우 거의 원래 금액과 동일해야 한다")
    void applyDiscount_AllowingUnder100Percent_whenRateIsTiny_returnsAlmostOriginal() {
        // 거의 0
        assertThrows(IllegalArgumentException.class, () -> customer.setDiscountRate(0));
        double result = customer.applyDiscountUnder100Percent(100);
        assertEquals(100.0,result, 0.0001); // 매우 작게 깎임
    }

    @Test
    @DisplayName("[under 100] 할인율이 NaN/Infinity 이면 원래 금액이 반환되어야 한다")
    void applyDiscount_AllowingUnder100Percent_whenRateIsNaN_returnsOriginal() {
        Customer customerA = new Customer();
        Customer customerB = new Customer();

        assertThrows(IllegalArgumentException.class, () -> customerA.setDiscountRate(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> customerB.setDiscountRate(Double.POSITIVE_INFINITY));
    }

    @Test
    @DisplayName("[under 100] 할인 대상 금액이 0일 경우 항상 0을 반환한다")
    void applyDiscount_whenBaseAmountIsZero_returnsZero() {
        customer.setDiscountRate(0.5);

        double result = customer.applyDiscountUnder100Percent(0);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    @DisplayName("[under 100] 할인 대상 금액이 음수일 경우 그대로 할인 계산된다")
    void applyDiscount_whenBaseAmountIsNegative_returnsDiscountedNegative() {
        customer.setDiscountRate(0.2);

        double result = customer.applyDiscountUnder100Percent(-100);
        assertEquals(-80.0, result, 0.0001); // -100 + 20% = -80
    }

    @Test
    @DisplayName("[price non minus] 금액이 음수일 경우 예외 발생")
    void applyDiscount_negativeAmount_throwsException() {
        customer.setDiscountRate(0.5);

        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, () -> {
                    customer.applyDiscountWithNonNegativeInputs(-100);
        });
        assertEquals("할인 대상 금액은 음수가 될 수 없습니다.", illegalArgumentException.getMessage());
    }

}
