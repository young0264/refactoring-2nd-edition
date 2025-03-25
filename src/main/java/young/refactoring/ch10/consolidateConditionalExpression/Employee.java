package young.refactoring.ch10.consolidateConditionalExpression;

public record Employee(
        int seniority,
        int monthDisabled,
        boolean isPartTime
) {
}
