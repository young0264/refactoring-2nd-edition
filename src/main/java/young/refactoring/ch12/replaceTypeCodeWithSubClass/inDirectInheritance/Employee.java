package young.refactoring.ch12.replaceTypeCodeWithSubClass.inDirectInheritance;

import java.util.List;

public class Employee {
    String name;
    String type;

    public Employee(String name, String type) throws Exception {
        validateType(type);
        this.name = name;
        this.type = type;
    }

    private void validateType(String type) throws Exception {
        if (List.of("manager", "engineer", "salesperson")
                .stream()
                .noneMatch(t -> t.equals(type))
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
        return type;
    }
}
