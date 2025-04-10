package young.refactoring.ch12.replaceTypeCodeWithSubClass.directInheritance;

public class Salesperson extends Employee {

    public Salesperson(String name) throws Exception {
        super(name);
    }

    public String getType() {
        return "salesperson";
    }
}
