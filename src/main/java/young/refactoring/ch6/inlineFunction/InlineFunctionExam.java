package young.refactoring.ch6.inlineFunction;

public class InlineFunctionExam {

    public int rating(Driver driver){
        return moreThanFiveLateDeliveries(driver) ? 2 : 1;
    }

    public boolean moreThanFiveLateDeliveries(Driver driver) {
        return driver.numberOfLateDeliveries > 5;
    }
}
