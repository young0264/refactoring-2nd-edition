package young.refactoring.ch11.replaceFunctionWithCommand;

public class Exam {

    public int score(Candidate candidate, MedicalExample medicalExample, ScoringGuide scoringGuide) {
        return new Score().execute(candidate, medicalExample, scoringGuide);
    }
}
