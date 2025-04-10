package young.refactoring.ch11.replaceErrorCodeWithException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ExamTest {

    private Exam exam;

    @BeforeEach
    void setUp() {
        exam = new Exam();

        // 기본 설정
        Order dummyOrder = new Order();
        dummyOrder.country = "KR";

        CountryData countryData = new CountryData();
        countryData.shippingRules = new HashMap<>();
        countryData.shippingRules.put("KR", "Domestic Rule");
        countryData.shippingRules.put("US", "");

        exam.orderData = dummyOrder;
        exam.countryData = countryData;
        exam.errorList = new ArrayList<>();
    }

    @Test
    @DisplayName("정상 국가인 경우 - 에러 없음")
    void testShippingCostSuccess() throws Exception {
        exam.orderData.country = "KR";

        exam.exam();

        assertTrue(exam.errorList.isEmpty(), "errorList는 비어 있어야 합니다.");
    }

    @Test
    @DisplayName("배송 규칙이 없으면 OrderProcessingError 발생하고 errorList에 추가되어야 한다")
    void testShippingCostFailure() throws Exception {
        exam.orderData.country = "US"; // 빈 값

        exam.exam();

        assertEquals(1, exam.errorList.size());
        OrderProcessingError errorCode = exam.errorList.get(0).getErrorCode();
        assertTrue(errorCode instanceof OrderProcessingError);
        assertEquals("주문 처리 오류 : -23", ((OrderProcessingError) errorCode).getErrorCode()); // ✅ 이게 진짜 확인해야 할 내용
    }

    @Test
    @DisplayName("배송 규칙이 없으면 OrderProcessingError 발생하고 errorList에 추가되어야 한다")
    void testGenericExceptionWhenShippingRulesIsNull() throws Exception {
        exam.orderData.country = "JP"; // Map에 존재하지 않음

        exam.exam();

        assertEquals(1, exam.errorList.size());

        CustomError error = exam.errorList.get(0);
        assertTrue(error.getErrorCode() instanceof OrderProcessingError);
        assertEquals("주문 처리 오류 : -23", ((OrderProcessingError) error.getErrorCode()).getErrorCode());
    }

}