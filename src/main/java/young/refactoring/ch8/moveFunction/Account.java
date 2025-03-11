package young.refactoring.ch8.moveFunction;

public class Account {
    private int dayOverdrawn;
    private AccountType type;

    /** 은행 이자 계산 */
    public double bankCharge(){
        double result = 4.5;
        if (this.dayOverdrawn > 0) {
            result += this.overdraftCharge();
        }
        return result;
    }

    /** 초과 인출 이자 계산 */
    public double overdraftCharge() {
        if (this.type.isPremium) {
            int baseCharge = 10;
            if (this.dayOverdrawn <= 7) {
                return baseCharge;
            } else {
                return baseCharge * (this.dayOverdrawn - 7) * 0.85;
            }
        } else {
            return this.dayOverdrawn * 1.75;
        }
    }

}
