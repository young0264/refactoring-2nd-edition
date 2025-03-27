package young.refactoring.ch10.replaceControlFlagWithBreak;

import java.util.Optional;

public class Exam {

    public Optional<String> checkForMiscreants(People people) {
        return people.people().stream()
                .map(Person::name)
                .filter(name -> name.equals("조커") || name.equals("사루만"))
                .findFirst()
                .map(name -> Optional.of(sendAert()))
                .orElse(Optional.empty());
    }

    private String sendAert() {
        return "찾았다는 알림을 보냅니다.";
    }

}
