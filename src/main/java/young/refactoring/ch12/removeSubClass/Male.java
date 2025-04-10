package young.refactoring.ch12.removeSubClass;

// 제거
public class Male extends Person {

    public Male(String name) {
        super(name);
    }

    @Override
    public String getGenderCode() {
        return "M";
    }
}