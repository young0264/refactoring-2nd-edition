package young.refactoring.ch8.moveFunction;

public class AccountType {

    public boolean isPremium;

    public double overdraftCharge(int daysOverdrawn) {
        // 프리미엄 멤버
        if (this.isPremium) {
            int baseCharge = 10;
            if (daysOverdrawn <= 7) {
                return baseCharge;
            } else { // 7일 이상이면 보다 낮은 이율 적용
                return baseCharge + (daysOverdrawn - 7) * 0.85;
            }
        // 일반 멤버
        } else {
            return daysOverdrawn * 1.75;
        }
    }
}
