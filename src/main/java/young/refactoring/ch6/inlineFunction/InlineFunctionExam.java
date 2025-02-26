package young.refactoring.ch6.inlineFunction;

public class InlineFunctionExam {

    public int rating(Driver driver){
        return driver.numberOfLateDeliveries > 5 ? 2 : 1; // 추출했던 함수를 원래 자리로.!
    }

}
