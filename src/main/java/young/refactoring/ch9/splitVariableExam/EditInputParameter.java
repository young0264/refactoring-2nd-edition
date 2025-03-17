package young.refactoring.ch9.splitVariableExam;

public class EditInputParameter {

    public int discount(int originalInputValue, int quantity) {
        int inputValue = originalInputValue;
        if (inputValue > 50) inputValue -= 2;
        if (quantity > 100) inputValue -= 1;
        return inputValue;
    }

}
