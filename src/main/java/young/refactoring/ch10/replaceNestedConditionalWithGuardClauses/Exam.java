package young.refactoring.ch10.replaceNestedConditionalWithGuardClauses;

public class Exam {
    public AmountReason payAmount(Employee employee){
        AmountReason result;
        if (employee.isSeparated()) { // 퇴사한 직원
            result = new AmountReason(0, "SEP");
        } else if (employee.isRetired()) { // 은퇴한 직원 , 책과는 상이함
            result = new AmountReason(0, "RET");
        } else {
            //급여 계산 로직
            result = calculateSalary();
        }
        return result;
    }

    private AmountReason calculateSalary() { // 그 외,
        return new AmountReason(0, "ELSE");
    }

}
