package young.refactoring.ch12.replaceTypeCodeWithSubClass.inDirectInheritance;

import java.util.List;

public class Employee {
    String name;
    EmployeeType type;

    public Employee(String name, EmployeeType type) throws Exception {
        validateType(type);
        this.name = name;
        this.type = type;
    }

    private void validateType(EmployeeType type) throws Exception {
        if (List.of("manager", "engineer", "salesperson")
                .stream()
                .noneMatch(t -> t.equals(type.getValue()))
        ) {
            throw new Exception(String.format("%s 에 해당하는 직원 유형은 없습니다.", type));
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
