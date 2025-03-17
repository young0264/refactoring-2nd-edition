package young.refactoring.ch9.splitVariableExam;

public class EditInputParameter {

    public int discount(int inputValue, int quantity) {
        if (inputValue > 50) inputValue -= 2;
        if (quantity > 100) inputValue -= 1;
        return inputValue;
    }

}
