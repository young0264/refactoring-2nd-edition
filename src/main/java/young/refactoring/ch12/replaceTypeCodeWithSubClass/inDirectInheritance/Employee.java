package young.refactoring.ch12.replaceTypeCodeWithSubClass.inDirectInheritance;

public class Employee {
    String name;
    EmployeeType type;

    public Employee(String name, String type) throws Exception {
        this.name = name;
        this.type = createEmployeeType(type);
    }

    private EmployeeType createEmployeeType(String type) throws Exception {
        switch (type) {
            case "manager": return new Manager();
            case "engineer": return new Engineer();
            case "salesperson": return new Salesperson();
            default: throw new Exception(String.format("%s 에 해당하는 직원 유형은 없습니다.", type));
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", type='" + getType() + '\'' +
                '}';
    }

    public String getType() {
        return type.getValue();
    }
}
