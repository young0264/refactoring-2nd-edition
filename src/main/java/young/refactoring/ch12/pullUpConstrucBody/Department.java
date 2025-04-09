package young.refactoring.ch12.pullUpConstrucBody;

public class Department extends Party {
    Staff staff;

    public Department(String name, Staff staff) {
        super(name);
        this.staff = staff;
    }
}
