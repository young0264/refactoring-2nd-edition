package young.refactoring.ch12.pullUpConstrucBody;

public class Employee extends Party {
    Long id;
    int monthlyCost;


    public Employee(String name) {
        super(name);
    }

    public Employee(Long id, String name, int monthlyCost) {
        super(name);
        this.id = id;
        this.monthlyCost = monthlyCost;
    }

    public boolean isPrivileged() {
        return false;
    }

    protected void assignCar() {
    }

    protected void finishConstruction() {
        if (isPrivileged()) assignCar();
    }
}
