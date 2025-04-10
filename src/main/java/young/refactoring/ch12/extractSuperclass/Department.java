package young.refactoring.ch12.extractSuperclass;

public class Department {
    String name;
    Staff staff;

    public Department(String name, Staff staff) {
        this.name = name;
        this.staff = staff;
    }

    public String getName() {
        return name;
    }

    public Staff getStaff() {
        return staff;
    }

    public int length() {
        return staff.length;
    }

    public int totalMonthlyCost() {
        return staff.employees
                .stream()
                .map(e -> e.monthlyCost)
                .reduce(0, Integer::sum);
    }

    public int totalAnnualCost() {
        return totalMonthlyCost() * 12;
    }
}
