package young.refactoring.ch11.replaceConstructorWithFactoryFunction;

public class Employee {
    String name;
    String type;

    public Employee(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public static Employee createEmployee(String name, String empType) {
        return new Employee(name, empType);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}