package young.refactoring.ch11.replaceParameterWithQuery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    @DisplayName("100보다 작을 때 할인 적용 -> 5% 할인")
    void finalPrice_shouldApply5PercentDiscount_whenQuantityIs100OrLess() {
        // given
        Order order = new Order(50, 100);

        // when
        int finalPrice = order.finalPrice();

        // then
        assertEquals(4750, finalPrice); // 50 * 100 = 5000 -> 5% 할인
    }

    @Test
    @DisplayName("100보다 클 때 할인 적용 -> 10% 할인")
    void finalPrice_shouldApply10PercentDiscount_whenQuantityIsMoreThan100() {
        // given
        Order order = new Order(200, 100);

        // when
        int finalPrice = order.finalPrice();

        // then
        assertEquals(18000, finalPrice); // 200 * 100 = 20000 -> 10% 할인
    }


}