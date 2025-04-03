package young.refactoring.ch11.removeSettingMethod;

public class Exam {

    public void client() {
        Person person = new Person();

        person.id = 1234;
        person.name = "마틴";
    }

}
