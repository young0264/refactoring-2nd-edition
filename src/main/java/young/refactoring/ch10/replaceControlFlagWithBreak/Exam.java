package young.refactoring.ch10.replaceControlFlagWithBreak;

public class Exam {

    public String checkForMiscreants(People people) {
        for (Person person : people.people()) {
            if (person.name().equals("조커")) {
                return sendAert();
            }else if (person.name().equals("사루만")) {
                return sendAert();
            }
        }
        return "신원불명";
    }

    private String sendAert() {
        return "찾았다는 알림을 보냅니다.";
    }

}
