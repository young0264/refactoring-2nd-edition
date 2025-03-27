package young.refactoring.ch10.replaceControlFlagWithBreak;

public class Exam {

    public String checkForMiscreants(People people) {
        boolean found = false;
        for (Person person : people.people()) {
            if (!found) {
                if (person.name().equals("조커")) {
                    found = true;
                    return sendAert();
                }else if (person.name().equals("사루만")) {
                    found = true;
                    return sendAert();
                }
            }
        }
        return "신원불명";
    }

    private String sendAert() {
        return "찾았다는 알림을 보냅니다.";
    }

}
