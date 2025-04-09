package young.refactoring.ch12.pullUpMethod;

public class Employee extends Party {
    int monthlyCost;

    public int annualCost() {
        return monthlyCost * 12;
    }
}
