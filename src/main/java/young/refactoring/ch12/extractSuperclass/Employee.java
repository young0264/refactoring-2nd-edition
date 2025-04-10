package young.refactoring.ch12.extractSuperclass;

public class Employee{
    Long id;
    String name;
    int monthlyCost;

    public Employee(Long id, String name, int monthlyCost) {
        this.id = id;
        this.name = name;
        this.monthlyCost = monthlyCost;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMonthlyCost() {
        return monthlyCost;
    }

    public int annualCost() {
        return this.monthlyCost * 12;
    }
}
