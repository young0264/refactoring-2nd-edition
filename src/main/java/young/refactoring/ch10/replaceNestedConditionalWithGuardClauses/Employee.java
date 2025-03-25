package young.refactoring.ch10.replaceNestedConditionalWithGuardClauses;

public record Employee(
        boolean isSeparated,
        boolean isRetired
) {
}
