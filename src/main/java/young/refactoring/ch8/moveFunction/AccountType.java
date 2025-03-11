package young.refactoring.ch8.moveFunction;

public class AccountType {

    public boolean isPremium;

    public double overdraftCharge(int daysOverdrawn) {
        if (this.isPremium) {
            int baseCharge = 10;
            if (daysOverdrawn <= 7) {
                return baseCharge;
            } else {
                return baseCharge + (daysOverdrawn - 7) * 0.85;
            }
        } else {
            return daysOverdrawn * 1.75;
        }
    }
}
