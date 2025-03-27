package young.refactoring.ch10.replaceControlFlagWithBreak;

public class Exam {

    public String checkForMiscreants(People people) {
        return people.people().stream()
                .map(Person::name)
                .filter(name -> name.equals("조커") || name.equals("사루만"))
                .findFirst()
                .map(name -> sendAert())
                .orElse("신원불명");
    }

    private String sendAert() {
        return "찾았다는 알림을 보냅니다.";
    }

}
