package young.refactoring.ch10.consolidateConditionalExpression;

public class Exam {
    public int disabilityAmount(Employee employee) {
        if(isNotEligibleForDisability(employee))
            return 0;

        // 장애수단 계산
        return 0;
    }

    // 책에서는 중첩함수.
    private static boolean isNotEligibleForDisability(Employee employee) {
        return employee.seniority() < 2
                || employee.monthDisabled() > 12
                || employee.isPartTime();
    }
}
