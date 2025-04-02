package young.refactoring.ch11.parameterizeFunction;

public class Exam {
    public void tenPercentRaise(Person person) {
        person.salary = (int) (person.salary * 1.1);
    }

    public void fivePercentRaise(Person person) {
        person.salary = (int) (person.salary * 1.05);
    }
}
