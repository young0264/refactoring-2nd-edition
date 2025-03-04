package young.refactoring.ch7;

import java.util.Arrays;
import java.util.List;

public class SubstituteAlgorithm {

    // AS-IS
    public String foundPersonAsIs(String[] people) {
        for (int i = 0; i < people.length; i++) {
            if (people[i].equals("Don")) {
                return "Don";
            }
            if (people[i].equals("John")) {
                return "John";
            }
            if (people[i].equals("Kent")) {
                return "Kent";
            }
        }
        return "";
    }

    // TO-BE
    public String foundPersonToBe(String[] people) {
        String[] candidates = new String[]{"Don", "John", "Kent"};
        List<String> candidateListFirst = Arrays.asList("Don", "John", "Kent");
        List<String> candidateListSecond = List.of("Don", "John", "Kent");
        return Arrays.stream(people)
                .filter(candidateListFirst::contains)
                .findFirst()
                .orElse("");
    }

}
