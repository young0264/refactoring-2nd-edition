package young.refactoring.ch7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import young.refactoring.ch7.encapsulateRecord.ComparisonResult;
import young.refactoring.ch7.encapsulateRecord.CustomerData;
import young.refactoring.ch7.encapsulateRecord.CustomerDataStore;
import young.refactoring.ch7.encapsulateRecord.CustomerService;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerData customerData;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 초기화
        customerData = new CustomerData();
        customerData.setUsage("C001", 2023, 3, 100.0); // 이전 연도 데이터
        customerData.setUsage("C001", 2024, 3, 120.0); // 현재 연도 데이터

        // 3. 공유 저장소에 등록
        CustomerDataStore.setRawDataOfCustomers(customerData);
    }

    @Test
    @DisplayName("이전 데이터가 있을 경우 변경량이 올바르게 계산되는지 확인")
    void compareUsage_ShouldReturnCorrectChange() {
        // when
        ComparisonResult result = CustomerService.compareUsage("C001", 2024, 3);

        // then
        assertNotNull(result);
        assertEquals(120.0, result.getLaterAmount());
        assertEquals(20.0, result.getChange());
    }

    @Test
    @DisplayName("이전 데이터가 없을 경우 laterAmount는 0.0, 변경량도 0.0 반환")
    void compareUsage_ShouldReturnZeroWhenNoPreviousData() {
        // when
        ComparisonResult result = CustomerService.compareUsage("C002", 2024, 3);

        // then
        assertNotNull(result);
        assertEquals(0.0, result.getLaterAmount());
        assertEquals(0.0, result.getChange()); // 이전 데이터가 없으므로 차이값도 0
    }

    @Test
    @DisplayName("이전 연도 데이터가 없는 경우 laterAmount 그대로 반환하고 변경량은 같음")
    void compareUsage_ShouldHandleMissingDataGracefully() {
        // given
        customerData.setUsage("C003", 2024, 3, 50.0); // 이전 연도 데이터 없음

        // when
        ComparisonResult result = CustomerService.compareUsage("C003", 2024, 3);

        // then
        assertNotNull(result);
        assertEquals(50.0, result.getLaterAmount());
        assertEquals(50.0, result.getChange()); // 이전 연도 데이터가 없어서 0으로 계산됨
    }

    @Test
    @DisplayName("아예 데이터가 없는 경우에도 0.0 반환")
    void compareUsage_ShouldReturnZeroWhenNoDataAtAll() {
        // when
        ComparisonResult result = CustomerService.compareUsage("C004", 2025, 5);

        // then
        assertNotNull(result);
        assertEquals(0.0, result.getLaterAmount());
        assertEquals(0.0, result.getChange());
    }

}
