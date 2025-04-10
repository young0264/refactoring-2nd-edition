package young.refactoring.ch12.replaceTypeCodeWithSubClass.directInheritance;

import java.util.List;

public class Employee {
    String name;
    String type;

    public Employee(String name) throws Exception {
        this.name = name;
    }

    public Employee(String name, String type) throws Exception {
        validateType(type);
        this.name = name;
        this.type = type;
    }

    public static Employee createEmployee(String name, String type) throws Exception {
        switch (type) {
            case "engineer": return new Engineer(name);
            case "manager": return new Manager(name);
            case "salesperson": return new Salesperson(name);
        }
        return new Employee(name, type);
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
                ", type='" + type + '\'' +
                '}';
    }
}
