package young.refactoring.ch12.replaceTypeCodeWithSubClass.directInheritance;

public class Manager extends Employee {

    public Manager(String name) throws Exception{
        super(name);
    }

    public String getType() {
        return "manager";
    }
}
