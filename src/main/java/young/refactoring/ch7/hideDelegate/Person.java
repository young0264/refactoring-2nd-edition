package young.refactoring.ch7.hideDelegate;

// Person(사람) 클래스
public class Person {
    protected String name;
    protected Department department;

    public Person(String name) {
        this.name = name;
    }

    // AS-IS, todo: department 참조 메서드 지우기
    public Department getDepartment() {
        return department;
    }

    // TO-BE
    public Person getManager() {
        return department.getManager();
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}