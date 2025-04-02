package young.refactoring.ch11.parameterizeFunction;

public class Exam {
    //ten: 1.1, five: 1.05
    // 리터럴 값을 매개변수화해서 하나의 함수로 만들 수 있음
    public void raise(Person person, double factor) {
        person.salary = (int) (person.salary * factor);
    }
}
