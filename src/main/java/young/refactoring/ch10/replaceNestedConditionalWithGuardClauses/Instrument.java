package young.refactoring.ch10.replaceNestedConditionalWithGuardClauses;

public record Instrument (
        int capital,
        double interRate,
        int duration,
        int income,
        double adjustmentFactor
) {
}
