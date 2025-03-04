package young.refactoring.ch7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubstituteAlgorithmTest {
    private final SubstituteAlgorithm substituteAlgorithm = new SubstituteAlgorithm();

    @Test
    @DisplayName("첫번째로 일치하는 후보자를 반환하는지")
    void foundPersonToBe_ShouldReturnFirstMatchingCandidate() {
        String[] people = {"Alice", "Bob", "John", "Don", "Kent"};
        assertEquals("John", substituteAlgorithm.foundPersonToBe(people));
    }

    @Test
    @DisplayName("후보자 리스트에 일치하는 값이 없을 때 빈 문자열을 반환하는지")
    void foundPersonToBe_ShouldReturnEmptyString_WhenNoCandidatesMatch() {
        String[] people = {"Alice", "Bob", "Eve"};
        assertEquals("", substituteAlgorithm.foundPersonToBe(people));
    }

}