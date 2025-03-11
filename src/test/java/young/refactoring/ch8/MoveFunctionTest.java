package young.refactoring.ch8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import young.refactoring.ch8.moveFunction.Account;
import young.refactoring.ch8.moveFunction.AccountType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveFunctionTest {

    @Test
    @DisplayName("초과 인출이 없을때 4.5가 나오는지")
    void isBankCharge_noOverdraft() {
        AccountType type = new AccountType();
        type.isPremium = false; // 일반 계좌

        Account account = new Account(0, type);

        assertEquals(4.5, account.bankCharge(), 0.001);
    }

    @Test
    @DisplayName("일반 계좌(isPremium=false)에서 초과 인출 3일일 때 정상적으로 이자가 계산되는지 확인")
    void isBankCharge_withOverdraft() {
        AccountType type = new AccountType();
        type.isPremium = false; // 일반 계좌

        Account account = new Account(3, type);

        double expectedCharge = 4.5 + (3 * 1.75); // 4.5 + 5.25 = 9.75
        assertEquals(expectedCharge, account.bankCharge(), 0.001);
    }

    @Test
    @DisplayName("프리미엄 계좌에서 초과 인출이 7일 이하일 경우, 기본 요금(10)이 적용되는지 확인")
    void isOverdraftCharge_premiumWithin7Days() {
        AccountType type = new AccountType();
        type.isPremium = true;

        assertEquals(10.0, type.overdraftCharge(5), 0.001); // 7일 이하 → 10.0
    }

    @Test
    @DisplayName("프리미엄 계좌에서 초과 인출이 7일 초과할 때 추가 이자가 계산되는지 확인")
    void isOverdraftCharge_premiumBeyond7Days() {
        AccountType type = new AccountType();
        type.isPremium = true;

        double expectedCharge = 10 + (3 * 0.85); // (7일 초과한 3일)
        assertEquals(expectedCharge, type.overdraftCharge(10), 0.001);
    }

    @Test
    @DisplayName("일반 계좌(isPremium=false)의 초과 인출 요금이 정상 계산되는지 확인")
    void isOverdraftCharge_nonPremium() {
        AccountType type = new AccountType();
        type.isPremium = false;

        assertEquals(8.75, type.overdraftCharge(5), 0.001); // 5 * 1.75
    }
}
