package young.refactoring.ch7.hideDelegate;

// Department 클래스
public class Department {
    protected int chargeCode;
    protected Person manager; // 관리자

    public int getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(int chargeCode) {
        this.chargeCode = chargeCode;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }
}