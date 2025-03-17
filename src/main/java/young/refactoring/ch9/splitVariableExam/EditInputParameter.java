package young.refactoring.ch9.splitVariableExam;

public class EditInputParameter {

    public int discount(int inputValue, int quantity) {
        int result = inputValue;
        if (inputValue > 50) result -= 2;
        if (quantity > 100) result -= 1;
        return result;
    }

}
