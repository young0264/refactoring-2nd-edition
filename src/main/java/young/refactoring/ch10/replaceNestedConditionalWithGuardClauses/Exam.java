package young.refactoring.ch10.replaceNestedConditionalWithGuardClauses;

public class Exam {
    public AmountReason payAmount(Employee employee){
        AmountReason result;
        if (employee.isSeparated()) { // 퇴사한 직원
            return  new AmountReason(0, "SEP");
        } if (employee.isRetired()) { // 은퇴한 직원
            return new AmountReason(0, "RET");
        }
        return calculateSalary(); //급여 계산 로직
    }

    private AmountReason calculateSalary() { // 그 외,
        return new AmountReason(0, "ELSE");
    }

}
