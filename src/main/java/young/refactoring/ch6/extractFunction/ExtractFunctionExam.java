package young.refactoring.ch6.extractFunction;

import java.time.*;

public class ExtractFunctionExam {

    public void printOwing(Invoice invoice) {

        printBanner();

        int outstanding = calculateOutstanding(invoice);
        recordDueDate(invoice);
        printDetails(invoice, outstanding);
    }

    private static void printBanner() {
        System.out.println("*****************");
        System.out.println("**** 고객 채무 ****");
        System.out.println("*****************");
    }

    // 마감일 기록
    private void recordDueDate(Invoice invoice) {
        LocalDateTime now = LocalDateTime.now();
        invoice.dueDate = now.plusDays(30);
    }

    //미해결 채무(outstanding)를 계산
    private static int calculateOutstanding(Invoice invoice) {
        int outstanding = 0;
        for (Order o : invoice.getOrders()) {
            outstanding += o.getAmount();
        }
        return outstanding;
    }

    // 세부 사항을 출력
    private void printDetails(Invoice invoice, int outstanding) {
        System.out.println(String.format("고객명: %s", invoice.customer));
        System.out.println(String.format("채무액: %d", outstanding));
        System.out.println(String.format("마감일: %s", invoice.dueDate));
    }

}
