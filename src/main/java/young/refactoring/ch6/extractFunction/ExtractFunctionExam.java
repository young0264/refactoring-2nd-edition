package young.refactoring.ch6.extractFunction;


public class ExtractFunctionExam {

    public int printOwing(Invoice invoice) {

        System.out.println("*****************");
        System.out.println("**** 고객 채무 ****");
        System.out.println("*****************");

        //미해결 채무(outstanding)를 계산
        int outstanding = 0;
        for (Order o : invoice.getOrders()) {
            outstanding += o.getAmount();
        }
        return outstanding;
    }

}
