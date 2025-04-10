package young.refactoring.ch12.removeSubClass;

// 제거
public class Female extends Person {

    public Female(String name) {
        super(name);
    }

    @Override
    public String getGenderCode() {
        return "F";
    }
}