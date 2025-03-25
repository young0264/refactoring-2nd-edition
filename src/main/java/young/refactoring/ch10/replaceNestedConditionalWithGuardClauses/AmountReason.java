package young.refactoring.ch10.replaceNestedConditionalWithGuardClauses;

public record AmountReason(
        int amount,
        String reasonCode
) {
}
