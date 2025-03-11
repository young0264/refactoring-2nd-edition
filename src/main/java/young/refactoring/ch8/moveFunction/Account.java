package young.refactoring.ch8.moveFunction;

public record Account (
        int dayOverdrawn, //(계좌별로 달라지는 변수), 특정날 초과인출?
        AccountType type
){

    public Account(int dayOverdrawn, AccountType type) {
        this.dayOverdrawn = dayOverdrawn;
        this.type = type;
    }

    /** 은행 이자 계산 */
    public double bankCharge(){
        double result = 4.5;
        if (this.dayOverdrawn > 0) {
            result += this.overdraftCharge();
        }
        return result;
    }

    /** 초과 인출 이자 계산 */
    public double overdraftCharge() { // 위임 메서드
        return this.type.overdraftCharge(this.dayOverdrawn);
    }

}
