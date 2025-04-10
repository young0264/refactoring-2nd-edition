package young.refactoring.ch12.replaceTypeCodeWithSubClass.directInheritance;

public class Engineer extends Employee {

    public Engineer(String name) throws Exception {
        super(name);
    }

    public String getType() {
        return "engineer";
    }
}
