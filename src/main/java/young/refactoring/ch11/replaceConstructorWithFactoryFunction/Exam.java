package young.refactoring.ch11.replaceConstructorWithFactoryFunction;

public class Exam {
    private Document document;

    public void client() {
        Employee candidate = createEmployee(document.name, document.empType);
    }

    public void client2() {
        Employee leadEngineer = createEngineer(document.leadEngineerName);
    }

    private Employee createEmployee(String name, String empType) {
        return Employee.createEmployee(name, empType);
    }

    private Employee createEngineer(String leadEngineerName) {
        return Employee.createEmployee(leadEngineerName, "Engineer");
    }

}
