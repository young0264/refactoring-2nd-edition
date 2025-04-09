package young.refactoring.ch12.pullUpMethod;

public class Department extends Party {
    int monthlyCost;

    public int totalAnnualCost() {
        return monthlyCost * 12;
    }
}

