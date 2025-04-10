package young.refactoring.ch12.replaceTypeCodeWithSubClass.inDirectInheritance;

//EmployeeType은 직접 인스턴스화되면 안 되고,
// 반드시 Manager, Engineer, Salesperson 같은 서브클래스가 사용되길 의도
public abstract class EmployeeType {
    private final String value;

    protected EmployeeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
