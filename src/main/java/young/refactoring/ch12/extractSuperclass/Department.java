package young.refactoring.ch12.extractSuperclass;

public class Department extends Party {
    Staff staff;

    public Department(String name, Staff staff) {
        super(name);
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
        return staff.employees.stream()
                .map(e -> e.monthlyCost)
                .reduce(0, Integer::sum);
    }

}
