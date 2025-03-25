package young.refactoring.ch10.decomposeConditional;

import java.time.LocalDateTime;

public record Plan (
    LocalDateTime summerStart,
    LocalDateTime summerEnd,
    double summerRate,
    double regularRate,
    double regularServiceCharge
    ) {}
