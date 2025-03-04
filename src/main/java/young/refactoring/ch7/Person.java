package young.refactoring.ch7;

public class Person {
    protected String name;
    protected Department department;

    public Person(String name) {
        this.name = name;
    }

    // TO-BE
    // 이 메서드를 통해 Department 객체에 접근 가능해짐
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}