package young.refactoring.ch12.removeSubClass;

public class Female extends Person {

    public Female(String name) {
        super(name);
    }

    @Override
    public String getGenderCode() {
        return "F";
    }
}