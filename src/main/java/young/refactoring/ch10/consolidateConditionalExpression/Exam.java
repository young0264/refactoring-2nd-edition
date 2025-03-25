package young.refactoring.ch10.consolidateConditionalExpression;

public class Exam {
    public int disabilityAmount(Employee employee) {
        if(employee.seniority() < 2) return 0;
        if(employee.monthDisabled() > 12) return 0;
        if(employee.isPartTime()) return 0;

        // 장애수단 계산
        return 0;
    }
}
