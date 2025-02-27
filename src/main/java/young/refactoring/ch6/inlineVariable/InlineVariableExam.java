package young.refactoring.ch6.inlineVariable;

public class InlineVariableExam {

    public boolean method(Order order){
        int basePrice = order.basePrice;
        return basePrice > 1000;
    }

}
