package young.refactoring.ch11.replaceFunctionWithCommand;

public class Score {

    public int execute(Candidate candidate, MedicalExample medicalExample, ScoringGuide scoringGuide) {
        int result = 0;
        int healthLevel = 0;
        boolean highMedicalRiskFlag = false;

        if (medicalExample.isSmoker) {
            healthLevel += 10;
            highMedicalRiskFlag = true;
        }

        String certificationGrade = "regular";
        if (scoringGuide.stateWithLowCertification(candidate.originalState)) {
            certificationGrade = "low";
            result -= 5;
        }

        // 비슷한 코드가 한 참 이어짐
        result -= Math.max(healthLevel - 5, 0);
        return result;
    }
}
