package young.refactoring.ch11.replaceConstructorWithFactoryFunction;

public class Exam {
    private Document document;

    public void client() {
        Employee candidate = new Employee(document.name, document.empType);
    }

    public void client2() {
        Employee leadEngineer = new Employee(document.leadEngineerName, "E");
    }

}
