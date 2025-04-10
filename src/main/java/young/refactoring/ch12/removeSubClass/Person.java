package young.refactoring.ch12.removeSubClass;

public class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getGenderCode() {
        return "X";
    }
}