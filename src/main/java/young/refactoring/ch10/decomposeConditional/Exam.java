package young.refactoring.ch10.decomposeConditional;

import java.time.LocalDateTime;

public class Exam {
    int quantity;
    int charge;

    public void calculatePayment(Plan plan, LocalDateTime dateTime) {
        if (isSummer(dateTime, plan)) {
            charge = summerCharge(plan);
        } else {
            charge = regularCharge(plan);
        }
    }

    private boolean isSummer(LocalDateTime dateTime, Plan plan) {
        return !dateTime.isBefore(plan.summerStart()) // 현재가 더 이전 날짜
             && !dateTime.isAfter(plan.summerEnd()); // 현재가 더 나중 날짜
    }

    private int summerCharge(Plan plan) {
        return (int) (quantity * plan.summerRate());
    }

    private int regularCharge(Plan plan) {
        return (int) (quantity * plan.regularRate() + plan.regularServiceCharge());
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCharge() {
        return charge;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }
}
